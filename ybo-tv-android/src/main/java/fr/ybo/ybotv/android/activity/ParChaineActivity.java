package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import android.widget.TextView;
import fr.ybo.ybotv.android.R;

public class ParChaineActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((TextView)findViewById(R.id.hello)).setText(R.string.parchaine);
        createMenu();
    }

    @Override
    protected int getMenuIdOfClass() {
        return R.id.menu_parchaine;
    }
}

