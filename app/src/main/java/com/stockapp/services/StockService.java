package com.stockapp.services;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.sdk.network.ErrorModel;
import com.sdk.network.FindQuery;
import com.sdk.network.Resource;
import com.stockapp.R;
import com.stockapp.network.apis.StockApi;
import com.stockapp.network.responses.StockDetailsResponse;
import com.stockapp.network.responses.StockListResponse;
import com.stockapp.paging.SCallback;
import com.stockapp.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class StockService {

    private final StockApi stockApi;

    Application application;

    @Inject
    StockService(Application application, StockApi stockApi) {
        this.stockApi = stockApi;
        this.application = application;
    }


    public MutableLiveData<Resource<StockListResponse>> findStocks(FindQuery findQuery, SCallback callback) {
        final MutableLiveData<Resource<StockListResponse>> baseResponseMutableLiveData = new MutableLiveData<>();

        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("page", findQuery.getSkip() + "");
        queryMap.put("limit", findQuery.getLimit() + "");
        queryMap.put("sort_key", findQuery.getSort());
        if (findQuery.getQuery() != null && findQuery.getQuery().trim().length() != 0)
            queryMap.put("term", findQuery.getQuery());
        stockApi.findStocks(queryMap).enqueue(new Callback<StockListResponse>() {
            @Override
            public void onResponse(@NonNull Call<StockListResponse> call, @NonNull Response<StockListResponse> response) {
                if (response.isSuccessful()) {
                    baseResponseMutableLiveData.setValue(Resource.success(response.body()));
                    if (callback != null)
                        callback.complete(response.body());
                } else {
                    ErrorModel errorModel = new ErrorModel.ErrorModelBuilder()
                            .title(Constants.STANDARD_TITLE)
                            .subTitle(Constants.STANDARD_MESSAGE)
                            .image(ContextCompat.getDrawable(application, R.drawable.empty_state))
                            .build();
                    baseResponseMutableLiveData.setValue(Resource.error(errorModel, null));
                    if (callback != null)
                        callback.complete(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<StockListResponse> call, @NonNull Throwable t) {
                ErrorModel errorModel = new ErrorModel.ErrorModelBuilder()
                        .title(Constants.STANDARD_TITLE)
                        .subTitle(Constants.STANDARD_MESSAGE)
                        .image(ContextCompat.getDrawable(application, R.drawable.empty_state))
                        .build();
                baseResponseMutableLiveData.setValue(Resource.error(errorModel, null));
                if (callback != null)
                    callback.complete(null);
            }
        });
        return baseResponseMutableLiveData;
    }

    public MutableLiveData<Resource<StockDetailsResponse>> getStockDetails(String symbol, String interval, String range) {

        final MutableLiveData<Resource<StockDetailsResponse>> responseMutableLiveData = new MutableLiveData<>();

        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("symbol", symbol);
        queryMap.put("interval", interval);
        queryMap.put("range", range);
        queryMap.put("region", Constants.REGION_IND);
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("x-rapidapi-key", Constants.RAPID_API_KEY);
        headerMap.put("x-rapidapi-host", Constants.RAPID_API_HOST);
        headerMap.put("useQueryString", "true");

        stockApi.getStockDetails(headerMap, Constants.YAHOO_FINANCE_URL, queryMap)
                .enqueue(new Callback<StockDetailsResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<StockDetailsResponse> call, @NotNull Response<StockDetailsResponse> response) {
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            responseMutableLiveData.setValue(Resource.success(response.body()));
                        } else {
                            responseMutableLiveData.setValue(Resource.error(null, null));
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<StockDetailsResponse> call, @NotNull Throwable t) {
                        responseMutableLiveData.setValue(Resource.error(null, null));
                    }
                });

        return responseMutableLiveData;
    }

}
