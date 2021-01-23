package com.stockapp.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.sdk.network.FindQuery;
import com.stockapp.models.StockListItem;
import com.stockapp.services.StockService;

import java.util.HashMap;


public class StockPagedDataSourceFactory extends DataSource.Factory {


    private final StockService stockService;
    private final MutableLiveData<StockListDatasource> basePagedDatasourceMutableLiveData = new MutableLiveData<>();
    private final FindQuery findQuery;
    private final HashMap<String, StockListItem> watchList;
    private StockListDatasource dataSource;

    public StockPagedDataSourceFactory(HashMap<String, StockListItem> watchList, StockService stockService, FindQuery findQuery) {
        this.stockService = stockService;
        this.findQuery = findQuery;
        this.watchList = watchList;
    }

    public StockListDatasource getDataSource() {
        return dataSource;
    }

    public MutableLiveData<StockListDatasource> getBasePagedDatasourceMutableLiveData() {
        return basePagedDatasourceMutableLiveData;
    }

    public void setQuery(String query) {
        if (dataSource != null)
            dataSource.setQuery(query);
    }


    public void setWatchList(Boolean watchList) {
        if (dataSource != null)
            dataSource.setWatchList(watchList);
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSource = new StockListDatasource(watchList, stockService, this.findQuery);
        // Keep reference to the data source with a MutableLiveData reference
        // Use to hold a reference to the
        basePagedDatasourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }
}