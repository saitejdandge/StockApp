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

public class StockListDatasource extends PageKeyedDataSource<Integer, StockListItem> {

    private static final String TAG = "stest";
    private final StockService stockService;
    private final MutableLiveData<ResourceStatus> networkState;
    private FindQuery findQuery;

    StockListDatasource(StockService stockService) {
        this(stockService, null);
    }

    StockListDatasource(StockService stockService, FindQuery findQuery) {
        this.stockService = stockService;
        networkState = new MutableLiveData<>();
        networkState.postValue(ResourceStatus.LOADING);
        this.findQuery = findQuery;
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
    }


    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, StockListItem> callback) {
        Log.d(TAG, "loadBefore: " + params.key + " skip:" + params.key * params.requestedLoadSize + " limit:" + params.requestedLoadSize);
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
