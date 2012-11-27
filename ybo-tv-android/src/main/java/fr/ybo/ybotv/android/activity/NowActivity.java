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
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;
import fr.ybo.ybotv.android.modele.LastUpdate;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class NowActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((TextView) findViewById(R.id.hello)).setText(R.string.now);
        createMenu();

        setListAdapter(new ArrayAdapter<ChannelWithProgramme>(this, android.R.layout.simple_list_item_1, channels));
        ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        registerForContextMenu(listView);

        YboTvDatabase database = ((YboTvApplication) getApplication()).getDatabase();
        LastUpdate lastUpdate = database.selectSingle(new LastUpdate());
        Log.d(TAG, "lastUpdate : " + lastUpdate);
        if (lastUpdate == null || mustUpdate(lastUpdate)) {
            startActivity(new Intent(this, LoadingActivity.class));
        } else {
            constructAdapter();
        }
    }

    private List<ChannelWithProgramme> channels = new ArrayList<ChannelWithProgramme>();

    private void constructAdapter() {

        List<ChannelWithProgramme> newChannels = ChannelWithProgramme.getCurrentProgrammes((YboTvApplication) getApplication());

        // Sort
        Collections.sort(newChannels, new Comparator<ChannelWithProgramme>() {
            @Override
            public int compare(ChannelWithProgramme channelWithProgramme, ChannelWithProgramme channelWithProgramme1) {
                Log.d(TAG, "Channel 1 : " + channelWithProgramme.getChannel().toString());
                Log.d(TAG, "Channel 2 : " + channelWithProgramme1.getChannel().toString());
                int id1 = Integer.parseInt(channelWithProgramme.getChannel().getId());
                int id2 = Integer.parseInt(channelWithProgramme1.getChannel().getId());
                if (id1 == id2) {
                    String start1 = channelWithProgramme.getProgramme().getStart();
                    String start2 = channelWithProgramme1.getProgramme().getStart();
                    return start1.compareTo(start2);
                }
                return (id1 < id2) ? -1 : 1;
            }
        });

        // Dédoublonnage
        Iterator<ChannelWithProgramme> iterator = newChannels.iterator();
        Set<String> channelsAlreadyIn = new HashSet<String>();
        while (iterator.hasNext()) {
            ChannelWithProgramme currentChannel = iterator.next();
            if (channelsAlreadyIn.contains(currentChannel.getChannel().getId())) {
                iterator.remove();
            } else {
                channelsAlreadyIn.add(currentChannel.getChannel().getId());
            }
        }

        channels.clear();
        channels.addAll(newChannels);
        Log.d(TAG, "Taille channels : " + channels.size());
        ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        constructAdapter();
    }

    private boolean mustUpdate(LastUpdate lastUpdate) {
        Date date = new Date();

        long timeSinceLastUpdate = date.getTime() - lastUpdate.getLastUpdate().getTime();
        long twoDays = TimeUnit.DAYS.toMillis(2);

        Log.d(TAG, "timeSinceLastUpdate : " + timeSinceLastUpdate);
        Log.d(TAG, "twoDays : " + twoDays);

        return (timeSinceLastUpdate > twoDays);
    }

    @Override
    protected int getMenuIdOfClass() {
        return R.id.menu_now;
    }
}

