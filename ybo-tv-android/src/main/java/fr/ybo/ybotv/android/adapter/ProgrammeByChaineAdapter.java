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
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.lasylist.ImageLoader;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;
import fr.ybo.ybotv.android.modele.Programme;

import java.util.List;

public class ProgrammeByChaineAdapter extends BaseAdapter {

    private final List<Programme> programmes;
    private final LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ProgrammeByChaineAdapter(Context context, List<Programme> programmes) {
        this.programmes = programmes;
        inflater = LayoutInflater.from(context);
        imageLoader=new ImageLoader(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return programmes.size();
    }

    @Override
    public Programme getItem(int position) {
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

        Programme currentProgramme = getItem(position);

        holder.horaires.setText(currentProgramme.getHoraires());
        holder.title.setText(currentProgramme.getTitle());

        if (currentProgramme.getIcon() != null && currentProgramme.getIcon().length() > 0) {
            holder.iconeChaine.setImageResource(R.drawable.loading);
            imageLoader.DisplayImage(currentProgramme.getIcon(), holder.iconeChaine);
            holder.iconeChaine.setVisibility(View.VISIBLE);
        } else {
            holder.iconeChaine.setVisibility(View.GONE);
        }

        return convertView;
    }
}
