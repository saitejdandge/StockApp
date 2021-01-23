package com.stockapp.ui.stock_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sdk.base.BaseFragment;
import com.stockapp.R;

public class StockDetailsFragment extends BaseFragment<StockDetailsViewModel> {


    public static StockDetailsFragment newInstance() {
        return new StockDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stock_details_fragment2, container, false);
    }

    @NonNull
    @Override
    protected Class<StockDetailsViewModel> viewModel() {
        return StockDetailsViewModel.class;
    }
}