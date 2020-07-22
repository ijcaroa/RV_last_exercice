package com.crisspian.recyclerviewexamples_01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crisspian.recyclerviewexamples_01.databinding.ItemListDataBinding;
import com.crisspian.recyclerviewexamples_01.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;
    private Context context;
    private SendItem listener;

    public ItemAdapter(List<Item> itemList, Context context, SendItem sendItem) {
        this.itemList = itemList;
        this.context = context;
        this.listener = sendItem;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListDataBinding binding = ItemListDataBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);


        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Item item = itemList.get(position);
        holder.textView.setText(item.getItemDescription());
        // This add the image on the img view
        Glide.with(context).load(item.getUrlImage()).centerCrop().override(300, 300)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;

        public ItemViewHolder(@NonNull ItemListDataBinding binding) {
            super(binding.getRoot());
            imageView = binding.ivItem;
            textView = binding.tvItem;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.sendItem(itemList.get(getLayoutPosition()));
        }
    }

    public interface SendItem {
        void sendItem(Item item);
    }

}
