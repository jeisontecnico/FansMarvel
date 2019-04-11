package com.jepsolucoes.fansmarvel.api;

import com.jepsolucoes.fansmarvel.model.ResultsROOT;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {


    /*For example, a user with a public key of "1234" and a private key of "abcd" could construct a valid call as follows:
    http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150
    // (the hash value is the md5 digest of 1abcd1234)*/



    @GET("characters")
    Call<ResultsROOT> recuperarVideos(
            @Query("nameStartsWith") String nameStartsWith,
            @Query("orderBy") String orderBy,
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("ts") String ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash

    );
}
