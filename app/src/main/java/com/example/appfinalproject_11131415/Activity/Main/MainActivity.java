package com.example.appfinalproject_11131415.Activity.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfinalproject_11131415.Activity.Authentication.SignInActivity;
import com.example.appfinalproject_11131415.Activity.Cart.CartActivity;
import com.example.appfinalproject_11131415.Activity.Profile.ProfileActivity;
import com.example.appfinalproject_11131415.Adapter.PopularAdapter;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	private RecyclerView.Adapter adapterPopular;
	private static final String JSON_FILE_NAME = "clothing_data.json";
	private static String UserName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		loginHandling();
		initRecyclerView();
		bottomNavigation();
	}
	private void loginHandling() {
		SharedPreferences sharedPreferences = getSharedPreferences("user_pref", Context.MODE_PRIVATE);
		boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
		if (!isLoggedIn) {
			startActivity(new Intent(this, SignInActivity.class));
		} else {
			UserName = sharedPreferences.getString("userName", "");
			// ...
			binding.userNameTxt.setText(UserName);
		}
	}
	private void bottomNavigation() {
		binding.homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
		binding.cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
		binding.profileBtn.setOnClickListener(v ->
				startActivity(new Intent(MainActivity.this, ProfileActivity.class)
						.putExtra("userName", UserName)));
	}
	private void initRecyclerView() {
		binding.view1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
		adapterPopular = new PopularAdapter(loadItem());
		binding.view1.setAdapter(adapterPopular);
	}

	private ArrayList<PopularDomain> loadItem() {
		ArrayList<PopularDomain> items = new ArrayList<>();
		try {
			JSONArray jsonArray = new JSONArray(loadJsonData(JSON_FILE_NAME).toString());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String title = jsonObject.getString("title");
				String description = jsonObject.getString("description");
				String type = jsonObject.getString("type");
				int review = jsonObject.getInt("review");
				int rating = jsonObject.getInt("rating");
				double price = jsonObject.getDouble("price");

				PopularDomain popularDomain = new PopularDomain(title, description, type, review, rating, price);
				items.add(popularDomain);
				Log.d("msg", items.get(i).getPicUrl() + " " + items.size());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return items;
	}
	private StringBuilder loadJsonData(String jsonFileName) {
		try {
			InputStream inputStream = getAssets().open(jsonFileName);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			inputStream.close();
			return stringBuilder;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}