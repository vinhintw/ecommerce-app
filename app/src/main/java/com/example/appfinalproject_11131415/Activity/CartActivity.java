package com.example.appfinalproject_11131415.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.example.appfinalproject_11131415.Adapter.CartAdapter;
import com.example.appfinalproject_11131415.Helper.ManagementCart;
import com.example.appfinalproject_11131415.R;
import com.example.appfinalproject_11131415.databinding.ActivityCartBinding;
import com.example.appfinalproject_11131415.databinding.ActivityDetailBinding;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ScrollView scrollView;
    private ManagementCart managementCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managementCart = new ManagementCart(this);

        recyclerView = binding.view2;
        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if (managementCart.getlistCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.backEmptyBtn.setVisibility(View.VISIBLE);
            binding.scrollView2.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.backEmptyBtn.setVisibility(View.GONE);
            binding.scrollView2.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CartAdapter(managementCart.getlistCart(), this, () -> calculateCart());
        recyclerView.setAdapter(adapter);

    }

    private void calculateCart(){
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round(managementCart.getTotalFee()*percentTax*100.0) / 100.0;

        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100 / 100);
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.DeliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }
    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });
        binding.backEmptyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }



}