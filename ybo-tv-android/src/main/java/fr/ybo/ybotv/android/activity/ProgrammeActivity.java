package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.google.analytics.tracking.android.EasyTracker;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.adapter.ProgrammeViewFlowAdapter;
import fr.ybo.ybotv.android.modele.Programme;
import fr.ybo.ybotv.android.util.AdMobUtil;
import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

public class ProgrammeActivity extends SherlockActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow);
        Programme programme = getIntent().getParcelableExtra("programme");
        getSupportActionBar().setTitle(programme.getTitle());

        ViewFlow viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        ProgrammeViewFlowAdapter adapter = new ProgrammeViewFlowAdapter(this, programme);
        viewFlow.setAdapter(adapter);
        TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
        indicator.setTitleProvider(adapter);
        viewFlow.setFlowIndicator(indicator);

        AdMobUtil.manageAds(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance().activityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance().activityStop(this);
    }

}

