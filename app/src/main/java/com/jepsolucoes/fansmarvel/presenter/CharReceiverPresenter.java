package com.jepsolucoes.fansmarvel.presenter;

import com.jepsolucoes.fansmarvel.contract.MainActivityContract;
import com.jepsolucoes.fansmarvel.model.MainActivityModel;
import com.jepsolucoes.fansmarvel.model.ResponseListener;
import com.jepsolucoes.fansmarvel.model.ResultsROOT;
import com.jepsolucoes.fansmarvel.api.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Retrofit;

public class CharReceiverPresenter implements ResponseListener, MainActivityContract.Presenter {

    private Retrofit retrofit;
    private String ts;
    private String md5;
    private String hash;
    private int offsetTest;
    private int limit = 20;

    private MainActivityContract.View view ;

    private MainActivityModel model = new MainActivityModel();

    @Override
    public void charReceiver(String query, String orderBy,int offset) {

        if (query.isEmpty()) {
            query = null;
        }
        if (orderBy.isEmpty()){
            orderBy = null;
        }
        if (offset == 0){

                    }else{
            offsetTest = offset;
        }
        ts = String.valueOf(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000L);
        md5 = (ts + Constants.PRIV_KEY + Constants.API_KEY);
        hash = stringHexa(gerarHash(md5, "MD5"));
        model.callApi(query,orderBy,offset , limit, ts, hash, this);
    }

    private static byte[] gerarHash(String frase, String algoritmo) {
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
        for (byte aByte : bytes) {
            int parteAlta = ((aByte >> 4) & 0xf) << 4;
            int parteBaixa = aByte & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    @Override
    public void onResponse(ResultsROOT resultsROOT) {
        view.configuraRecyclerView(resultsROOT.data.results);
    }

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
