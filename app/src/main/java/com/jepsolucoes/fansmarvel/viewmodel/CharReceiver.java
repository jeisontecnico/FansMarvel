package com.jepsolucoes.fansmarvel.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jepsolucoes.fansmarvel.R;
import com.jepsolucoes.fansmarvel.model.ListaChars;
import com.jepsolucoes.fansmarvel.model.ResultadoROOT;
import com.jepsolucoes.fansmarvel.model.Results;
import com.jepsolucoes.fansmarvel.model.api.Constants;
import com.jepsolucoes.fansmarvel.model.api.MarvelService;
import com.jepsolucoes.fansmarvel.model.api.RetrofitConfig;
import com.jepsolucoes.fansmarvel.view.MainActivity;
import com.jepsolucoes.fansmarvel.viewmodel.adapter.AdapterChars;
import com.jepsolucoes.fansmarvel.viewmodel.listener.RecyclerItemClickListener;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CharReceiver {

    private Retrofit retrofit;
    private String ts;
    private String hash;
    private String md5;
    public List<Results> lista = new ArrayList<>();
    ResultadoROOT resultadoROOT;
    private RecyclerView recyclerLista;
    private AdapterChars adapterChars;
    public Context context;

    public CharReceiver(LinearLayout linearLayout) {
        recyclerLista = linearLayout.findViewById(R.id.recyclerLista);
    }

    public void charReceiver() {
        retrofit = RetrofitConfig.getRetrofit();
        ts = String.valueOf(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000L);
        md5 = (ts + Constants.PRIV_KEY + Constants.API_KEY);
        hash = stringHexa(gerarHash(md5, "MD5"));

        MarvelService marvelService = retrofit.create(MarvelService.class);
        marvelService.recuperarVideos(
                ts,
                Constants.API_KEY,
                hash
        ).enqueue(new Callback<ResultadoROOT>() {
            @Override
            public void onResponse(Call<ResultadoROOT> call, Response<ResultadoROOT> response) {

                Log.d("resultado", "resultado: "+response);
                if(response.isSuccessful()){

                    resultadoROOT = response.body();
                    lista = resultadoROOT.data.results;
                    configuraRecyclerView();
                    Log.d("resultado", "resultado url: "+lista.get(0).thumbnail.getPath().toString());
                }
            }

            @Override
            public void onFailure(Call<ResultadoROOT> call, Throwable t) {
                Log.d("resultado", " Falhou");
            }
        });
    }

    public static byte[] gerarHash(String frase, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    private static String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    public void configuraRecyclerView(){
        adapterChars = new AdapterChars(lista, context);
        recyclerLista.setHasFixedSize(true);
        recyclerLista.setLayoutManager(new LinearLayoutManager(context));
        recyclerLista.setAdapter(adapterChars);

        recyclerLista.addOnItemTouchListener(new RecyclerItemClickListener(
                context,
                recyclerLista,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Results results = lista.get(position);
                        String idCharacter = results.getId();

                        Intent i = new Intent(MainActivity.this, )
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));

    }
}
