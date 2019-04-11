package com.jepsolucoes.fansmarvel.contract;

import com.jepsolucoes.fansmarvel.basecontract.BaseContract;
import com.jepsolucoes.fansmarvel.model.Results;

import java.util.List;

public class MainActivityContract {

    public interface View extends BaseContract.View {

        void configuraRecyclerView(List<Results> list);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void charReceiver(String query, String orderby, int offset);
    }
}
