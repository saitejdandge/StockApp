package com.stockapp.models.stock_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chart {

    @SerializedName("result")
    @Expose
    public List<Result> result = null;
    @SerializedName("error")
    @Expose
    public Object error;

}
