package com.example.appfinalproject_11131415.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfinalproject_11131415.Helper.DBHelper;
import com.example.appfinalproject_11131415.databinding.ActivitySignupBinding;
;


public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DBHelper(this);

        binding.signupButton.setOnClickListener(v -> {
            String name = binding.signupName.getText().toString();
            String phoneNumber = binding.signupMobileNumber.getText().toString();
            String email = binding.signupEmail.getText().toString();
            String password = binding.signupPassword.getText().toString();
            String confirmPassword = binding.confirmPassword.getText().toString();

            if (password.equals(confirmPassword)){
                Boolean checkUserName = dbHelper.checkName(name);

                if (!checkUserName){
                    Boolean insert = dbHelper.insertData(name, phoneNumber, email, password);

                    if (insert){
                        Toast.makeText(SignupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignupActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }

        });
        binding.signInRedirectText.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
        });
        //setContentView(R.layout.activity_signup);
    }
}

