package com.stockapp.ui.explore;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.sdk.base.BaseViewModel;
import com.sdk.network.FindQuery;
import com.sdk.network.ResourceStatus;
import com.stockapp.models.StockListItem;
import com.stockapp.paging.StockPagedDataSourceFactory;
import com.stockapp.services.StockService;

import javax.inject.Inject;

public class ExploreStocksViewModel extends BaseViewModel {


    private final StockPagedDataSourceFactory basePagedDataSourceFactory;
    private final LiveData<ResourceStatus> networkState;
    private final LiveData<PagedList<StockListItem>> stockList;
    private MutableLiveData<String> searchQuery;

    public MutableLiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public StockPagedDataSourceFactory getBasePagedDataSourceFactory() {
        return basePagedDataSourceFactory;
    }

    @Inject
    ExploreStocksViewModel(StockService stockService) {
        searchQuery = new MutableLiveData<>();
        FindQuery findQuery = new FindQuery.Builder().skip(1).query(null).build();
        this.basePagedDataSourceFactory = new StockPagedDataSourceFactory(stockService, findQuery);
        // Initial page size to fetch can also be configured here too
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(20).setEnablePlaceholders(true).setPrefetchDistance(5).build();
        stockList = new LivePagedListBuilder(basePagedDataSourceFactory, config).build();
        networkState = Transformations.switchMap(basePagedDataSourceFactory.getBasePagedDatasourceMutableLiveData(), dataSource -> dataSource.getNetworkState());
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

}