package com.stockapp.network.apis;


import com.stockapp.network.responses.ListStockResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StockApi {

    @FormUrlEncoded
    @POST("subService/getAvailableMerchants/")
    Call<ListStockResponse> getAllStocks(@Field("query") String query, @Field("sort") String sort);

}