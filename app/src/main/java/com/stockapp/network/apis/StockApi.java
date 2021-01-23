package com.stockapp.network.apis;


import com.stockapp.network.responses.StockListResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface StockApi {

    @GET("/api/v1/equity/foreign-stocks/category/all")
    Call<StockListResponse> findStocks(@QueryMap HashMap<String,String>queryMap);

}