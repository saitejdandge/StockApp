package com.stockapp.ui.stock_details;

import android.app.Application;

import com.sdk.base.BaseViewModel;

import javax.inject.Inject;

public class StockDetailsViewModel extends BaseViewModel {
    @Inject
    StockDetailsViewModel(Application application) {
        super(application);
    }
}