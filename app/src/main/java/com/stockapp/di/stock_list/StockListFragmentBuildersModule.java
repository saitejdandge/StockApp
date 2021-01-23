package com.stockapp.di.stock_list;

import com.stockapp.ui.stock_list.StockListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StockListFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract StockListFragment contributeExploreStocksFragment();


}
