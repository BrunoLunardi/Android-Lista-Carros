package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import br.com.livroandroid.carros.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Cria a Toolbar
        setUpToolbar();
        //Cria o menu NavDrawer
        setupNavDrawer();
    }
}
