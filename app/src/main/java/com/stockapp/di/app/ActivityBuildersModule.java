package com.stockapp.di.app;

import com.stockapp.di.stock_details.StockDetailsFragmentBuildersModule;
import com.stockapp.di.stock_details.StockDetailsViewModelsModule;
import com.stockapp.di.stock_list.StockListFragmentBuildersModule;
import com.stockapp.di.stock_list.StockListViewModelsModule;
import com.stockapp.ui.activities.BottomNavigationActivity;
import com.stockapp.ui.activities.StockDetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {


    @ContributesAndroidInjector(modules = {StockListFragmentBuildersModule.class, StockListViewModelsModule.class})
    abstract BottomNavigationActivity contributesBottomNavigationActivity();


    @ContributesAndroidInjector(modules = {StockDetailsFragmentBuildersModule.class, StockDetailsViewModelsModule.class})
    abstract StockDetailsActivity contributesStockDetailsActivity();
}
