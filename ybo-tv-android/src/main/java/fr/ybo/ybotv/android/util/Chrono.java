package fr.ybo.ybotv.android.util;


import android.util.Log;
import fr.ybo.ybotv.android.YboTvApplication;

public class Chrono {

    private String name;

    public Chrono(String name) {
        this.name = name;
    }

    private long startTime;

    public Chrono start() {
        startTime = System.nanoTime();
        return this;
    }

    public void stop() {
        long elapsedTime = (System.nanoTime() - startTime) / 1000 / 1000;
        Log.d(YboTvApplication.TAG, name + " : " + elapsedTime);
    }


}
