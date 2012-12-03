package fr.ybo.ybotv.android.modele;

import fr.ybo.database.annotation.Column;
import fr.ybo.database.annotation.Entity;
import fr.ybo.database.annotation.PrimaryKey;

@Entity
public class FavoriteChannel {
    @Column
    @PrimaryKey
    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
