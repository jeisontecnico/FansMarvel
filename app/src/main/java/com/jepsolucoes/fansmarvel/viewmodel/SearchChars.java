package com.jepsolucoes.fansmarvel.viewmodel;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class SearchChars {

    public String campoBusca = "";

    public SearchChars(final MaterialSearchView searchView) {


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {


                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
            }

            @Override
            public void onSearchViewClosed() {

            }
        });

    }
}
