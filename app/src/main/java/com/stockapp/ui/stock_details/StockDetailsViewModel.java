package com.stockapp.ui.stock_details;

import androidx.lifecycle.MutableLiveData;

import com.sdk.base.BaseViewModel;
import com.sdk.network.Resource;
import com.stockapp.models.StockListItem;
import com.stockapp.network.responses.StockDetailsResponse;
import com.stockapp.repositories.StockRepo;

import javax.inject.Inject;

public class StockDetailsViewModel extends BaseViewModel {
    private final StockRepo stockRepo;
    public MutableLiveData<String> range;
    public MutableLiveData<StockListItem> stockListItemMutableLiveData;

    @Inject
    StockDetailsViewModel(StockRepo stockRepo) {
        range = new MutableLiveData<>();
        this.stockRepo = stockRepo;
        this.stockListItemMutableLiveData = new MutableLiveData<>();

    }


    public String getIntervalForRange(String range) {
        switch (range) {
            case "1d":
            case "5d":
                return "15m";
            default:
                return "1d";
        }
    }

    public MutableLiveData<Resource<StockDetailsResponse>> getStockDetails(String symbol, String interval, String range) {
        return this.stockRepo.getStockService().getStockDetails(symbol, interval, range);
    }

}