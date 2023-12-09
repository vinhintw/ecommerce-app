package com.example.appfinalproject_11131415.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.Model.ChangeNumberItemsListener;
import com.example.appfinalproject_11131415.Model.ManagementCart;
import com.example.appfinalproject_11131415.R;
import com.example.appfinalproject_11131415.databinding.ViewholderCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
        private ArrayList<PopularDomain> listItemSelected;
        private ChangeNumberItemsListener changeNumberItemsListener;
        private ManagementCart managementCart;
    public CartAdapter(ArrayList<PopularDomain> listItemSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener)  {
        this.listItemSelected = listItemSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }
    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        holder.bind(listItemSelected.get(position));
        holder.binding.plusCartBtn.setOnClickListener(v -> managementCart.plusNumberItem(listItemSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
        holder.binding.minusCartBtn.setOnClickListener(v -> managementCart.minusNumberItem(listItemSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }
    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        private final ViewholderCartBinding binding;
        public Viewholder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(PopularDomain item) {
            binding.titleTxt3.setText(item.getTitle());
            binding.feeEachItem.setText("$" + item.getPrice());
            binding.totalEachItem.setText(("$" + item.getNumberInCart()*item.getPrice()));
            binding.numberItemTxt.setText(String.valueOf(item.getNumberInCart()));
            int drawableResourceId = itemView.getContext().getResources().getIdentifier(item.getPicUrl(),
                "drawable", itemView.getContext().getPackageName());
            Glide.with(itemView.getContext()).load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30)).into(binding.picincart);
        }
    }
}
