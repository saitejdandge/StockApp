package com.stockapp.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sdk.ViewModelProviderFactory;
import com.sdk.base.BaseFragment;
import com.stockapp.R;

import javax.inject.Inject;

public class ExploreStocksFragment extends BaseFragment<ExploreStocksViewModel> {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    private ExploreStocksViewModel mViewModel;

    public static ExploreStocksFragment newInstance() {
        return new ExploreStocksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.explore_stocks_fragment, container, false);
    }


    @NonNull
    @Override
    protected Class<ExploreStocksViewModel> viewModel() {
        return ExploreStocksViewModel.class;
    }
}