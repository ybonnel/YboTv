package fr.ybo.ybotv.android.modele;

import fr.ybo.database.annotation.Column;
import fr.ybo.database.annotation.Entity;
import fr.ybo.database.annotation.Indexed;
import fr.ybo.database.annotation.PrimaryKey;

import java.io.Serializable;

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
}
