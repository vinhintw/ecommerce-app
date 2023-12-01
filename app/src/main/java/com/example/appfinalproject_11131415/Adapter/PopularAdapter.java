package com.example.appfinalproject_11131415.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.appfinalproject_11131415.Activity.DetailActivity;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {

    ArrayList<PopularDomain> items;
    Context context;

    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_pup_list, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.freeTxt.setText("$" +items.get(position).getPrice());
        holder.scoreTxt.setText(""+items.get(position).getScore());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(holder.pic);

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

        TextView titleTxt, freeTxt, scoreTxt;
        ImageView pic;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            freeTxt = itemView.findViewById(R.id.freeTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
