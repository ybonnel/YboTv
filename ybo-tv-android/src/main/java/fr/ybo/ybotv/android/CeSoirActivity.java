package fr.ybo.ybotv.android;

import android.os.Bundle;
import android.widget.TextView;

public class CeSoirActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((TextView)findViewById(R.id.hello)).setText(R.string.cesoir);
        createMenu();
    }


    @Override
    protected int getMenuIdOfClass() {
        return R.id.menu_cesoir;
    }
}

