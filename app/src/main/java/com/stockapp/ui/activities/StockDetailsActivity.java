package com.stockapp.ui.activities;

import android.os.Bundle;

import com.stockapp.R;
import com.stockapp.ui.stock_details.StockDetailsFragment;

import dagger.android.support.DaggerAppCompatActivity;

public class StockDetailsActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, StockDetailsFragment.newInstance(getIntent().getExtras().getParcelable("stock"))).commit();
    }
}