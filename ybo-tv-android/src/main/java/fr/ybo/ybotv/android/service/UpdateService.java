package fr.ybo.ybotv.android.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.modele.LastUpdate;
import fr.ybo.ybotv.android.util.TimeUnit;
import fr.ybo.ybotv.android.util.UpdateProgrammes;

import java.util.Date;

public class UpdateService extends Service  {

    public static final String ACTION_UPDATE = "fr.ybo.ybotv.android.action.UPDATE";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && ACTION_UPDATE.equals(intent.getAction())) {
            update();
        }
        return START_STICKY;
    }

    @SuppressWarnings("unchecked")
    private void update() {
        YboTvDatabase database = ((YboTvApplication)getApplication()).getDatabase();
        LastUpdate lastUpdate = database.selectSingle(new LastUpdate());
        if (lastUpdate == null || mustUpdate(lastUpdate)) {
            new UpdateProgrammes(null, null, null, null, database).execute();
        }
    }

    private boolean mustUpdate(LastUpdate lastUpdate) {
        Date date = new Date();

        long timeSinceLastUpdate = date.getTime() - lastUpdate.getLastUpdate().getTime();
        long twoDays = TimeUnit.DAYS.toMillis(2);

        return (timeSinceLastUpdate > twoDays);
    }
}
