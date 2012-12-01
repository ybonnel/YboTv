package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.adapter.CeSoirViewFlowAdapter;
import fr.ybo.ybotv.android.adapter.ParChaineViewFlowAdapter;
import fr.ybo.ybotv.android.util.AdMobUtil;
import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

public class ParChaineActivity extends MenuManager.AbstractSimpleActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow);
        createMenu();

        ViewFlow viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        ParChaineViewFlowAdapter adapter = new ParChaineViewFlowAdapter(this);
        viewFlow.setAdapter(adapter);
        TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
        indicator.setTitleProvider(adapter);
        viewFlow.setFlowIndicator(indicator);

        AdMobUtil.manageAds(this);
    }

    @Override
    public int getMenuIdOfClass() {
        return R.id.menu_parchaine;
    }
}

