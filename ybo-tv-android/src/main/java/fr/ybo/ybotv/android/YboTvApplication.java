package fr.ybo.ybotv.android;


import android.app.Application;
import fr.ybo.ybotv.android.database.YboTvDatabase;

public class YboTvApplication extends Application {

    private YboTvDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = new YboTvDatabase(this);
    }

    public YboTvDatabase getDatabase() {
        return database;
    }
}
