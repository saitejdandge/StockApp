package com.stockapp.ui.explore;


import android.app.Application;

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
import com.stockapp.repositories.StockRepo;

import javax.inject.Inject;

public class ExploreStocksViewModel extends BaseViewModel {

    private final StockPagedDataSourceFactory basePagedDataSourceFactory;
    private final LiveData<ResourceStatus> networkState;
    private final LiveData<PagedList<StockListItem>> stockList;
    private final MutableLiveData<String> searchQuery;
    private StockRepo stockRepo;

    @Inject
    ExploreStocksViewModel(Application application, StockRepo stockRepo) {
        super(application);
        searchQuery = new MutableLiveData<>();
        this.stockRepo = stockRepo;
        FindQuery findQuery = new FindQuery.Builder().skip(1).query(null).build();
        this.basePagedDataSourceFactory = new StockPagedDataSourceFactory(this.stockRepo.getStockService(), findQuery);
        // Initial page size to fetch can also be configured here too
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(20).setEnablePlaceholders(true).setPrefetchDistance(5).build();
        stockList = new LivePagedListBuilder(basePagedDataSourceFactory, config).build();
        networkState = Transformations.switchMap(basePagedDataSourceFactory.getBasePagedDatasourceMutableLiveData(), dataSource -> dataSource.getNetworkState());
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

}