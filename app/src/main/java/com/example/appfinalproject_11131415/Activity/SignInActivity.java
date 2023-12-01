package com.example.appfinalproject_11131415.Activity;

import android.content.Intent;
import android.health.connect.datatypes.StepsRecord;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfinalproject_11131415.Helper.DBHelper;
import com.example.appfinalproject_11131415.databinding.ActivitySigninBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySigninBinding binding;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelper(this);

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.signInUserName.getText().toString();
                String password = binding.signInPassword.getText().toString();

                if (name.equals("") || password.equals("")){
                    Toast.makeText(SignInActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkCredentials = dbHelper.checkNamePassword(name, password);

                    if (checkCredentials){
                        Toast.makeText(SignInActivity.this, "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.signupRedirectTxt.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}