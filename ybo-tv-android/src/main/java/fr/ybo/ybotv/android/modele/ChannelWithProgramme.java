package fr.ybo.ybotv.android.modele;

import android.database.Cursor;
import android.util.Log;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.database.YboTvDatabase;

import java.text.SimpleDateFormat;
import java.util.*;

public class ChannelWithProgramme {

    private Channel channel;
    private Programme programme;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    @Override
    public String toString() {
        return channel.getDisplayName() + " : " + programme.getTitle();
    }

    public static List<ChannelWithProgramme> getProgrammesForDate(YboTvApplication application, String date) {
        YboTvDatabase database = application.getDatabase();

        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("SELECT Channel.id as channelId, ");
        sqlQuery.append("Channel.displayName as channelDisplayName, ");
        sqlQuery.append("Channel.icon as channelIcon, ");
        sqlQuery.append("Programme.id as programmeId, ");
        sqlQuery.append("Programme.start as programmeStart, ");
        sqlQuery.append("Programme.stop as programmeStop, ");
        sqlQuery.append("Programme.icon as programmeIcon, ");
        sqlQuery.append("Programme.title as programmeTitle, ");
        sqlQuery.append("Programme.desc as programmeDesc, ");
        sqlQuery.append("Programme.starRating as programmeStarRating, ");
        sqlQuery.append("Programme.csaRating as programmeCsaRating, ");
        sqlQuery.append("Programme.directors as programmeDirectors, ");
        sqlQuery.append("Programme.actors as programmeActors, ");
        sqlQuery.append("Programme.writers as programmeWriters, ");
        sqlQuery.append("Programme.presenters as programmePresenters, ");
        sqlQuery.append("Programme.date as programmeDate, ");
        sqlQuery.append("Programme.categories as programmeCategories ");

        sqlQuery.append("FROM Channel, Programme, FavoriteChannel ");
        sqlQuery.append("WHERE ");
        sqlQuery.append("Channel.id = Programme.channel ");
        sqlQuery.append("AND Channel.id = FavoriteChannel.channel ");
        sqlQuery.append("AND Programme.start <= :currentDate ");
        sqlQuery.append("AND Programme.stop >= :currentDate ");

        List<String> selectionArgs = new ArrayList<String>(1);

        selectionArgs.add(date);

        long startTime = System.nanoTime();
        Cursor cursor = database.executeSelectQuery(sqlQuery.toString(), selectionArgs);

        int nbResult = cursor.getCount();
        long elapsedTime = System.nanoTime() - startTime;
        Log.d("YboTv", "Requete executee : " + sqlQuery.toString());
        Log.d("YboTv", "Nombre de resultas : " + nbResult + " en " + (elapsedTime / 1000) + "µs");

        List<ChannelWithProgramme> channels = new ArrayList<ChannelWithProgramme>();

        int channelIdCol = cursor.getColumnIndex("channelId");
        int channelDisplayNameCol = cursor.getColumnIndex("channelDisplayName");
        int channelIconCol = cursor.getColumnIndex("channelIcon");
        int programmeIdCol = cursor.getColumnIndex("programmeId");
        int programmeStartCol = cursor.getColumnIndex("programmeStart");
        int programmeStopCol = cursor.getColumnIndex("programmeStop");
        int programmeIconCol = cursor.getColumnIndex("programmeIcon");
        int programmeTitleCol = cursor.getColumnIndex("programmeTitle");
        int programmeDescCol = cursor.getColumnIndex("programmeDesc");
        int programmeStarRatingCol = cursor.getColumnIndex("programmeStarRating");
        int programmeCsaRatingCol = cursor.getColumnIndex("programmeCsaRating");
        int programmeDirectorsCol = cursor.getColumnIndex("programmeDirectors");
        int programmeActorsCol = cursor.getColumnIndex("programmeActors");
        int programmeWritersCol = cursor.getColumnIndex("programmeWriters");
        int programmePresentersCol = cursor.getColumnIndex("programmePresenters");
        int programmeDateCol = cursor.getColumnIndex("programmeDate");
        int programmeCategoriesCol = cursor.getColumnIndex("programmeCategories");

        while (cursor.moveToNext()) {
            ChannelWithProgramme oneChannelWithProgramme = new ChannelWithProgramme();

            Channel oneChannel = new Channel();
            oneChannel.setId(cursor.getString(channelIdCol));
            oneChannel.setDisplayName(cursor.getString(channelDisplayNameCol));
            oneChannel.setIcon(cursor.getString(channelIconCol));
            oneChannelWithProgramme.setChannel(oneChannel);

            Programme oneProgramme = new Programme();
            oneProgramme.setId(cursor.getString(programmeIdCol));
            oneProgramme.setStart(cursor.getString(programmeStartCol));
            oneProgramme.setStop(cursor.getString(programmeStopCol));
            oneProgramme.setIcon(cursor.getString(programmeIconCol));
            oneProgramme.setTitle(cursor.getString(programmeTitleCol));
            oneProgramme.setDesc(cursor.getString(programmeDescCol));
            oneProgramme.setStarRating(cursor.getString(programmeStarRatingCol));
            oneProgramme.setCsaRating(cursor.getString(programmeCsaRatingCol));
            oneProgramme.setChannel(oneChannel.getId());
            oneProgramme.fillDirectorsWithDb(cursor.getString(programmeDirectorsCol));
            oneProgramme.fillActorsWithDb(cursor.getString(programmeActorsCol));
            oneProgramme.fillWritersWithDb(cursor.getString(programmeWritersCol));
            oneProgramme.fillPresentersWithDb(cursor.getString(programmePresentersCol));
            oneProgramme.setDate(cursor.getString(programmeDateCol));
            oneProgramme.fillCategoriesWithDb(cursor.getString(programmeCategoriesCol));

            oneChannelWithProgramme.setProgramme(oneProgramme);

            channels.add(oneChannelWithProgramme);
        }

        cursor.close();

        Iterator<ChannelWithProgramme> iterator = channels.iterator();
        Set<String> channelsAlreadyIn = new HashSet<String>();
        while (iterator.hasNext()) {
            ChannelWithProgramme currentChannel = iterator.next();
            if (channelsAlreadyIn.contains(currentChannel.getChannel().getId())) {
                iterator.remove();
            } else {
                channelsAlreadyIn.add(currentChannel.getChannel().getId());
            }
        }

        return channels;
    }

    public static List<ChannelWithProgramme> getCurrentProgrammes(YboTvApplication application) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return getProgrammesForDate(application, dateFormat.format(new Date()));
    }
}
