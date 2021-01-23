package com.stockapp.di.app;

import com.stockapp.HomeActivity;
import com.stockapp.di.explore.ExploreFragmentBuildersModule;
import com.stockapp.di.explore.ExploreViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {ExploreFragmentBuildersModule.class, ExploreViewModelsModule.class})
    abstract HomeActivity contributesHomeActivity();


}
