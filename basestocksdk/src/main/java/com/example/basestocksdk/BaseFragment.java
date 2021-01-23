package com.example.basestocksdk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public abstract class BaseFragment<V extends BaseViewModel> extends DaggerFragment {


    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private V mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(viewModel());
    }


    @NonNull
    protected abstract Class<V> viewModel();

    @NonNull
    public V getViewModel() {
        return mViewModel;
    }
}
