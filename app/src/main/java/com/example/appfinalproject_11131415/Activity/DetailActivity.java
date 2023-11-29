package com.example.appfinalproject_11131415.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.Helper.ManagementCart;
import com.example.appfinalproject_11131415.R;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt ;
    private ImageView picItem, backBtn;
    private ManagementCart managementCart;
    private int numberOder = 1;
    private PopularDomain object;
    Button addToCartBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        managementCart = new ManagementCart(this);

        initView();
        //getBundle();
    }

    private void getBundle() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(picItem);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview());
        scoreTxt.setText(object.getScore() + "");

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberCart(numberOder);
            managementCart.insertFood(object);

        });
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        feeTxt = findViewById(R.id.priceTxt2);
        titleTxt = findViewById(R.id.titleTxt2);
        descriptionTxt = findViewById(R.id.descriptionTxt2);
        picItem = findViewById(R.id.item_pic);
        reviewTxt = findViewById(R.id.reviewTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        backBtn = findViewById(R.id.back_btn);
        addToCartBtn = findViewById(R.id.addToCartBtn);
    }

}
