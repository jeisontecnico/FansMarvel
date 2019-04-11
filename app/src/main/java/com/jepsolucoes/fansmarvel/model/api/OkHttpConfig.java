package com.jepsolucoes.fansmarvel.model.api;

import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpConfig extends OkHttpClient implements Interceptor  {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url;

        Long timeStamp = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000L);
        String ts = timeStamp.toString();
            url = originalHttpUrl.newBuilder()
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("apikey",Constants.API_KEY)
                    .addQueryParameter("hash", ("timeStamp + Constants.PRIV_KEY + Constants.API_KEY"))
                    .build();


            return chain.proceed(original.newBuilder().url(url).build());


    }
}
