package com.stockapp.network.apis;


import com.stockapp.network.responses.StockListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface StockApi {

    @FormUrlEncoded
    @GET("subService/getAvailableMerchants/")
    Call<StockListResponse> findStocks(@Field("query") String query, @Field("sort") String sort);

}