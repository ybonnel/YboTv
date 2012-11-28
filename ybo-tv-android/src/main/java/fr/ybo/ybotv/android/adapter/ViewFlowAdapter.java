package fr.ybo.ybotv.android.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.ybo.ybotv.android.R;
import org.taptwo.android.widget.TitleProvider;

public class ViewFlowAdapter extends BaseAdapter implements TitleProvider {



    private LayoutInflater inflater;
    private Context context;

    private int[] titles = {R.string.primeTime, R.string.partie2, R.string.finSoiree};

    public ViewFlowAdapter(Context context) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list, null);
        }

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
