package fr.ybo.ybotv.android.modele;


import fr.ybo.database.annotation.Column;
import fr.ybo.database.annotation.Entity;

import java.util.Date;

@Entity
public class LastUpdate {

    @Column(type = Column.TypeColumn.DATE)
    private Date lastUpdate;

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "LastUpdate{" +
                "lastUpdate=" + lastUpdate +
                '}';
    }
}
