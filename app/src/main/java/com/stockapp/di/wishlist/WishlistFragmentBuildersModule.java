package com.stockapp.di.wishlist;

import com.stockapp.ui.wishlist.WishListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class WishlistFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract WishListFragment contributeWishListFragment();


}
