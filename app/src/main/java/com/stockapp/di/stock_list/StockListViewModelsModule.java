package com.stockapp.di.stock_list;

import androidx.lifecycle.ViewModel;

import com.stockapp.di.app.ViewModelKey;
import com.stockapp.ui.stock_list.StockListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class StockListViewModelsModule {


    @Binds
    @IntoMap
    @ViewModelKey(StockListViewModel.class)
    public abstract ViewModel bindUploadImagesViewModel(StockListViewModel viewModel);


}
