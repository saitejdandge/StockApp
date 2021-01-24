package com.stockapp.models.stock_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meta {

    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("exchangeName")
    @Expose
    public String exchangeName;
    @SerializedName("instrumentType")
    @Expose
    public String instrumentType;
    @SerializedName("firstTradeDate")
    @Expose
    public Integer firstTradeDate;
    @SerializedName("regularMarketTime")
    @Expose
    public Integer regularMarketTime;
    @SerializedName("gmtoffset")
    @Expose
    public Integer gmtoffset;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("exchangeTimezoneName")
    @Expose
    public String exchangeTimezoneName;
    @SerializedName("regularMarketPrice")
    @Expose
    public Double regularMarketPrice;
    @SerializedName("chartPreviousClose")
    @Expose
    public Double chartPreviousClose;
    @SerializedName("previousClose")
    @Expose
    public Double previousClose;
    @SerializedName("scale")
    @Expose
    public Integer scale;
    @SerializedName("priceHint")
    @Expose
    public Integer priceHint;
    @SerializedName("currentTradingPeriod")
    @Expose
    public CurrentTradingPeriod currentTradingPeriod;
    @SerializedName("tradingPeriods")
    @Expose
    public List<List<TradingPeriod>> tradingPeriods = null;
    @SerializedName("dataGranularity")
    @Expose
    public String dataGranularity;
    @SerializedName("range")
    @Expose
    public String range;
    @SerializedName("validRanges")
    @Expose
    public List<String> validRanges = null;

}
