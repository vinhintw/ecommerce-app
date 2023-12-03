package com.example.appfinalproject_11131415.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfinalproject_11131415.Domain.User;
import com.example.appfinalproject_11131415.Helper.DBHelper;
import com.example.appfinalproject_11131415.R;
import com.example.appfinalproject_11131415.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

	ActivityProfileBinding binding;

	private String userName;
    DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityProfileBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		Intent intent = getIntent();
		userName = intent.getStringExtra("userName");
		dbHelper = new DBHelper(this);
		User user = dbHelper.get(userName);
		bindingData(user);

	}

	private void bindingData(User user) {
		binding.profileUserNameTxt.setText(user.getName());
		binding.profileEmailTxt.setText(user.getEmail());
		binding.mobileTxt.setText(user.getMobile());
		binding.addressTxt.setText(user.getAddress());
		binding.postalCodeTxt.setText(user.getPostalCode());

		binding.ProfileBackBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
		binding.logoutBtn.setOnClickListener(v -> {
			SharedPreferences sharedPreferences = getSharedPreferences("user_pref", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.clear(); // clear log in data
			editor.apply();
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
		});

	}
}