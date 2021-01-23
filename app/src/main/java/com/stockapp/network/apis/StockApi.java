package com.stockapp.network.apis;


import com.stockapp.network.responses.StockListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StockApi {

    @GET("/api/v1/equity/foreign-stocks/category/all")
    Call<StockListResponse> findStocks(@Query("page") String page, @Query("limit") String limit, @Query("sort_key") String sort);

}