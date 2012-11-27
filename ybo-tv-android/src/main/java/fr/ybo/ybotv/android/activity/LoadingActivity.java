package fr.ybo.ybotv.android.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.modele.LastUpdate;
import fr.ybo.ybotv.android.modele.Programme;
import fr.ybo.ybotv.android.service.YboTvService;

import java.util.Date;
import java.util.List;

public class LoadingActivity extends SherlockActivity {

    private TextView messageLoading;
    private ProgressBar loadingBar;

    private Handler handler = new Handler();

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
                YboTvDatabase database = ((YboTvApplication) getApplication()).getDatabase();
                // Suppression des anciennes chaînes.
                database.deleteAll(Channel.class);

                // insertion des nouvelles chaines
                List<Channel> channels = null;
                try {
                    database.beginTransaction();
                    channels = YboTvService.getInstance().getChannels();
                    for (Channel channel : channels) {
                        database.insert(channel);
                    }
                } finally {
                    database.endTransaction();
                }

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        messageLoading.setText(R.string.loadingProgrammes);
                    }
                });

                // Suppression de tout les programmes.
                int count = 0;
                database.deleteAll(Programme.class);


                for (Channel channel : channels) {

                    try {
                        database.beginTransaction();
                        for (Programme programme : YboTvService.getInstance().getProgrammes(channel)) {
                            database.insert(programme);
                        }
                    } finally {
                        database.endTransaction();
                    }

                    count++;
                    final int progress = 100 * count / channels.size();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadingBar.setProgress(progress);
                        }
                    });
                }

                database.deleteAll(LastUpdate.class);
                LastUpdate lastUpdate = new LastUpdate();
                lastUpdate.setLastUpdate(new Date());
                database.insert(lastUpdate);

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
