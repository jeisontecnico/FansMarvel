package com.jepsolucoes.fansmarvel.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jepsolucoes.fansmarvel.R;
import com.jepsolucoes.fansmarvel.model.Characters;
import com.jepsolucoes.fansmarvel.model.api.RetrofitConfig;
import com.jepsolucoes.fansmarvel.viewmodel.CharReceiver;
import com.jepsolucoes.fansmarvel.viewmodel.SearchChars;
import com.jepsolucoes.fansmarvel.viewmodel.adapter.AdapterChars;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerLista;
    private AdapterChars adapterChars;
    private List<Characters> listChars = new ArrayList<>();
    private MaterialSearchView searchView;
    private Button buttonAtualiza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchTextView);
        buttonAtualiza = findViewById(R.id.buttonAtualizar);



        buttonAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharReceiver charReceiver = new CharReceiver();
                charReceiver.charReceiver();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Fans Marvel");
        setSupportActionBar(toolbar);


        //Recebe dados do servidor


        //Recebe dados para pesquisa
        SearchChars searchChars = new SearchChars(searchView);

        adapterChars= new AdapterChars(listChars,this);
        recyclerLista = findViewById(R.id.recyclerLista);
        recyclerLista.setHasFixedSize(true);
        recyclerLista.setLayoutManager(new LinearLayoutManager(this));
        recyclerLista.setAdapter(adapterChars);

    }


    //inflate do SearchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menusearch);
        searchView.setMenuItem( item );
        return true;
    }

}
