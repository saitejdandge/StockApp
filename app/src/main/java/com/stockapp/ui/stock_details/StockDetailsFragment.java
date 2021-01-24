package com.stockapp.ui.stock_details;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.sdk.base.BaseFragment;
import com.sdk.network.Resource;
import com.stockapp.R;
import com.stockapp.adapters.StockDetailsMetaDataAdapter;
import com.stockapp.models.StockListItem;
import com.stockapp.models.stock_details.Meta;
import com.stockapp.network.responses.StockDetailsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockDetailsFragment extends BaseFragment<StockDetailsViewModel> {

    CandleStickChart candleStickChart;
    String symbol;
    RangeAdapter rangeAdapter;
    private StockDetailsMetaDataAdapter metaDataAdapter;

    private ImageView logo, triangle;
    private TextView name, price, desc, change;

    public static StockDetailsFragment newInstance(StockListItem stockListItem) {

        Bundle args = new Bundle();
        StockDetailsFragment fragment = new StockDetailsFragment();
        args.putString("symbol", stockListItem.getTicker());
        args.putParcelable("stock", stockListItem);
        fragment.setArguments(args);
        return fragment;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        candleStickChart = view.findViewById(R.id.candle_stick_chart);

        name = view.findViewById(R.id.name);
        price = view.findViewById(R.id.price);
        desc = view.findViewById(R.id.desc);
        logo = view.findViewById(R.id.logo);
        change = view.findViewById(R.id.change);
        triangle = view.findViewById(R.id.triangle);


        this.symbol = getArguments().getString("symbol");
        this.getViewModel().stockListItemMutableLiveData.setValue(getArguments().getParcelable("stock"));
        this.getViewModel().range.setValue("5d");
        RecyclerView recyclerView = view.findViewById(R.id.rangesRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        this.rangeAdapter = new RangeAdapter(Arrays.asList(new String[]{"5d", "3mo", "6mo", "1y", "5y", "max"}));
        this.metaDataAdapter = new StockDetailsMetaDataAdapter();
        RecyclerView metaRv = view.findViewById(R.id.metaDataRv);
        metaRv.setAdapter(metaDataAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        metaRv.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(rangeAdapter);
        initCandleGraph();
        observeRange();
        observeStockListItem();
    }

    private void observeStockListItem() {
        this.getViewModel().stockListItemMutableLiveData.observe(getViewLifecycleOwner(), stockListItem -> {
            name.setText(stockListItem.getName());
            price.setText(stockListItem.getFormattedPrice());
            change.setText(stockListItem.getFormattedChange());
            desc.setText(stockListItem.getTicker() + " |" + stockListItem.getMarketCap());
            Glide.with(getActivity()).load(stockListItem.getLogo()).into(logo);
            if (stockListItem.getChange() == null || stockListItem.getChange() >= 0) {
                change.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGreen));
                triangle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.green_triangle));
            } else {
                change.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
                triangle.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.red_triangle));
            }
        });
    }

    private void observeRange() {
        this.getViewModel().range.observe(getViewLifecycleOwner(), s -> {
            rangeAdapter.notifyDataSetChanged();
            StockDetailsFragment.this.getViewModel().getStockDetails(symbol, StockDetailsFragment.this.getViewModel().getIntervalForRange(s), s).observe(getViewLifecycleOwner(), new Observer<Resource<StockDetailsResponse>>() {
                @Override
                public void onChanged(Resource<StockDetailsResponse> resource) {
                    if (resource != null && resource.isSuccess()) {
                        setCandleData(resource.getResponse());
                        setMetaData(resource.getResponse().chart.result.get(0).meta);
                    } else {
                        candleStickChart.setData(null);
                        candleStickChart.invalidate();
                        Toast.makeText(getContext(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        });
    }

    private void setMetaData(Meta meta) {

        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair("Stock Symbol", meta.symbol));
        data.add(new Pair("Regular Market Price", "$" + meta.regularMarketPrice));
        data.add(new Pair("Previous Close", "$" + meta.previousClose));
        metaDataAdapter.setData(data);

    }

    private void initCandleGraph() {

        candleStickChart.setHighlightPerDragEnabled(true);
        candleStickChart.setDrawBorders(true);
        candleStickChart.setBorderColor(getResources().getColor(R.color.loadingColor));
        YAxis yAxis = candleStickChart.getAxisLeft();
        YAxis rightAxis = candleStickChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLines(false);
        candleStickChart.requestDisallowInterceptTouchEvent(true);

        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setDrawGridLines(false);// disable x axis grid lines
        xAxis.setDrawLabels(false);
        rightAxis.setTextColor(Color.WHITE);
        yAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAvoidFirstLastClipping(true);

        Legend l = candleStickChart.getLegend();
        l.setEnabled(false);
    }

    private void setCandleData(StockDetailsResponse response) {
        ArrayList<CandleEntry> yValsCandleStick = new ArrayList<CandleEntry>();

        for (int i = 0; i < response.chart.result.get(0).indicators.quote.get(0).close.size(); i++) {
            float open = response.chart.result.get(0).indicators.quote.get(0).open.get(i).floatValue();
            float close = response.chart.result.get(0).indicators.quote.get(0).close.get(i).floatValue();
            float low = response.chart.result.get(0).indicators.quote.get(0).low.get(i).floatValue();
            float high = response.chart.result.get(0).indicators.quote.get(0).high.get(i).floatValue();
            yValsCandleStick.add(new CandleEntry(i, low, high, open, close));

        }
        CandleDataSet set1 = new CandleDataSet(yValsCandleStick, "Stock Analysis");
        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(getResources().getColor(R.color.loadingColor));
        set1.setShadowWidth(0.8f);
        set1.setDecreasingColor(getResources().getColor(R.color.colorRed));
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(getResources().getColor(R.color.colorGreen));
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        set1.setNeutralColor(Color.LTGRAY);
        set1.setDrawValues(false);
// create a data object with the datasets
        CandleData data = new CandleData(set1);
// set data
        candleStickChart.setData(data);
        candleStickChart.invalidate();

    }


    class RangeAdapter extends RecyclerView.Adapter<RangeAdapter.RangeViewHolder> {

        List<String> ranges;

        public RangeAdapter(List<String> ranges) {
            this.ranges = ranges;
        }

        @NonNull
        @Override
        public RangeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RangeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.range_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RangeViewHolder holder, int position) {
            String selectedRange = StockDetailsFragment.this.getViewModel().range.getValue();
            holder.range.setText(ranges.get(position));
            if (ranges.get(position).equals(selectedRange)) {
                holder.range.setTextColor(ContextCompat.getColor(getContext(), R.color.purple_500));
            } else {
                holder.range.setTextColor(ContextCompat.getColor(getContext(), R.color.mediumEmphasis));
            }
            holder.range.setOnClickListener(v -> StockDetailsFragment.this.getViewModel().range.setValue(ranges.get(position)));
        }

        @Override
        public int getItemCount() {
            return ranges != null ? ranges.size() : 0;
        }

        class RangeViewHolder extends RecyclerView.ViewHolder {
            TextView range;

            public RangeViewHolder(@NonNull View itemView) {
                super(itemView);
                this.range = itemView.findViewById(R.id.value);
            }
        }
    }
}