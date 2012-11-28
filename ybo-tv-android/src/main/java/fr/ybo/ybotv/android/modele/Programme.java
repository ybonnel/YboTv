package fr.ybo.ybotv.android.modele;

import android.database.Cursor;
import android.util.Log;
import fr.ybo.database.annotation.Column;
import fr.ybo.database.annotation.Entity;
import fr.ybo.database.annotation.Indexed;
import fr.ybo.database.annotation.PrimaryKey;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.database.YboTvDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Programme implements Serializable {

    @Column
    @PrimaryKey
    private String id;
    @Column
    @Indexed
    private String start;
    @Column
    @Indexed
    private String stop;
    @Column
    @Indexed
    private String channel;
    @Column
    private String date;
    @Column
    private String icon;
    @Column
    private String title;
    @Column
    private String subTitle;
    @Column
    private String desc;
    @Column
    private String episodeNum;
    @Column
    private String starRating;

    public String getHoraires() {

        StringBuilder builder = new StringBuilder();
        builder.append(start.substring(8, 10));
        builder.append(':');
        builder.append(start.substring(10,12));
        builder.append(" - ");
        builder.append(stop.substring(8,10));
        builder.append(':');
        builder.append(stop.substring(10,12));

        return builder.toString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(String episodeNum) {
        this.episodeNum = episodeNum;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    @Override
    public String toString() {
        return "Programme{" +
                "id='" + id + '\'' +
                ", start='" + start + '\'' +
                ", stop='" + stop + '\'' +
                ", channel='" + channel + '\'' +
                ", title='" + title + '\'' +
                '}';
    }


    public static List<Programme> getProgrammes(YboTvApplication application, Channel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendarToday = Calendar.getInstance();
        Calendar calendarYesterday = Calendar.getInstance();
        calendarYesterday.add(Calendar.DAY_OF_MONTH, -1);
        Calendar calendarTwomorrow = Calendar.getInstance();
        calendarTwomorrow.add(Calendar.DAY_OF_MONTH, 1);

        Date today = calendarToday.getTime();
        Date yesterday  = calendarYesterday.getTime();
        Date twomorrow = calendarTwomorrow.getTime();

        String dateDebut;
        String dateFin;

        if (calendarToday.get(Calendar.HOUR_OF_DAY) < 3) {
            dateDebut = sdf.format(yesterday) + "030000";
            dateFin = sdf.format(today) + "030000";
        } else {
            dateDebut = sdf.format(today) + "030000";
            dateFin = sdf.format(twomorrow) + "030000";
        }


        YboTvDatabase database = application.getDatabase();

        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("SELECT ");
        sqlQuery.append("Programme.id as programmeId, ");
        sqlQuery.append("Programme.start as programmeStart, ");
        sqlQuery.append("Programme.stop as programmeStop, ");
        sqlQuery.append("Programme.date as programmeDate, ");
        sqlQuery.append("Programme.icon as programmeIcon, ");
        sqlQuery.append("Programme.title as programmeTitle, ");
        sqlQuery.append("Programme.subTitle as programmeSubTitle, ");
        sqlQuery.append("Programme.desc as programmeDesc, ");
        sqlQuery.append("Programme.episodeNum as programmeEpisodeNum, ");
        sqlQuery.append("Programme.starRating as programmeStarRating ");

        sqlQuery.append("FROM Programme ");
        sqlQuery.append("WHERE ");
        sqlQuery.append("Programme.channel = :channel ");
        sqlQuery.append("AND Programme.stop >= :beginDate ");
        sqlQuery.append("AND Programme.start <= :endDate ");
        sqlQuery.append("ORDER BY Programme.start ");

        List<String> selectionArgs = new ArrayList<String>(3);

        selectionArgs.add(channel.getId());
        selectionArgs.add(dateDebut);
        selectionArgs.add(dateFin);

        long startTime = System.nanoTime();
        Cursor cursor = database.executeSelectQuery(sqlQuery.toString(), selectionArgs);

        int nbResult = cursor.getCount();
        long elapsedTime = System.nanoTime() - startTime;
        Log.d("YboTv", "Requete executee : " + sqlQuery.toString());
        Log.d("YboTv", "Nombre de resultas : " + nbResult + " en " + (elapsedTime / 1000) + "µs");

        List<Programme> programmes = new ArrayList<Programme>();

        int programmeIdCol = cursor.getColumnIndex("programmeId");
        int programmeStartCol = cursor.getColumnIndex("programmeStart");
        int programmeStopCol = cursor.getColumnIndex("programmeStop");
        int programmeDateCol = cursor.getColumnIndex("programmeDate");
        int programmeIconCol = cursor.getColumnIndex("programmeIcon");
        int programmeTitleCol = cursor.getColumnIndex("programmeTitle");
        int programmeSubTitleCol = cursor.getColumnIndex("programmeSubTitle");
        int programmeDescCol = cursor.getColumnIndex("programmeDesc");
        int programmeEpisodeNumCol = cursor.getColumnIndex("programmeEpisodeNum");
        int programmeStarRatingCol = cursor.getColumnIndex("programmeStarRating");

        while (cursor.moveToNext()) {

            Programme oneProgramme = new Programme();
            oneProgramme.setId(cursor.getString(programmeIdCol));
            oneProgramme.setStart(cursor.getString(programmeStartCol));
            oneProgramme.setStop(cursor.getString(programmeStopCol));
            oneProgramme.setDate(cursor.getString(programmeDateCol));
            oneProgramme.setIcon(cursor.getString(programmeIconCol));
            oneProgramme.setTitle(cursor.getString(programmeTitleCol));
            oneProgramme.setSubTitle(cursor.getString(programmeSubTitleCol));
            oneProgramme.setDesc(cursor.getString(programmeDescCol));
            oneProgramme.setEpisodeNum(cursor.getString(programmeEpisodeNumCol));
            oneProgramme.setStarRating(cursor.getString(programmeStarRatingCol));
            oneProgramme.setChannel(channel.getId());

            programmes.add(oneProgramme);
        }

        cursor.close();

        return programmes;
    }
}
