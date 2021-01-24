package com.stockapp.network.apis;


import com.stockapp.network.responses.StockDetailsResponse;
import com.stockapp.network.responses.StockListResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface StockApi {

    @GET("/api/v1/equity/foreign-stocks/category/all")
    Call<StockListResponse> findStocks(@QueryMap HashMap<String, String> queryMap);

    @GET
    Call<StockDetailsResponse> getStockDetails(@HeaderMap HashMap<String, String> map, @Url String url, @QueryMap HashMap<String, String> queryMap);

}