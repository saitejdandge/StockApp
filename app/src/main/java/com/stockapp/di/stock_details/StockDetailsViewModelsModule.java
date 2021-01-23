package com.stockapp.di.stock_details;

import androidx.lifecycle.ViewModel;

import com.stockapp.di.app.ViewModelKey;
import com.stockapp.ui.stock_details.StockDetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class StockDetailsViewModelsModule {


    @Binds
    @IntoMap
    @ViewModelKey(StockDetailsViewModel.class)
    public abstract ViewModel bindStockDetailsViewModel(StockDetailsViewModel viewModel);


}
