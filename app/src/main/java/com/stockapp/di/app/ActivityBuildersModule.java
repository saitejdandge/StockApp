package com.stockapp.di.app;

import com.stockapp.di.explore.ExploreFragmentBuildersModule;
import com.stockapp.di.explore.ExploreViewModelsModule;
import com.stockapp.di.stock_details.StockDetailsFragmentBuildersModule;
import com.stockapp.di.stock_details.StockDetailsViewModelsModule;
import com.stockapp.di.wishlist.WishlistFragmentBuildersModule;
import com.stockapp.di.wishlist.WishlistViewModelsModule;
import com.stockapp.ui.BottomNavigationActivity;
import com.stockapp.ui.activities.HomeActivity;
import com.stockapp.ui.activities.StockDetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {ExploreFragmentBuildersModule.class, ExploreViewModelsModule.class})
    abstract HomeActivity contributesHomeActivity();


    @ContributesAndroidInjector(modules = {ExploreFragmentBuildersModule.class, ExploreViewModelsModule.class, WishlistViewModelsModule.class, WishlistFragmentBuildersModule.class})
    abstract BottomNavigationActivity contributesBottomNavigationActivity();


    @ContributesAndroidInjector(modules = {StockDetailsFragmentBuildersModule.class, StockDetailsViewModelsModule.class})
    abstract StockDetailsActivity contributesStockDetailsActivity();
}
