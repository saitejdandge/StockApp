package com.stockapp.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sdk.network.BaseResponse;
import com.stockapp.models.stock_details.Chart;

public class StockDetailsResponse extends BaseResponse {

    @SerializedName("chart")
    @Expose
    public Chart chart;

    public boolean isSuccess() {
        return this.chart != null && this.chart.error == null && this.chart.result != null;
    }
}
