package com.stockapp.repositories;

import com.stockapp.services.StockService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StockRepo {
    private StockService stockService;

    @Inject
    public StockRepo(StockService stockService) {
        this.stockService = stockService;
    }

    public StockService getStockService() {
        return stockService;
    }
}
