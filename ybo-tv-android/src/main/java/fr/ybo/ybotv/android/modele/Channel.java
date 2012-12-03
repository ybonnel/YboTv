package fr.ybo.ybotv.android.modele;

import fr.ybo.database.annotation.Column;
import fr.ybo.database.annotation.Entity;
import fr.ybo.database.annotation.PrimaryKey;
import fr.ybo.ybotv.android.service.YboTvService;

import java.io.Serializable;

@Entity
public class Channel implements Serializable, Comparable<Channel> {

    @Column
    @PrimaryKey
    private String id;
    @Column
    private String displayName;
    @Column
    private String icon;

    private final static String BASE_URL = YboTvService.SERVEUR + "logos/";

    public String getIconUrl() {
        return BASE_URL + icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }


    @Override
    public int compareTo(Channel other) {
        int id1 = Integer.parseInt(getId());
        int id2 = Integer.parseInt(other.getId());
        return (id1 == id2) ? 0 : (id1 < id2) ? -1 : 1;
    }
}
