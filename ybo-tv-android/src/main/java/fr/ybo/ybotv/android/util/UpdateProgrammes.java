package fr.ybo.ybotv.android.util;

import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.exception.YboTvErreurReseau;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.modele.LastUpdate;
import fr.ybo.ybotv.android.modele.Programme;
import fr.ybo.ybotv.android.service.YboTvService;

import java.util.*;


public class UpdateProgrammes extends TacheAvecGestionErreurReseau {

    private Handler handler;
    private ProgressBar loadingBar;
    private TextView messageLoading;
    private YboTvDatabase database;

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
        List<Channel> channels = YboTvService.getInstance().getChannels();

        if (handler != null) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    messageLoading.setText(R.string.getProgrammes);
                }
            });
        }

        Set<String> programeIds = new HashSet<String>();

        List<Programme> programmesToInsert = new ArrayList<Programme>();

        int count = 0;
        int nbChaines = channels.size();

        for (Channel channel : channels) {

            for (Programme programme : YboTvService.getInstance().getProgrammes(channel)) {

                if (!programeIds.contains(programme.getId())) {
                    programme.fillCsaRating();
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
        // Suppression des anciennes chaînes.
        database.deleteAll(Channel.class);

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

        // Suppression de tout les programmes.
        database.deleteAll(Programme.class);

        try {
            database.beginTransaction();
            for (Programme programme : programmesToInsert) {
                database.insert(programme);
            }
        } finally {
            database.endTransaction();
        }

        database.deleteAll(LastUpdate.class);
        LastUpdate lastUpdate = new LastUpdate();
        lastUpdate.setLastUpdate(new Date());
        database.insert(lastUpdate);
    }
}
