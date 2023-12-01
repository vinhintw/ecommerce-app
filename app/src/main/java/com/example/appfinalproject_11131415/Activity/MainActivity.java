package com.example.appfinalproject_11131415.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfinalproject_11131415.Adapter.PopularAdapter;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private RecyclerView.Adapter adapterPopular;
	private RecyclerView recyclerViewPopular;
	private static final String JSON_FILE_NAME = "clothing_data.json";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initRecyclerView();
		bottomNavigation();
	}

	private void bottomNavigation() {
		LinearLayout homeBtn = findViewById(R.id.homeBtn);
		LinearLayout cartBtn = findViewById(R.id.cartBtn);

		homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));

		cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
	}

	private void initRecyclerView() {
		ArrayList<PopularDomain> items = new ArrayList<>();

		try {
			InputStream inputStream = getAssets().open(JSON_FILE_NAME);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			inputStream.close();

			JSONArray jsonArray = new JSONArray(stringBuilder.toString());
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

		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}

		recyclerViewPopular = findViewById(R.id.view1);
		recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
		adapterPopular = new PopularAdapter(items);
		recyclerViewPopular.setAdapter(adapterPopular);
	}

}