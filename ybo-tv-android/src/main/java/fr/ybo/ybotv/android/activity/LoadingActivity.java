package fr.ybo.ybotv.android.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.service.YboTvService;

public class LoadingActivity extends SherlockActivity {

    private TextView messageLoading;
    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        messageLoading = (TextView) findViewById(R.id.messageLoading);
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);

        getSupportActionBar().setTitle(R.string.loading);

        loadDatas();
    }

    private void loadDatas() {
        loadChannel();
    }


    @SuppressWarnings("unchecked")
    private void loadChannel() {
        messageLoading.setText(R.string.loadingChannels);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                YboTvDatabase database = ((YboTvApplication)getApplication()).getDatabase();
                // Suppression des anciennes chaînes.
                database.deleteAll(Channel.class);

                // insertion des nouvelles chaines
                database.beginTransaction();
                for (Channel channel : YboTvService.getInstance().getChannels()) {
                    database.insert(channel);
                }
                database.endTransaction();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                finish();
            }
        }.execute();
    }


    // Verrue pour faire marcher en 1.6.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((!(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.DONUT)
                && keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        // On ne fait rien.
    }
}
