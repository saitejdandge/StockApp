package com.stockapp.ui.wishlist;


import android.app.Application;

import com.sdk.base.BaseViewModel;

import javax.inject.Inject;

public class WishListViewModel extends BaseViewModel {
    @Inject
    WishListViewModel(Application application) {
        super(application);
    }
}