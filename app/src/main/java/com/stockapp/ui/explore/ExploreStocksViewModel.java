package com.stockapp.ui.explore;


import com.sdk.base.BaseViewModel;
import com.stockapp.repositories.StockRepo;

import javax.inject.Inject;

public class ExploreStocksViewModel extends BaseViewModel {
    @Inject
    ExploreStocksViewModel(StockRepo stockRepo) {

    }
}