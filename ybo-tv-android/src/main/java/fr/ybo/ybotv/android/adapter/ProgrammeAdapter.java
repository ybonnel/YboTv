package fr.ybo.ybotv.android.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.ybo.ybotv.android.exception.YboTvException;
import fr.ybo.ybotv.android.lasylist.ImageLoader;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;

import java.util.List;

public class ProgrammeAdapter extends BaseAdapter {

    private final List<ChannelWithProgramme> programmes;
    private final LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ProgrammeAdapter(Context context, List<ChannelWithProgramme> programmes) {
        this.programmes = programmes;
        inflater = LayoutInflater.from(context);
        imageLoader=new ImageLoader(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return programmes.size();
    }

    @Override
    public ChannelWithProgramme getItem(int position) {
        return programmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private static class ViewHolder {
        ImageView iconeChaine;
        TextView horaires;
        TextView title;
    }

    @Override
    public View getView(int position, View convertViewIn, ViewGroup parent) {
        View convertView = convertViewIn;
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.programme, null);
            holder = new ViewHolder();
            holder.horaires = (TextView) convertView.findViewById(R.id.programme_horaire);
            holder.title = (TextView) convertView.findViewById(R.id.programme_title);
            holder.iconeChaine = (ImageView) convertView.findViewById(R.id.programme_imageChaine);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChannelWithProgramme currentProgramme = getItem(position);

        holder.horaires.setText(currentProgramme.getProgramme().getHoraires());
        holder.title.setText(currentProgramme.getProgramme().getTitle());

        imageLoader.DisplayImage(currentProgramme.getChannel().getIconUrl(), holder.iconeChaine);

        return convertView;
    }
}
