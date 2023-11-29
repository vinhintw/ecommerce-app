package com.example.appfinalproject_11131415.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appfinalproject_11131415.Adapter.PopularAdapter;
import com.example.appfinalproject_11131415.Domain.PopularDomain;
import com.example.appfinalproject_11131415.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private RecyclerView.Adapter adapterPopular;
	private RecyclerView recyclerViewPopular;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initRecyclerView();
	}

	private void initRecyclerView() {
		ArrayList<PopularDomain> items = new ArrayList<>();
		items.add(new PopularDomain("T-shirt black", "nvkjnvjkn jvnfskjvndf vkdfnvjkd vdnfkjvndfnj","item_1",15,4, 500));
		items.add(new PopularDomain("title 2", "","coat",15,4, 500));
		items.add(new PopularDomain("title 3", "","pants",15,4, 500));

		recyclerViewPopular = findViewById(R.id.view1);
		recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

		adapterPopular = new PopularAdapter(items);
		recyclerViewPopular.setAdapter(adapterPopular);
	}
}