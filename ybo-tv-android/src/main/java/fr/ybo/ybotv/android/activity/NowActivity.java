package fr.ybo.ybotv.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.adapter.ProgrammeAdapter;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;
import fr.ybo.ybotv.android.modele.LastUpdate;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class NowActivity extends MenuManager.AbstractListActivity implements ListProgrammeManager.GetProgramme {


    private ListProgrammeManager listProgrammeManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        createMenu();

        listProgrammeManager = new ListProgrammeManager(getListView(), this, this);

        YboTvDatabase database = ((YboTvApplication) getApplication()).getDatabase();
        LastUpdate lastUpdate = database.selectSingle(new LastUpdate());
        Log.d(YboTvApplication.TAG, "lastUpdate : " + lastUpdate);
        if (lastUpdate == null || mustUpdate(lastUpdate)) {
            startActivity(new Intent(this, LoadingActivity.class));
        } else {
            listProgrammeManager.constructAdapter();
        }
    }


    public List<ChannelWithProgramme> getProgrammes() {
        return ChannelWithProgramme.getCurrentProgrammes((YboTvApplication) getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(YboTvApplication.TAG, "onResume");
        listProgrammeManager.constructAdapter();
    }

    private boolean mustUpdate(LastUpdate lastUpdate) {
        Date date = new Date();

        long timeSinceLastUpdate = date.getTime() - lastUpdate.getLastUpdate().getTime();
        long twoDays = TimeUnit.DAYS.toMillis(2);

        Log.d(YboTvApplication.TAG, "timeSinceLastUpdate : " + timeSinceLastUpdate);
        Log.d(YboTvApplication.TAG, "twoDays : " + twoDays);

        return (timeSinceLastUpdate > twoDays);
    }

    @Override
    public int getMenuIdOfClass() {
        return R.id.menu_now;
    }
}

