package com.stockapp.models.stock_details;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentTradingPeriod {


    @SerializedName("pre")
    @Expose
    public Pre pre;
    @SerializedName("regular")
    @Expose
    public Regular regular;
    @SerializedName("post")
    @Expose
    public Post post;

}
