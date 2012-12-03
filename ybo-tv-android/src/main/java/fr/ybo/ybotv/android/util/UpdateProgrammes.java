package fr.ybo.ybotv.android.util;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.exception.YboTvErreurReseau;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.modele.FavoriteChannel;
import fr.ybo.ybotv.android.modele.LastUpdate;
import fr.ybo.ybotv.android.modele.Programme;
import fr.ybo.ybotv.android.service.YboTvService;

import java.util.*;


public class UpdateProgrammes extends TacheAvecGestionErreurReseau {

    private Handler handler;
    private ProgressBar loadingBar;
    private TextView messageLoading;
    private YboTvDatabase database;

    private final static Set<String> defaultFavoriteChannels = new HashSet<String>(){{
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");
        add("6");
        add("7");
        add("8");
        add("9");
        add("10");
        add("11");
        add("12");
        add("13");
        add("14");
        add("15");
        add("16");
        add("17");
        add("18");
        add("999");
    }};

    public UpdateProgrammes(Context context, Handler handler, final ProgressBar loadingBar, final TextView messageLoading, YboTvDatabase database) {
        super(context);
        this.handler = handler;
        this.loadingBar = loadingBar;
        this.messageLoading = messageLoading;
        this.database = database;
    }

    @Override
    protected void myDoBackground() throws YboTvErreurReseau {
        // Récupération des chaines
        Chrono chrono = new Chrono("UpdateProgramme>Reseau").start();
        List<Channel> channels = YboTvService.getInstance().getChannels();

        if (handler != null) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    messageLoading.setText(R.string.getProgrammes);
                }
            });
        }


        List<FavoriteChannel> favoriteChannels = database.selectAll(FavoriteChannel.class);

        if (favoriteChannels.isEmpty()) {
            for (String defaultChannelId : defaultFavoriteChannels) {
                FavoriteChannel favoriteChannel = new FavoriteChannel();
                favoriteChannel.setChannel(defaultChannelId);
                database.insert(favoriteChannel);
                favoriteChannels.add(favoriteChannel);
            }
        }

        Set<String> favoriteChannelIds = new HashSet<String>(favoriteChannels.size());
        for (FavoriteChannel favoriteChannel : favoriteChannels) {
            favoriteChannelIds.add(favoriteChannel.getChannel());
        }

        Set<String> programeIds = new HashSet<String>();

        List<Programme> programmesToInsert = new ArrayList<Programme>();

        int count = 0;
        int nbChaines = favoriteChannelIds.size();

        for (Channel channel : channels) {

            if (!favoriteChannelIds.contains(channel.getId())) {
                continue;
            }

            for (Programme programme : YboTvService.getInstance().getProgrammes(channel)) {

                if (!programeIds.contains(programme.getId())) {
                    programme.fillFields();
                    programmesToInsert.add(programme);
                    programeIds.add(programme.getId());
                }
            }

            count++;
            final int progress = 100 * count / (nbChaines + 2);
            if (handler != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loadingBar.setProgress(progress);
                    }
                });
            }
        }

        if (handler != null) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    messageLoading.setText(R.string.loadingChannels);
                }
            });
        }
        chrono.stop();

        chrono = new Chrono("Chaines>Suppression").start();
        // Suppression des anciennes chaînes.
        database.deleteAll(Channel.class);
        chrono.stop();

        chrono = new Chrono("Chaines>insertion").start();

        // insertion des nouvelles chaines
        try {
            database.beginTransaction();
            for (Channel channel : channels) {
                database.insert(channel);
            }
        } finally {
            database.endTransaction();
        }

        if (handler != null) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    messageLoading.setText(R.string.loadingProgrammes);
                }
            });
        }

        count++;
        final int progress = 100 * count / (nbChaines + 2);
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    loadingBar.setProgress(progress);
                }
            });
        }

        chrono.stop();

        chrono = new Chrono("Programme>Suppression").start();

        // Suppression de tout les programmes.
        database.deleteAll(Programme.class);
        chrono.stop();

        chrono = new Chrono("Programme>insertion").start();



        try {
            SQLiteDatabase db = database.getWritableDatabase();
            database.beginTransaction();
            DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, database.getBase().getTable(Programme.class).getName());

            int idCol = ih.getColumnIndex("id");
            int startCol = ih.getColumnIndex("start");
            int stopCol = ih.getColumnIndex("stop");
            int channelCol = ih.getColumnIndex("channel");
            int iconCol = ih.getColumnIndex("icon");
            int titleCol = ih.getColumnIndex("title");
            int descCol = ih.getColumnIndex("desc");
            int starRatingCol = ih.getColumnIndex("starRating");
            int csaRatingCol = ih.getColumnIndex("csaRating");
            int directorsCol = ih.getColumnIndex("directors");
            int actorsCol = ih.getColumnIndex("actors");
            int writersCol = ih.getColumnIndex("writers");
            int presentersCol = ih.getColumnIndex("presenters");
            int dateCol = ih.getColumnIndex("date");
            int categoriesCol = ih.getColumnIndex("categories");

            for (Programme programme : programmesToInsert) {
                ih.prepareForInsert();

                // Add the data for each column
                ih.bind(idCol, programme.getId());
                ih.bind(startCol, programme.getStart());
                ih.bind(stopCol, programme.getStop());
                ih.bind(channelCol, programme.getChannel());
                ih.bind(iconCol, programme.getIcon());
                ih.bind(titleCol, programme.getTitle());
                ih.bind(descCol, programme.getDesc());
                ih.bind(starRatingCol, programme.getStarRating());
                ih.bind(csaRatingCol, programme.getCsaRating());
                ih.bind(directorsCol, programme.getDirectorsInCsv());
                ih.bind(actorsCol, programme.getActorsInCsv());
                ih.bind(writersCol, programme.getWritersInCsv());
                ih.bind(presentersCol, programme.getPresentersInCsv());
                ih.bind(dateCol, programme.getDate());
                ih.bind(categoriesCol, programme.getCategoriesInCsv());

                ih.execute();
            }
        } finally {
            database.endTransaction();
        }

        chrono.stop();

        chrono = new Chrono("LastUpdate>update").start();
        database.deleteAll(LastUpdate.class);
        LastUpdate lastUpdate = new LastUpdate();
        lastUpdate.setLastUpdate(new Date());
        database.insert(lastUpdate);
        chrono.stop();
    }
}
