package com.example.appfinalproject_11131415.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        binding.signInButton.setOnClickListener(v -> {
            String name = binding.signInUserName.getText().toString();
            String password = binding.signInPassword.getText().toString();

            if (name.equals("") || password.equals("")){
                Toast.makeText(SignInActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkCredentials = dbHelper.checkNamePassword(name, password);

                if (checkCredentials){
                    SharedPreferences sharedPreferences = getSharedPreferences("user_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.putString("userName", name); //put userName
                    editor.apply();

                    Toast.makeText(SignInActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.signupRedirectTxt.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, SignupActivity.class)));
        binding.signInBackBtn.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, MainActivity.class)));
    }
}