package com.stockapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.sdk.base.BaseModel;

public class StockListItem extends BaseModel implements Parcelable {
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
