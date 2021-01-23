package com.stockapp.di.explore;

import androidx.lifecycle.ViewModel;

import com.stockapp.di.app.ViewModelKey;
import com.stockapp.ui.explore.ExploreStocksViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ExploreViewModelsModule {


    @Binds
    @IntoMap
    @ViewModelKey(ExploreStocksViewModel.class)
    public abstract ViewModel bindUploadImagesViewModel(ExploreStocksViewModel viewModel);


}
