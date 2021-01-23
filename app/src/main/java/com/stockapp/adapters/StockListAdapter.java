package com.stockapp.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.stockapp.R;
import com.stockapp.databinding.StockListItemBinding;
import com.stockapp.models.StockListItem;
import com.stockapp.ui.activities.StockDetailsActivity;

import javax.inject.Inject;

public class StockListAdapter extends PagedListAdapter<StockListItem, StockListAdapter.StockListViewHolder> {

    private static final String POST_DATA = "POST_DATA";
    private static DiffUtil.ItemCallback<StockListItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<StockListItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull StockListItem oldItem, @NonNull StockListItem newItem) {
            return !oldItem.getId().equals(newItem.getId());
        }

        //todo add equality check
        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull StockListItem oldItem, @NonNull StockListItem newItem) {
            return false;
        }
    };

    @Inject
    public StockListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public StockListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StockListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.stock_list_item, parent, false);
        return new StockListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StockListViewHolder holder, int position) {

        if (getItem(position) != null) {
            holder.binding.setStock(getItem(position));
            holder.itemView.setOnClickListener(v ->
            {
                Intent intent = new Intent(v.getContext(), StockDetailsActivity.class);
                intent.putExtra(POST_DATA, getItem(position));
                v.getContext().startActivity(intent);
            });
//            StockListItem stockListItem = getItem(position);
//            if (stockListItem.getChange() >= 0) {
//                holder.binding.triangle.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.green_triangle));
////                holder.binding.change.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorGreen));
//            } else {
////                holder.binding.change.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorRed));
//                holder.binding.triangle.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.red_triangle));
//
//            }

        }
    }

    static class StockListViewHolder extends RecyclerView.ViewHolder {

        StockListItemBinding binding;

        StockListViewHolder(@NonNull StockListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
