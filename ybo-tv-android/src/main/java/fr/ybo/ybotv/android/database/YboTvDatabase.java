package fr.ybo.ybotv.android.database;

import android.content.Context;
import fr.ybo.database.DataBaseException;
import fr.ybo.database.DataBaseHelper;
import fr.ybo.ybotv.android.modele.Channel;
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
    }};

    private static final String DB_NAME = "YBO_TV";
    private static final int DB_VERSION = 1;


    public YboTvDatabase(Context context) throws DataBaseException {
        super(context, databaseClasses, DB_NAME, DB_VERSION);
    }

    private static final Map<Integer, UpgradeDatabase> mapUpgrades = new HashMap<Integer, UpgradeDatabase>(){{

    }};

    @Override
    protected Map<Integer, UpgradeDatabase> getUpgrades() {
        return mapUpgrades;
    }
}
