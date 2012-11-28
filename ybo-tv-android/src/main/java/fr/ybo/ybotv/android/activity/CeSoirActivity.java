package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import fr.ybo.ybotv.android.R;
import fr.ybo.ybotv.android.adapter.ViewFlowAdapter;
import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

public class CeSoirActivity extends MenuManager.AbstractSimpleActivity {

    private ViewFlow viewFlow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cesoir);
        createMenu();

        viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        ViewFlowAdapter adapter = new ViewFlowAdapter(this);
        viewFlow.setAdapter(adapter);
        TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
        indicator.setTitleProvider(adapter);
        viewFlow.setFlowIndicator(indicator);
    }


    @Override
    public int getMenuIdOfClass() {
        return R.id.menu_cesoir;
    }
}

