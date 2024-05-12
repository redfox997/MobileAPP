package com.example.mobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Filterable {
    private ArrayList<Items> itemsData;
    private ArrayList<Items> ItemsDataAll;
    private Context context;
    private int lastPosition = -1;


    ItemAdapter(Context context, ArrayList<Items> itemsData) {
        this.itemsData = itemsData;
        this.ItemsDataAll = itemsData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Items items = itemsData.get(position);

        holder.bindTo(items);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    @Override
    public Filter getFilter() {

        return itemFilter;
    }
    private Filter itemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Items> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0){
                results.count = ItemsDataAll.size();
                results.values = ItemsDataAll;
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Items items : ItemsDataAll){
                    if (items.getModel().toLowerCase().contains(filterPattern)){
                        filteredList.add(items);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemsData = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView tImage;
        private TextView tBrand;
        private TextView tModel;
        private TextView tBancmark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tImage = itemView.findViewById(R.id.itemImage);
            tBrand = itemView.findViewById(R.id.itemBrand);
            tModel = itemView.findViewById(R.id.itemModel);
            tBancmark = itemView.findViewById(R.id.itemBancmarkPoint);


        }

        public void bindTo(Items items) {
            tBrand.setText(items.getBrand());
            tModel.setText(items.getModel());
            tBancmark.setText(items.getBancmarkPoint());

            Glide.with(context).load(items.getImageResource()).into(tImage);
        }
    }
}
