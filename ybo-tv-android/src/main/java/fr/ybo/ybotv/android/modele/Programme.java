package fr.ybo.ybotv.android.modele;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
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
public class Programme implements Serializable, Parcelable {

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
    private String icon;
    @Column
    private String title;
    @Column
    private String desc;
    @Column
    private String starRating;
    @Column
    private String csaRating;


    private Map<String, String> ratings;

    public void fillCsaRating() {
        if (ratings != null && ratings.containsKey("CSA")) {
            csaRating = ratings.get("CSA");
        }
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getCsaRating() {
        return csaRating;
    }

    public void setCsaRating(String csaRating) {
        this.csaRating = csaRating;
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
        sqlQuery.append("Programme.icon as programmeIcon, ");
        sqlQuery.append("Programme.title as programmeTitle, ");
        sqlQuery.append("Programme.desc as programmeDesc, ");
        sqlQuery.append("Programme.starRating as programmeStarRating, ");
        sqlQuery.append("Programme.csaRating as programmeCsaRating ");

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
        int programmeIconCol = cursor.getColumnIndex("programmeIcon");
        int programmeTitleCol = cursor.getColumnIndex("programmeTitle");
        int programmeDescCol = cursor.getColumnIndex("programmeDesc");
        int programmeStarRatingCol = cursor.getColumnIndex("programmeStarRating");
        int programmeCsaRatingCol = cursor.getColumnIndex("programmeCsaRating");

        while (cursor.moveToNext()) {

            Programme oneProgramme = new Programme();
            oneProgramme.setId(cursor.getString(programmeIdCol));
            oneProgramme.setStart(cursor.getString(programmeStartCol));
            oneProgramme.setStop(cursor.getString(programmeStopCol));
            oneProgramme.setIcon(cursor.getString(programmeIconCol));
            oneProgramme.setTitle(cursor.getString(programmeTitleCol));
            oneProgramme.setDesc(cursor.getString(programmeDescCol));
            oneProgramme.setStarRating(cursor.getString(programmeStarRatingCol));
            oneProgramme.setCsaRating(cursor.getString(programmeCsaRatingCol));
            oneProgramme.setChannel(channel.getId());

            programmes.add(oneProgramme);
        }

        cursor.close();

        return programmes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(start);
        parcel.writeString(stop);
        parcel.writeString(channel);
        parcel.writeString(icon);
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(starRating);
        parcel.writeString(csaRating);
    }

    public Programme() {
    }

    public Programme(Parcel in) {
        id = in.readString();
        start = in.readString();
        stop = in.readString();
        channel = in.readString();
        icon = in.readString();
        title = in.readString();
        desc = in.readString();
        starRating = in.readString();
        csaRating = in.readString();
    }

    public static final Creator<Programme> CREATOR = new Creator<Programme>() {
        @Override
        public Programme createFromParcel(Parcel parcel) {
            return new Programme(parcel);
        }

        @Override
        public Programme[] newArray(int size) {
            return new Programme[size];
        }
    };
}
