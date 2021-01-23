package com.stockapp.di.search;

import com.stockapp.ui.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SearchFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();


}
