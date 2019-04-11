package com.jepsolucoes.fansmarvel.view.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import com.jepsolucoes.fansmarvel.R;
import com.jepsolucoes.fansmarvel.contract.MainActivityContract;
import com.jepsolucoes.fansmarvel.model.AdapterInterface;
import com.jepsolucoes.fansmarvel.model.Results;
import com.jepsolucoes.fansmarvel.model.listener.EndlessRecyclerViewScrollListener;
import com.jepsolucoes.fansmarvel.presenter.CharReceiverPresenter;
import com.jepsolucoes.fansmarvel.view.Activities.adapter.AdapterChars;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterInterface, MainActivityContract.View {

    private MaterialSearchView searchView;
    public LinearLayout linearLayout;
    private AdapterChars adapter;
    private RecyclerView recycler;
    private MainActivityContract.Presenter presenter = new CharReceiverPresenter();
    private List<Results> listMainChars = new ArrayList<>();
    private List<Results> listVazia = new ArrayList<>();
    private RadioGroup radioGroup;
    private EndlessRecyclerViewScrollListener scrollListener;
    public String opcao = "name";
    public int offset;
    public ProgressBar progressCharList;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchTextView);

        //Configura ToolBar
        configToobar();

        // inicializa as Views
        initViews();

        // chamada de api
        callApiSearch("", "", 0);

        // chama api passando o texto digitado
        searchQuery();

        // chama listener dos Radio Buttons
        checkRadio();


        presenter.attachView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void searchQuery() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.deleteItems();
                listMainChars = listVazia;
                callApiSearch(query, "", 0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void callApiSearch(String search, String radio, int offset) {
        presenter.charReceiver(search, radio, offset);
    }

    private void configToobar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("          Marvel Characters");
        setSupportActionBar(toolbar);
    }

    private void initViews() {
        adapter = new AdapterChars(  this);
        linearLayout = findViewById(R.id.linearMain);
        recycler = findViewById(R.id.recyclerLista);
        radioGroup = findViewById(R.id.radioGroup);
        progressCharList = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void configuraRecyclerView(final List<Results> list) {

        adapter.addItens(list);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    offset += 20;
                    callApiSearch("", opcao, offset);
            }
        };
        recycler.addOnScrollListener(scrollListener);
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

    @Override
    public void onItemClickListener(Results results) {

        String name = results.getName();
        String description = results.getDescription();
        String path = results.thumbnail.getPath();
        String extension = results.thumbnail.getExtension();
        Intent i = new Intent(MainActivity.this, CharacterActivity.class);
        i.putExtra("name", name);
        i.putExtra("description", description);
        i.putExtra("path", path);
        i.putExtra("extension", extension);
        Log.e("teste", "id Teste: " + path);
        startActivity(i);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void checkRadio() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                adapter.deleteItems();

                if (checkedId == R.id.radioButtonDate) {
                    opcao = "-modified";
                    listMainChars = listVazia;
                    callApiSearch("", opcao, 0);

                } else {
                    opcao = "name";
                    listMainChars = listVazia;
                    callApiSearch("", opcao, 0);
                }
            }
        });
    }
}

