package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.YboTvApplication;
import fr.ybo.ybotv.android.database.YboTvDatabase;
import fr.ybo.ybotv.android.exception.YboTvErreurReseau;
import fr.ybo.ybotv.android.modele.Channel;
import fr.ybo.ybotv.android.modele.LastUpdate;
import fr.ybo.ybotv.android.modele.Programme;
import fr.ybo.ybotv.android.service.YboTvService;
import fr.ybo.ybotv.android.util.TacheAvecGestionErreurReseau;

import java.util.*;

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
        messageLoading.setText(R.string.getChannels);
        new TacheAvecGestionErreurReseau(this) {

            @Override
            protected void myDoBackground() throws YboTvErreurReseau{
                // Récupération des chaines
                List<Channel> channels = YboTvService.getInstance().getChannels();

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        messageLoading.setText(R.string.getProgrammes);
                    }
                });

                Set<String> programeIds = new HashSet<String>();

                List<Programme> programmesToInsert = new ArrayList<Programme>();

                int count = 0;
                int nbChaines = channels.size();

                for (Channel channel : channels) {

                    for (Programme programme : YboTvService.getInstance().getProgrammes(channel)) {

                        if (!programeIds.contains(programme.getId())) {
                            programmesToInsert.add(programme);
                            programeIds.add(programme.getId());
                        }
                    }

                    count++;
                    final int progress = 100 * count / (nbChaines + 2);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadingBar.setProgress(progress);
                        }
                    });
                }


                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        messageLoading.setText(R.string.loadingChannels);
                    }
                });

                YboTvDatabase database = ((YboTvApplication) getApplication()).getDatabase();
                // Suppression des anciennes chaînes.
                database.deleteAll(Channel.class);

                // insertion des nouvelles chaines
                try {
                    database.beginTransaction();
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
                database.deleteAll(Programme.class);

                try {
                    database.beginTransaction();
                    for (Programme programme : programmesToInsert) {
                        database.insert(programme);
                    }
                } finally {
                    database.endTransaction();
                }

                database.deleteAll(LastUpdate.class);
                LastUpdate lastUpdate = new LastUpdate();
                lastUpdate.setLastUpdate(new Date());
                database.insert(lastUpdate);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
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
