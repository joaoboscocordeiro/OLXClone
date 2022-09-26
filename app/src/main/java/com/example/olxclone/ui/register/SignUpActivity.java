package com.example.olxclone.ui.register;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.databinding.ActivitySignUpBinding;

/**
 *  Created by João Bosco on 21/09/2022.
 */
public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}