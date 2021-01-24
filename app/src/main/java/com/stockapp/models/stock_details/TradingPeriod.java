package com.stockapp.models.stock_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradingPeriod {

    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("start")
    @Expose
    public Integer start;
    @SerializedName("end")
    @Expose
    public Integer end;
    @SerializedName("gmtoffset")
    @Expose
    public Integer gmtoffset;

}
