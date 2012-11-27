package fr.ybo.ybotv.android.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.webimageloader.ImageLoader;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;

import java.util.List;

public class ProgrammeAdapter extends BaseAdapter {

    private final List<ChannelWithProgramme> programmes;
    private final Context context;
    private final LayoutInflater inflater;

    public ProgrammeAdapter(Context context, List<ChannelWithProgramme> programmes) {
        this.context = context;
        this.programmes = programmes;
        inflater = LayoutInflater.from(context);
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChannelWithProgramme currentProgramme = getItem(position);

        holder.horaires.setText(currentProgramme.getProgramme().getHoraires());
        holder.title.setText(currentProgramme.getProgramme().getTitle());
        ImageLoader loader = YboTvApplication.getLoader(context);
        holder.iconeChaine = (ImageView) convertView.findViewById(R.id.programme_imageChaine);
        loader.load(holder.iconeChaine, currentProgramme.getChannel().getIconUrl(), new ImageLoader.Listener<ImageView>() {
            @Override
            public void onSuccess(ImageView tag, Bitmap b) {
                tag.setImageBitmap(b);
            }

            @Override
            public void onError(ImageView tag, Throwable t) {
                Log.e("YboTv", "Erreur durant le chargement de l'image");
                Log.e("YboTv", Log.getStackTraceString(t));
            }
        });

        return convertView;
    }
}
