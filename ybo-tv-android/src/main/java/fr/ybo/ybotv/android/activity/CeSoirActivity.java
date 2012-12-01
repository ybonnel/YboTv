package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.adapter.CeSoirViewFlowAdapter;
import fr.ybo.ybotv.android.util.AdMobUtil;
import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

public class CeSoirActivity extends MenuManager.AbstractSimpleActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow);
        createMenu();

        ViewFlow viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        CeSoirViewFlowAdapter adapter = new CeSoirViewFlowAdapter(this);
        viewFlow.setAdapter(adapter);
        TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
        indicator.setTitleProvider(adapter);
        viewFlow.setFlowIndicator(indicator);

        AdMobUtil.manageAds(this);
    }


    @Override
    public int getMenuIdOfClass() {
        return R.id.menu_cesoir;
    }
}

