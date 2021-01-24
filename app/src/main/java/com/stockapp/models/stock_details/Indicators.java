package com.stockapp.models.stock_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Indicators {

    @SerializedName("quote")
    @Expose
    public List<Quote> quote = null;

}
