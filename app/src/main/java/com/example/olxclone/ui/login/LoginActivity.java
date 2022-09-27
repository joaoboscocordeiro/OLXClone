package com.example.olxclone.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.databinding.ActivityLoginBinding;
import com.example.olxclone.ui.register.SignUpActivity;

/**
 *  Created by João Bosco on 21/09/2022.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLogin.textToolbarTitle.setText(R.string.txt_login_toolbar_title);

        binding.textLoginRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}