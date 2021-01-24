package com.stockapp.models.stock_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quote {

    @SerializedName("open")
    @Expose
    public List<Double> open = null;
    @SerializedName("close")
    @Expose
    public List<Double> close = null;
    @SerializedName("high")
    @Expose
    public List<Double> high = null;
    @SerializedName("low")
    @Expose
    public List<Double> low = null;
    @SerializedName("volume")
    @Expose
    public List<Integer> volume = null;

}
