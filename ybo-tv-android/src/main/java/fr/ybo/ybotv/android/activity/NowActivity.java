package fr.ybo.ybotv.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.modele.LastUpdate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NowActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((TextView)findViewById(R.id.hello)).setText(R.string.now);
        createMenu();

        setListAdapter(new ArrayAdapter<Channel>(this, android.R.layout.simple_list_item_1, channels));
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        registerForContextMenu(listView);

        YboTvDatabase database = ((YboTvApplication) getApplication()).getDatabase();
        LastUpdate lastUpdate = database.selectSingle(new LastUpdate());
        if (lastUpdate == null || mustUpdate(lastUpdate)) {
            startActivity(new Intent(this, LoadingActivity.class));
        } else {
            constructAdapter();
        }
    }

    private List<Channel> channels = new ArrayList<Channel>();

    private void constructAdapter() {
        channels.clear();
        channels.addAll(((YboTvApplication)getApplication()).getDatabase().selectAll(Channel.class));
        Log.d(TAG, "Taille channels : " + channels.size());
        ((ArrayAdapter)getListAdapter()).notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        constructAdapter();
    }

    private boolean mustUpdate(LastUpdate lastUpdate) {
        Date date = new Date();
        return ((date.getTime() - lastUpdate.getLastUpdate().getTime()) > TimeUnit.DAYS.toMinutes(2));
    }

    @Override
    protected int getMenuIdOfClass() {
        return R.id.menu_now;
    }
}

