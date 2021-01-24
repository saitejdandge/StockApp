package com.stockapp.ui.stock_list;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.gson.Gson;
import com.sdk.base.BaseViewModel;
import com.sdk.network.FindQuery;
import com.sdk.network.ResourceStatus;
import com.stockapp.models.StockListItem;
import com.stockapp.paging.StockListDatasource;
import com.stockapp.paging.StockPagedDataSourceFactory;
import com.stockapp.repositories.StockRepo;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class StockListViewModel extends BaseViewModel {

    private final MutableLiveData<String> searchQuery;
    private StockPagedDataSourceFactory basePagedDataSourceFactory;
    private LiveData<ResourceStatus> networkState;
    private LiveData<PagedList<StockListItem>> stockList;
    private final MutableLiveData<Boolean> isWatchList;
    private final StockRepo stockRepo;

    @Inject
    StockListViewModel(StockRepo stockRepo) {
        searchQuery = new MutableLiveData<>();
        isWatchList = new MutableLiveData<>();
        this.stockRepo = stockRepo;
    }

    public MutableLiveData<Boolean> getIsWatchList() {
        return isWatchList;
    }

    public void loadStocks(Boolean isWatchList, HashMap<String, StockListItem> watchListData) {
        FindQuery findQuery = new FindQuery.Builder().skip(1).watchList(isWatchList).query(null).build();
        this.basePagedDataSourceFactory = new StockPagedDataSourceFactory(watchListData, this.stockRepo.getStockService(), findQuery);
        // Initial page size to fetch can also be configured here too
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(20).setEnablePlaceholders(true).setPrefetchDistance(5).build();
        stockList = new LivePagedListBuilder(basePagedDataSourceFactory, config).build();
        networkState = Transformations.switchMap(basePagedDataSourceFactory.getBasePagedDatasourceMutableLiveData(), StockListDatasource::getNetworkState);
    }

    public MutableLiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public StockPagedDataSourceFactory getBasePagedDataSourceFactory() {
        return basePagedDataSourceFactory;
    }

    void invalidateList() {
        if (basePagedDataSourceFactory != null && basePagedDataSourceFactory.getDataSource() != null)
            basePagedDataSourceFactory.getDataSource().invalidate();
    }


    LiveData<ResourceStatus> getNetworkState() {
        return networkState;
    }

    LiveData<PagedList<StockListItem>> getStockList() {
        return stockList;
    }

    public HashMap<String, StockListItem> getWatchListMap(Map<String, ?> map) {
        HashMap<String, StockListItem> output = new HashMap<>();
        Gson gson = new Gson();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            output.put(entry.getKey(), gson.fromJson(entry.getValue() + "", StockListItem.class));
        }
        return output;
    }

}