package com.stockapp.di.explore;

import com.stockapp.ui.explore.ExploreStocksFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ExploreFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract ExploreStocksFragment contributeExploreStocksFragment();


}
