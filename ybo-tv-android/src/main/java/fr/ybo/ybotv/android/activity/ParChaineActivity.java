package fr.ybo.ybotv.android.activity;

import android.os.Bundle;
import fr.ybo.ybotv.android.R;

public class ParChaineActivity extends MenuManager.AbstractListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        createMenu();
    }

    @Override
    public int getMenuIdOfClass() {
        return R.id.menu_parchaine;
    }
}

