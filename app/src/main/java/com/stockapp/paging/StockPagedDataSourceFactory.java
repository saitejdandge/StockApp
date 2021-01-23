package com.stockapp.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.sdk.network.FindQuery;
import com.stockapp.services.StockService;


public class StockPagedDataSourceFactory extends DataSource.Factory {


    private final StockService stockService;
    private StockListDatasource dataSource;
    private final MutableLiveData<StockListDatasource> basePagedDatasourceMutableLiveData = new MutableLiveData<>();
    private final FindQuery findQuery;

    public StockPagedDataSourceFactory(StockService baseCrudInstance, FindQuery findQuery) {
        this.stockService = baseCrudInstance;
        this.findQuery = findQuery;
    }

    public StockListDatasource getDataSource() {
        return dataSource;
    }

    public MutableLiveData<StockListDatasource> getBasePagedDatasourceMutableLiveData() {
        return basePagedDatasourceMutableLiveData;
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSource = new StockListDatasource(stockService, this.findQuery);
        // Keep reference to the data source with a MutableLiveData reference
        // Use to hold a reference to the
        basePagedDatasourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }
}