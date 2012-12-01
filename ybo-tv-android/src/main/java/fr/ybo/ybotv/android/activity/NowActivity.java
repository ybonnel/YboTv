package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;
import fr.ybo.ybotv.android.util.AdMobUtil;

import java.util.List;

public class NowActivity extends MenuManager.AbstractListActivity implements ListProgrammeManager.GetProgramme {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        createMenu();

        ListProgrammeManager listProgrammeManager = new ListProgrammeManager(getListView(), this, this);
        listProgrammeManager.constructAdapter();

        AdMobUtil.manageAds(this);
    }


    public List<ChannelWithProgramme> getProgrammes() {
        return ChannelWithProgramme.getCurrentProgrammes((YboTvApplication) getApplication());
    }

    @Override
    public int getMenuIdOfClass() {
        return R.id.menu_now;
    }
}

