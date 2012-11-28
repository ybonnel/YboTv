package fr.ybo.ybotv.android.activity;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.adapter.ProgrammeAdapter;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListProgrammeManager {

    public interface GetProgramme {
        List<ChannelWithProgramme> getProgrammes();
    }

    private final ListView listView;
    private final Context context;
    private final GetProgramme getProgramme;
    private final ProgrammeAdapter adapter;
    private List<ChannelWithProgramme> channels = new ArrayList<ChannelWithProgramme>();

    public ListProgrammeManager(ListView listView, Activity context, GetProgramme getProgramme) {
        this.listView = listView;
        this.context = context;
        this.getProgramme = getProgramme;
        this.adapter = new ProgrammeAdapter(context, channels);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        context.registerForContextMenu(listView);
    }

    public void constructAdapter() {

        List<ChannelWithProgramme> newChannels = getProgramme.getProgrammes();

        // Sort
        Collections.sort(newChannels, new Comparator<ChannelWithProgramme>() {
            @Override
            public int compare(ChannelWithProgramme channelWithProgramme, ChannelWithProgramme channelWithProgramme1) {
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


        channels.clear();
        channels.addAll(newChannels);
        Log.d(YboTvApplication.TAG, "Taille channels : " + channels.size());
        adapter.notifyDataSetChanged();

    }
}
