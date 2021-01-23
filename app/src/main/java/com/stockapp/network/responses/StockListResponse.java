package com.stockapp.network.responses;

import com.sdk.network.BaseResponse;
import com.stockapp.models.StockListItem;

import java.util.List;

public class StockListResponse extends BaseResponse {

    public List<StockListItem> data;
}
