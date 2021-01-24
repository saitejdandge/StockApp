package com.stockapp.models.stock_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("timestamp")
    @Expose
    public List<Integer> timestamp = null;
    @SerializedName("indicators")
    @Expose
    public Indicators indicators;

}
