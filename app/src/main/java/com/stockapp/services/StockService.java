package com.stockapp.services;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.sdk.network.FindQuery;
import com.sdk.network.Resource;
import com.stockapp.network.apis.StockApi;
import com.stockapp.network.responses.StockListResponse;
import com.stockapp.paging.SCallback;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class StockService {

    private StockApi stockApi;

    @Inject
    StockService(StockApi stockApi) {
        this.stockApi = stockApi;
    }


    public MutableLiveData<Resource<StockListResponse>> findStocks(FindQuery findQuery, SCallback callback) {
        final MutableLiveData<Resource<StockListResponse>> baseResponseMutableLiveData = new MutableLiveData<>();
        stockApi.findStocks(findQuery.getQuery(), findQuery.getSort()).enqueue(new Callback<StockListResponse>() {
            @Override
            public void onResponse(@NonNull Call<StockListResponse> call, @NonNull Response<StockListResponse> response) {
                if (response.isSuccessful()) {
                    baseResponseMutableLiveData.setValue(Resource.success(response.body()));
                } else {
                    baseResponseMutableLiveData.setValue(Resource.error(null, null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<StockListResponse> call, @NonNull Throwable t) {
                //todo add error model here
                baseResponseMutableLiveData.setValue(Resource.error(null, null));
            }
        });
        return baseResponseMutableLiveData;
    }

}
