package fr.ybo.ybotv.android.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.activity.ListProgrammeManager;
import fr.ybo.ybotv.android.modele.ChannelWithProgramme;
import org.taptwo.android.widget.TitleProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CeSoirViewFlowAdapter extends BaseAdapter implements TitleProvider {


    private LayoutInflater inflater;
    private Activity context;

    private int[] titles = {R.string.primeTime, R.string.partie2, R.string.finSoiree};


    public CeSoirViewFlowAdapter(Activity context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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

    private static class MyGetProgramme implements ListProgrammeManager.GetProgramme {

        private int position;
        private YboTvApplication application;

        private MyGetProgramme(int position, YboTvApplication application) {
            this.position = position;
            this.application = application;
        }

        @Override
        public List<ChannelWithProgramme> getProgrammes() {
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateToSelect;
            switch (position) {
                case 1:
                    // Deuxième partie
                    dateToSelect = simpleDateFormat.format(now) + "230000";
                    break;
                case 2:
                    // Fin de soirée
                    Calendar calendarTwomorrow = Calendar.getInstance();
                    calendarTwomorrow.add(Calendar.DAY_OF_MONTH, 1);
                    Date twomorrow = calendarTwomorrow.getTime();
                    if (calendarTwomorrow.get(Calendar.HOUR_OF_DAY) > 2) {
                        dateToSelect = simpleDateFormat.format(now) + "010000";
                    } else {
                        dateToSelect = simpleDateFormat.format(twomorrow) + "010000";
                    }
                    break;
                default:
                    // PrimeTime
                    dateToSelect = simpleDateFormat.format(now) + "210000";
                    break;
            }

            return ChannelWithProgramme.getProgrammesForDate(application, dateToSelect);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_ss_pub, null);
        }

        ListView listView = (ListView) convertView.findViewById(android.R.id.list);
        new ListProgrammeManager(listView, context, new MyGetProgramme(position, (YboTvApplication) context.getApplication())).constructAdapter();

        return convertView;
    }


    /* (non-Javadoc)
    * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
    */
    @Override
    public String getTitle(int position) {
        return context.getString(titles[position]);
    }
}
