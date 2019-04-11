package com.jepsolucoes.fansmarvel.model;

import android.util.Log;

import com.jepsolucoes.fansmarvel.api.Constants;
import com.jepsolucoes.fansmarvel.api.MarvelService;
import com.jepsolucoes.fansmarvel.api.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityModel {

    private Retrofit retrofit;
    private ResultsROOT resultsROOT;


    public void callApi(String nameStartsWith, String orderby, int offset, int limit, String ts, String hash, final ResponseListener responseListener) {

        retrofit = RetrofitConfig.getRetrofit();

        MarvelService marvelService = retrofit.create(MarvelService.class);
        marvelService.recuperarVideos(
                nameStartsWith,
                orderby,
                offset,
                limit,
                ts,
                Constants.API_KEY,
                hash
        ).enqueue(new Callback<ResultsROOT>() {
            @Override
            public void onResponse(Call<ResultsROOT> call, Response<ResultsROOT> response) {

                Log.d("result", "result: " + response);
                if (response.isSuccessful()) {

                    resultsROOT = response.body();
                    responseListener.onResponse(resultsROOT);
                }
            }

            @Override
            public void onFailure(Call<ResultsROOT> call, Throwable t) {
                Log.d("result", " Fail");
            }
        });
    }
}
