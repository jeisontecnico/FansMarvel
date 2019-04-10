package com.jepsolucoes.fansmarvel.view.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jepsolucoes.fansmarvel.R;
import com.jepsolucoes.fansmarvel.viewmodel.CharReceiver;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity {

    private MaterialSearchView searchView;
    public LinearLayout linearLayout;
    String pesquisa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchTextView);

        //Configura ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Fans Marvel");
        setSupportActionBar(toolbar);
        linearLayout = findViewById(R.id.linearMain);

        CharReceiver charReceiver = new CharReceiver(linearLayout);
        charReceiver.charReceiver("");

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                CharReceiver charReceiver = new CharReceiver(linearLayout);
                pesquisa = query;
                charReceiver.charReceiver(pesquisa);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    //inflate do SearchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menusearch);
        searchView.setMenuItem(item);

        return true;

    }




}
