package com.example.appfinalproject_11131415.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.appfinalproject_11131415.Activity.Detail.DetailActivity;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.databinding.ViewholderPopListBinding;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    ArrayList<PopularDomain> items;
    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderPopListBinding binding = ViewholderPopListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        holder.bind(items.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("object", items.get(position));
            view.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {
        private ViewholderPopListBinding binding;
        public Viewholder(ViewholderPopListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(PopularDomain item) {
            binding.titleTxt.setText(item.getTitle());
            binding.freeTxt.setText("$" +item.getPrice());
            binding.scoreTxt.setText(""+item.getScore());
            binding.reviewTxt.setText(""+item.getReview());
            int drawableResourceId = itemView.getResources().getIdentifier(item.getPicUrl(),
                    "drawable", itemView.getContext().getPackageName());
            Glide.with(itemView.getContext()).load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.pic);
        }
    }
}
