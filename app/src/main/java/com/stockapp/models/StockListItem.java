package com.stockapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sdk.base.BaseModel;

public class StockListItem extends BaseModel implements Parcelable {


    @SerializedName("company_code")
    @Expose
    private String companyCode;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("volume")
    @Expose
    private Double volume;
    @SerializedName("market_cap")
    @Expose
    private String marketCap;
    @SerializedName("sector")
    @Expose
    private Object sector;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("price_diff")
    @Expose
    private Double priceDiff;
    @SerializedName("change")
    @Expose
    private Double change;
    @SerializedName("exchange")
    @Expose
    private String exchange;
    @SerializedName("mcap")
    @Expose
    private Double mcap;
    @SerializedName("ticker")
    @Expose
    private String ticker;
    @SerializedName("logo")
    @Expose
    private String logo;

    public String getCompanyCode() {
        return companyCode;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getVolume() {
        return volume;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public Object getSector() {
        return sector;
    }

    public String getFormattedPrice() {
        return "$ " + this.price + "";
    }

    public Double getPrice() {
        return price;
    }

    public Double getPriceDiff() {
        return priceDiff;
    }

    public Double getChange() {
        return change;
    }

    public String getExchange() {
        return exchange;
    }

    public Double getMcap() {
        return mcap;
    }

    public String getTicker() {
        return ticker;
    }

    public String getLogo() {
        return logo;
    }

    protected StockListItem(Parcel in) {
    }

    public static final Creator<StockListItem> CREATOR = new Creator<StockListItem>() {
        @Override
        public StockListItem createFromParcel(Parcel in) {
            return new StockListItem(in);
        }

        @Override
        public StockListItem[] newArray(int size) {
            return new StockListItem[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
