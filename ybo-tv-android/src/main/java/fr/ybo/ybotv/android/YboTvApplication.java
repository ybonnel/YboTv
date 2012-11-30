package fr.ybo.ybotv.android;


import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.receiver.AlarmReceiver;
import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

@ReportsCrashes(formKey = "dDBLNm5ZR2dLWFhyQTV0dDMtTDdFZVE6MQ")
public class YboTvApplication extends Application {


    public static final String TAG = "YboTv";

    private YboTvDatabase database;

    @Override
    public void onCreate() {
        ACRA.init(this);
        super.onCreate();
        database = new YboTvDatabase(this);
        /*StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());*/
    }

    public YboTvDatabase getDatabase() {
        return database;
    }

    private String versionInPref = null;

    public String getVersionInPref() {
        if (versionInPref == null) {
            SharedPreferences prefs = getSharedPreferences(TAG, 0);
            versionInPref = prefs.getString("ybo-tv.version", "0");
        }
        return versionInPref;
    }

    public void updateVersionInPref(String currentVersion) {
        SharedPreferences prefs = getSharedPreferences(TAG, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ybo-tv.version", currentVersion);
        editor.commit();
        versionInPref = currentVersion;
    }

    public void setRecurringAlarm() {
        Calendar updateTime = Calendar.getInstance();
        Intent alarm = new Intent(this, AlarmReceiver.class);
        PendingIntent recurringAlarm = PendingIntent.getBroadcast(this, 0, alarm, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP, updateTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, recurringAlarm);
    }

}
