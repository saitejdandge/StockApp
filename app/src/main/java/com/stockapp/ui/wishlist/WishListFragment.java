package com.stockapp.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sdk.base.BaseFragment;
import com.stockapp.R;

public class WishListFragment extends BaseFragment<WishListViewModel> {

    private WishListViewModel mViewModel;

    public static WishListFragment newInstance() {
        return new WishListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wish_list_fragment, container, false);
    }

    @NonNull
    @Override
    protected Class<WishListViewModel> viewModel() {
        return WishListViewModel.class;
    }
}