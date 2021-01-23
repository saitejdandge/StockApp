package com.stockapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
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

    SharedPreferences sharedPreferences;

    @Inject
    public StockListAdapter(Context context) {
        super(DIFF_CALLBACK);
        sharedPreferences = context.getSharedPreferences("list", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public StockListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StockListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.stock_list_item, parent, false);
        return new StockListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StockListViewHolder holder, int position) {

        StockListItem stockListItem = getItem(position);
        if (stockListItem != null) {
            holder.binding.setStock(stockListItem);
            holder.itemView.setOnClickListener(v ->
            {
                Intent intent = new Intent(v.getContext(), StockDetailsActivity.class);
                intent.putExtra(POST_DATA, getItem(position));
                v.getContext().startActivity(intent);
            });
            if (this.sharedPreferences.getAll().keySet() != null && this.sharedPreferences.getAll().containsKey(stockListItem.getId() + "")) {
                holder.binding.add.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_done_black_18dp));
                holder.binding.add.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.rounded_rectangle_selected));
                holder.binding.add.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                holder.binding.add.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_add_black_18dp));
                holder.binding.add.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.rounded_rectangle));
                holder.binding.add.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);

            }
            holder.binding.add.setOnClickListener(v -> {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                StockListItem stockListItem1 = getItem(position);
                if (this.sharedPreferences.getAll().keySet() != null && this.sharedPreferences.getAll().containsKey(stockListItem1.getId() + "")) {
                    editor.remove(stockListItem1.getId() + "");
                } else {
                    editor.putString(stockListItem1.getId() + "", new Gson().toJson(stockListItem1));
                }
                editor.apply();
                notifyItemChanged(position);
            });
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
