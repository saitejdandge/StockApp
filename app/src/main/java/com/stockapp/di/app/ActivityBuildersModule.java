package com.stockapp.di.app;

import com.stockapp.ui.activities.HomeActivity;
import com.stockapp.ui.activities.SearchActivity;
import com.stockapp.ui.activities.StockDetailsActivity;
import com.stockapp.di.explore.ExploreFragmentBuildersModule;
import com.stockapp.di.explore.ExploreViewModelsModule;
import com.stockapp.di.search.SearchFragmentBuildersModule;
import com.stockapp.di.search.SearchViewModelsModule;
import com.stockapp.di.stock_details.StockDetailsFragmentBuildersModule;
import com.stockapp.di.stock_details.StockDetailsViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {ExploreFragmentBuildersModule.class, ExploreViewModelsModule.class})
    abstract HomeActivity contributesHomeActivity();

    @ContributesAndroidInjector(modules = {SearchFragmentBuildersModule.class, SearchViewModelsModule.class})
    abstract SearchActivity contributesSearchActivity();

    @ContributesAndroidInjector(modules = {StockDetailsFragmentBuildersModule.class, StockDetailsViewModelsModule.class})
    abstract StockDetailsActivity contributesStockDetailsActivity();
}
