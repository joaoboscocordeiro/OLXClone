package com.example.olxclone.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.databinding.FragmentHomeBinding;
import com.example.olxclone.ui.activity.FormAdsActivity;
import com.example.olxclone.ui.login.LoginActivity;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        configClick();

        return binding.getRoot();
    }

    private void configClick() {
        binding.btnFab.setOnClickListener(v -> {
            if (FirebaseHelper.getAuthentication()) {
                startActivity(new Intent(getActivity(), FormAdsActivity.class));
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }
}