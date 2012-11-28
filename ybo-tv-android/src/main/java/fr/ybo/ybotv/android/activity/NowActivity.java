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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        createMenu();

        ListProgrammeManager listProgrammeManager = new ListProgrammeManager(getListView(), this, this);
        listProgrammeManager.constructAdapter();
    }


    public List<ChannelWithProgramme> getProgrammes() {
        return ChannelWithProgramme.getCurrentProgrammes((YboTvApplication) getApplication());
    }

    @Override
    public int getMenuIdOfClass() {
        return R.id.menu_now;
    }
}

