package com.stockapp.di.search;

import androidx.lifecycle.ViewModel;

import com.stockapp.di.app.ViewModelKey;
import com.stockapp.ui.search.SearchViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SearchViewModelsModule {


    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    public abstract ViewModel bindSearchViewModel(SearchViewModel viewModel);


}
