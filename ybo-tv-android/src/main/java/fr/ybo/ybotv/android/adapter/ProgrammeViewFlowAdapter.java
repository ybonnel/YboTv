package fr.ybo.ybotv.android.adapter;


import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.lasylist.ImageLoader;
import fr.ybo.ybotv.android.modele.Programme;
import org.taptwo.android.widget.TitleProvider;

import java.util.HashMap;
import java.util.Map;

public class ProgrammeViewFlowAdapter extends BaseAdapter implements TitleProvider {


    private LayoutInflater inflater;
    private Activity context;
    private Programme programme;

    private int[] titles = {R.string.resume, R.string.detail};


    public ProgrammeViewFlowAdapter(Activity context, Programme programme) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.programme = programme;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        switch (position) {
            case 0:
                convertView = getResumeView();
                break;
            case 1:
                convertView = getDetailView();
                break;
        }

        return convertView;
    }

    private View getDetailView() {
        View view = inflater.inflate(R.layout.programme_detail, null);

        TextView categories = (TextView) view.findViewById(R.id.programme_detail_categories);
        TextView date = (TextView) view.findViewById(R.id.programme_detail_date);
        TextView credits = (TextView) view.findViewById(R.id.programme_detail_credits);

        if (programme.getCategories().isEmpty()) {
            categories.setVisibility(View.GONE);
        } else {
            categories.setVisibility(View.VISIBLE);
            String categorie = null;
            for (String oneCategorie : programme.getCategories()) {
                categorie = oneCategorie;
            }
            categories.setText(categorie);
        }

        if (programme.getDate() == null) {
            date.setVisibility(View.GONE);
        } else {
            date.setVisibility(View.VISIBLE);
            date.setText(context.getString(R.string.date, programme.getDate()));
        }

        StringBuilder builderCredits = new StringBuilder();

        for (String director : programme.getDirectors()) {
            builderCredits.append(context.getString(R.string.director, director));
            builderCredits.append('\n');
        }
        for (String actor : programme.getActors()) {
            builderCredits.append(context.getString(R.string.actor, actor));
            builderCredits.append('\n');
        }
        for (String writer : programme.getWriters()) {
            builderCredits.append(context.getString(R.string.writer, writer));
            builderCredits.append('\n');
        }
        for (String presenter : programme.getPresenters()) {
            builderCredits.append(context.getString(R.string.presenter, presenter));
            builderCredits.append('\n');
        }

        credits.setText(builderCredits.toString());
        credits.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

    private final static Map<String, Integer> mapOfRatings = new HashMap<String, Integer>(){{
        put("1/4", R.drawable.rating_1star);
        put("2/4", R.drawable.rating_2star);
        put("3/4", R.drawable.rating_3star);
        put("4/4", R.drawable.rating_4star);
    }};

    private final static Map<String, Integer> mapOfCsaRatings = new HashMap<String, Integer>(){{
        put("-18", R.drawable.moins18);
        put("-16", R.drawable.moins16);
        put("-12", R.drawable.moins12);
        put("-10", R.drawable.moins10);
    }};

    private View getResumeView() {
        View view = inflater.inflate(R.layout.programme_resume, null);
        ImageLoader imageLoader=new ImageLoader(context.getApplicationContext());

        ImageView icon = (ImageView) view.findViewById(R.id.programme_activity_icon);
        ImageView rating = (ImageView) view.findViewById(R.id.programme_activity_rating);
        ImageView csaRating = (ImageView) view.findViewById(R.id.programme_activity_csa_rating);
        TextView description = (TextView) view.findViewById(R.id.programme_activity_description);

        if (programme.getIcon() != null && programme.getIcon().length() > 0) {
            imageLoader.DisplayImage(programme.getIcon(), icon);
            icon.setVisibility(View.VISIBLE);
        } else {
            icon.setVisibility(View.GONE);
        }
        if (programme.getStarRating() != null
                && mapOfRatings.containsKey(programme.getStarRating())) {
            rating.setImageResource(mapOfRatings.get(programme.getStarRating()));
            rating.setVisibility(View.VISIBLE);
        } else {
            rating.setVisibility(View.GONE);
        }
        Log.d(YboTvApplication.TAG, "CsaRating : " + programme.getCsaRating());
        if (programme.getCsaRating() != null
                && mapOfCsaRatings.containsKey(programme.getCsaRating())) {
            csaRating.setImageResource(mapOfCsaRatings.get(programme.getCsaRating()));
            csaRating.setVisibility(View.VISIBLE);
        } else {
            csaRating.setVisibility(View.GONE);
        }
        if (programme.getDesc() != null) {
            description.setText(programme.getDesc());
            description.setVisibility(View.VISIBLE);
        } else {
            description.setVisibility(View.GONE);
        }

        description.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }


    /* (non-Javadoc)
    * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
    */
    @Override
    public String getTitle(int position) {
        return context.getString(titles[position]);
    }
}
