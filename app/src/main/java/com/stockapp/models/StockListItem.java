package com.stockapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sdk.base.BaseModel;

import java.text.DecimalFormat;

public class StockListItem extends BaseModel implements Parcelable {


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

    protected StockListItem(Parcel in) {
        companyCode = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            volume = null;
        } else {
            volume = in.readDouble();
        }
        marketCap = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        if (in.readByte() == 0) {
            priceDiff = null;
        } else {
            priceDiff = in.readDouble();
        }
        if (in.readByte() == 0) {
            change = null;
        } else {
            change = in.readDouble();
        }
        exchange = in.readString();
        if (in.readByte() == 0) {
            mcap = null;
        } else {
            mcap = in.readDouble();
        }
        ticker = in.readString();
        logo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyCode);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (volume == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(volume);
        }
        dest.writeString(marketCap);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        if (priceDiff == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(priceDiff);
        }
        if (change == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(change);
        }
        dest.writeString(exchange);
        if (mcap == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mcap);
        }
        dest.writeString(ticker);
        dest.writeString(logo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

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

    public String getFormattedChange() {
        try {
            DecimalFormat f = new DecimalFormat("#.00");
            String s = "";
            if (this.change != null)
                s += f.format(this.change) + "% ";
            s += ("($" + f.format(this.priceDiff) + ")");
            return s;
        } catch (Exception e) {
            Log.v("etest", e.getLocalizedMessage() + " " + this.change + " " + this.priceDiff);
            e.printStackTrace();
            return "";
        }
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

}
