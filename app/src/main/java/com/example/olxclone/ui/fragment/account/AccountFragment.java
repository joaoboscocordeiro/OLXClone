package com.example.olxclone.ui.fragment.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.olxclone.databinding.FragmentAccountBinding;
import com.example.olxclone.ui.activity.AddressActivity;
import com.example.olxclone.ui.activity.ProfileActivity;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        configClick();

        return binding.getRoot();
    }

    private void configClick() {

        binding.clProfile.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ProfileActivity.class));
        });
        binding.clAddress.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddressActivity.class));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}