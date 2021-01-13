package com.crisspian.recyclerviewexamples_01.adapter;

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


    private List<Item> mItem;
    private IpassElement ipassElement;
    public ItemAdapter(List<Item> mItem, IpassElement ipassElement){
        this.mItem = mItem;
        this.ipassElement = ipassElement;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemListDataBinding
                binding = ItemListDataBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = mItem.get(position);
        holder.text.setText(item.getItemDescription());
        Glide.with(holder.itemView).load(item.getUrlImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView text;


    public ItemViewHolder(@NonNull ItemListDataBinding Binding) {

        super(Binding.getRoot());
        image = Binding.ivItem;
        text = Binding.tvItem;
        itemView.setOnClickListener(this);

    }


        @Override
        public void onClick(View v) {
        int position = getLayoutPosition();
        Item item = mItem.get(position);
        ipassElement.passElement(item);

        }
    }
        public interface IpassElement {
        void passElement (Item item);
        }

}

