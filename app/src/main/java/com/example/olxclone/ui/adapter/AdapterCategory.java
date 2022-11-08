package com.example.olxclone.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.olxclone.R;
import com.example.olxclone.model.Category;

import java.util.List;

/**
 * Created by João Bosco on 07/11/2022.
 */
public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.MyViewHolder> {

    private final List<Category> categoryList;
    private final OnClickListener onClickListener;

    public AdapterCategory(List<Category> categoryList, OnClickListener onClickListener) {
        this.categoryList = categoryList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Category category = categoryList.get(position);

        holder.img_category.setImageResource(category.getPath());
        holder.txt_category.setText(category.getName());

        holder.itemView.setOnClickListener(v -> onClickListener.onClick(category));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public interface OnClickListener {
        void onClick(Category category);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_category;
        TextView txt_category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_category = itemView.findViewById(R.id.img_category);
            txt_category = itemView.findViewById(R.id.txt_category);
        }
    }
}
