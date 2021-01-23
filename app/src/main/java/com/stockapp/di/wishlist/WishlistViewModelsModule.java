package com.stockapp.di.wishlist;

import androidx.lifecycle.ViewModel;

import com.stockapp.di.app.ViewModelKey;
import com.stockapp.ui.wishlist.WishListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class WishlistViewModelsModule {


    @Binds
    @IntoMap
    @ViewModelKey(WishListViewModel.class)
    public abstract ViewModel bindWishListViewModel(WishListViewModel viewModel);


}
