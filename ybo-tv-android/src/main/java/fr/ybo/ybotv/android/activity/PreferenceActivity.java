package fr.ybo.ybotv.android.activity;


import android.os.Bundle;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.util.AdMobUtil;

public class PreferenceActivity extends SherlockPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        getSupportActionBar().setTitle(R.string.preference);
        addPreferencesFromResource(R.xml.preferences);

        AdMobUtil.manageAds(this);
    }
}
