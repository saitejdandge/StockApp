package com.stockapp.paging;

import com.stockapp.network.responses.StockListResponse;

public interface SCallback {
    void complete(StockListResponse response);
}
