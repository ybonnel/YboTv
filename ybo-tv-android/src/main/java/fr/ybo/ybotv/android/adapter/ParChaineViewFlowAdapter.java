package fr.ybo.ybotv.android.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.activity.ListProgrammeManager;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;
import org.taptwo.android.widget.TitleProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParChaineViewFlowAdapter extends BaseAdapter implements TitleProvider {


    private LayoutInflater inflater;
    private Activity context;

    private List<Channel> channels;

    public ParChaineViewFlowAdapter(Activity context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.channels = ((YboTvApplication) context.getApplication()).getDatabase().selectAll(Channel.class);
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Channel getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list, null);
        }

        ListView listView = (ListView) convertView.findViewById(android.R.id.list);

        // Todo gestion des programmes.

        return convertView;
    }


    /* (non-Javadoc)
    * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
    */
    @Override
    public String getTitle(int position) {
        return getItem(position).getDisplayName();
    }
}
