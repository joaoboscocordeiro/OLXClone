package com.example.olxclone.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.databinding.ActivityFormAdsBinding;

public class FormAdsActivity extends AppCompatActivity {

    private ActivityFormAdsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormAdsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarFormAds.textToolbarTitle.setText(R.string.toolbar_form_ads_title);
        binding.toolbarFormAds.imbBack.setOnClickListener(v -> finish());
    }
}