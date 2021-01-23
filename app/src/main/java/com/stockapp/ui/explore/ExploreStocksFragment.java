package com.stockapp.ui.explore;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.sdk.ViewModelProviderFactory;
import com.sdk.base.BaseFragment;
import com.stockapp.R;
import com.stockapp.adapters.StockListAdapter;

import javax.inject.Inject;

public class ExploreStocksFragment extends BaseFragment<ExploreStocksViewModel> {


    RecyclerView recyclerView;
    private View loadingView;
    private View errorView;
    private TextInputEditText searchEditText;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private StockListAdapter stockListAdapter;

    public static ExploreStocksFragment newInstance() {
        return new ExploreStocksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.explore_stocks_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        loadData();
    }

    private void initViews(View view) {
        stockListAdapter = new StockListAdapter();
        recyclerView = view.findViewById(R.id.recyclerView);
        loadingView = view.findViewById(R.id.loadingView);
        searchEditText = view.findViewById(R.id.searchEditText);
        errorView = view.findViewById(R.id.errorView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        View retry = view.findViewById(R.id.retry);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            ExploreStocksFragment.this.getViewModel().invalidateList();
        });
        retry.setOnClickListener(v -> this.getViewModel().invalidateList());
        recyclerView.setAdapter(stockListAdapter);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ExploreStocksFragment.this.getViewModel().getSearchQuery().setValue(s.toString());
            }
        });
    }


    private void loadData() {
        getViewModel().getNetworkState().observe(getViewLifecycleOwner(), networkState -> {
                    switch (networkState) {
                        case SUCCESS:
                            loadingView.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            errorView.setVisibility(View.GONE);
                            break;
                        case LOADING:
                            loadingView.setVisibility(View.VISIBLE);
                            errorView.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.GONE);
                            break;
                        case ERROR:
                            loadingView.setVisibility(View.GONE);
                            errorView.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            break;
                    }
                }
        );
        this.getViewModel().getStockList().observe(getViewLifecycleOwner(), posts -> {
            stockListAdapter.submitList(posts);
        });
        this.getViewModel().getSearchQuery().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.trim().length() == 0) {
                    ExploreStocksFragment.this.getViewModel().invalidateList();
                    ExploreStocksFragment.this.getViewModel().getBasePagedDataSourceFactory().setQuery(null);
                } else {
                    ExploreStocksFragment.this.getViewModel().invalidateList();
                    ExploreStocksFragment.this.getViewModel().getBasePagedDataSourceFactory().setQuery(s);
                }
            }
        });
    }

    @NonNull
    @Override
    protected Class<ExploreStocksViewModel> viewModel() {
        return ExploreStocksViewModel.class;
    }
}