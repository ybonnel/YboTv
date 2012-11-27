package fr.ybo.ybotv.android.modele;

import fr.ybo.database.annotation.Column;
import fr.ybo.database.annotation.Entity;
import fr.ybo.database.annotation.PrimaryKey;

import java.io.Serializable;

@Entity
public class Channel implements Serializable {

    @Column
    @PrimaryKey
    private String id;
    @Column
    private String displayName;
    @Column
    private String icon;

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
}
