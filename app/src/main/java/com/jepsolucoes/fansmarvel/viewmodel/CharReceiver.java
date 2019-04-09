package com.jepsolucoes.fansmarvel.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jepsolucoes.fansmarvel.model.Characters;
import com.jepsolucoes.fansmarvel.model.Result;
import com.jepsolucoes.fansmarvel.model.api.Constants;
import com.jepsolucoes.fansmarvel.model.api.MarvelService;
import com.jepsolucoes.fansmarvel.model.api.RetrofitConfig;

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



    public void charReceiver() {
        retrofit = RetrofitConfig.getRetrofit();
        ts = String.valueOf(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000L);
        md5 = (ts + Constants.PRIV_KEY + Constants.API_KEY);
        hash = stringHexa(gerarHash(md5, "MD5"));
        Log.i("hash teste", hash+"teste hash");

        MarvelService marvelService = retrofit.create(MarvelService.class);
        marvelService.recuperarVideos(
                ts,
                Constants.API_KEY,
                hash
        ).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("resultado", "resultado: "+response);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
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
}
