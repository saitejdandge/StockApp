package com.stockapp.di.stock_details;

import com.stockapp.ui.stock_details.StockDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StockDetailsFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract StockDetailsFragment contributeStockDetailsFragment();


}
