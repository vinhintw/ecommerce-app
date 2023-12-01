package com.example.appfinalproject_11131415.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.Helper.ManagementCart;
import com.example.appfinalproject_11131415.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    ImageView picItem;
    private ManagementCart managementCart;
    private int numberOder = 1;
    private PopularDomain object;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managementCart = new ManagementCart(this);
        getBundle();
    }

    private void getBundle() {

        object = (PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(binding.itemPic);
        binding.titleTxt2.setText(object.getTitle());
        binding.priceTxt2.setText("$" + object.getPrice());
        binding.descriptionTxt2.setText(object.getDescription());
        binding.reviewTxt.setText(object.getReview() + "");
        binding.scoreTxt.setText(object.getScore() + "");

        binding.addToCartBtn.setOnClickListener(v -> {
            object.setNumberCart(numberOder);
            managementCart.insertFood(object);

        });
        binding.backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
        });
    }

}
