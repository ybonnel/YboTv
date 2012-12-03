package fr.ybo.ybotv.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import fr.ybo.database.DataBaseException;
import fr.ybo.database.DataBaseHelper;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.modele.FavoriteChannel;
import fr.ybo.ybotv.android.modele.LastUpdate;
import fr.ybo.ybotv.android.modele.Programme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YboTvDatabase extends DataBaseHelper {

    private static final List<Class<?>> databaseClasses = new ArrayList<Class<?>>(){{
        add(Channel.class);
        add(Programme.class);
        add(LastUpdate.class);
        add(FavoriteChannel.class);
    }};

    private static final String DB_NAME = "YBO_TV";
    private static final int DB_VERSION = 2;


    public YboTvDatabase(Context context) throws DataBaseException {
        super(context, databaseClasses, DB_NAME, DB_VERSION);
    }

    private final Map<Integer, UpgradeDatabase> mapUpgrades = new HashMap<Integer, UpgradeDatabase>(){{
        put(2, new UpgradeDatabase() {
            @Override
            public void upgrade(SQLiteDatabase sqLiteDatabase) {
                getBase().dropDataBase(sqLiteDatabase);
                getBase().createDataBase(sqLiteDatabase);
            }
        });

    }};

    @Override
    protected Map<Integer, UpgradeDatabase> getUpgrades() {
        return mapUpgrades;
    }
}
