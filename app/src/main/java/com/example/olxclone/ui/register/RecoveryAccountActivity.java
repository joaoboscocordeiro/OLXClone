package com.example.olxclone.ui.register;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.databinding.ActivityRecoveryAccountBinding;

/**
 *  Created by João Bosco on 21/09/2022.
 */
public class RecoveryAccountActivity extends AppCompatActivity {

    private ActivityRecoveryAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecoveryAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}