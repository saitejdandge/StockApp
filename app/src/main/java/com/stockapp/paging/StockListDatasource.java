package com.stockapp.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.sdk.network.FindQuery;
import com.sdk.network.ResourceStatus;
import com.stockapp.models.StockListItem;
import com.stockapp.network.responses.StockListResponse;
import com.stockapp.services.StockService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockListDatasource extends PageKeyedDataSource<Integer, StockListItem> {

    private static final String TAG = "stest";
    private final StockService stockService;
    private final MutableLiveData<ResourceStatus> networkState;
    private final HashMap<String, StockListItem> watchList;
    private FindQuery findQuery;

    StockListDatasource(HashMap<String, StockListItem> watchList, StockService stockService, FindQuery findQuery) {
        this.stockService = stockService;
        this.watchList = watchList;
        networkState = new MutableLiveData<>();
        networkState.postValue(ResourceStatus.LOADING);
        this.findQuery = findQuery;
    }

    public void setQuery(String query) {
        this.findQuery.setQuery(query);
    }

    public void setWatchList(Boolean watchList) {
        this.findQuery.setWatchList(watchList);
    }

    public MutableLiveData<ResourceStatus> getNetworkState() {
        return networkState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, StockListItem> callback) {
        Log.d(TAG, "loadInitial: " + params + " skip:0, limit:" + params.requestedLoadSize);
        if (this.findQuery == null) {
            this.findQuery = new FindQuery.Builder().skip(1).limit(params.requestedLoadSize).build();
        } else {
            this.findQuery.setLimit(params.requestedLoadSize);
        }
        if (this.findQuery.isWatchList() == null || !this.findQuery.isWatchList())
            stockService.findStocks(this.findQuery, new SCallback() {
                @Override
                public void complete(StockListResponse listResponse) {
                    if (listResponse != null && listResponse.data != null) {
                        networkState.postValue(ResourceStatus.SUCCESS);
                        //since default initial multiplier is 3
                        //callback.onResult(Arrays.asList(listResponse.data), 0, 1000, null, 3);
                        callback.onResult(listResponse.data, null, 3);
                    } else {
                        networkState.postValue(ResourceStatus.ERROR);
                    }

                }
            });
        else {
            //
            List<StockListItem> result = new ArrayList<>();
            result.addAll(watchList.values());
            Log.v("ptest", result.size() + "");
            networkState.postValue(ResourceStatus.SUCCESS);
            callback.onResult(result, null, 1);
        }

    }


    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StockListItem> callback) {
        Log.d(TAG, "loadBefore: " + params.key + " skip:" + params.key * params.requestedLoadSize + " limit:" + params.requestedLoadSize);
        if (this.findQuery.isWatchList() == null || !this.findQuery.isWatchList())
            stockService.findStocks(new FindQuery.Builder().skip(params.key).limit(params.requestedLoadSize).build(), new SCallback() {
                @Override
                public void complete(StockListResponse listResponse) {
                    if (listResponse != null && listResponse.data != null) {
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(listResponse.data, key);
                    }
                }
            });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StockListItem> callback) {
        Log.d(TAG, "loadAfter: " + params.key + " skip:" + params.key * params.requestedLoadSize + " limit:" + params.requestedLoadSize);
        if (this.findQuery.isWatchList() == null || !this.findQuery.isWatchList())
            stockService.findStocks(new FindQuery.Builder().skip(params.key).limit(params.requestedLoadSize).build(), new SCallback() {
                @Override
                public void complete(StockListResponse listResponse) {
                    if (listResponse != null && listResponse.data != null) {
                        Integer key = params.key + 1;
                        callback.onResult(listResponse.data, key);
                    }
                }
            });
    }
}
