package com.stockapp.adapters;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.stockapp.R;

import java.util.List;

public class StockDetailsMetaDataAdapter extends RecyclerView.Adapter<StockDetailsMetaDataAdapter.MetaDataViewHolder> {

    List<Pair<String, String>> data;


    public StockDetailsMetaDataAdapter() {
    }

    public void setData(List<Pair<String, String>> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MetaDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MetaDataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meta_data_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MetaDataViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.loadingColor));
        } else
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
        holder.left.setText(data.get(position).first + "");
        holder.right.setText(data.get(position).second + "");

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class MetaDataViewHolder extends RecyclerView.ViewHolder {

        TextView left, right;

        public MetaDataViewHolder(@NonNull View itemView) {
            super(itemView);
            left = itemView.findViewById(R.id.left);
            right = itemView.findViewById(R.id.right);
        }
    }
}
