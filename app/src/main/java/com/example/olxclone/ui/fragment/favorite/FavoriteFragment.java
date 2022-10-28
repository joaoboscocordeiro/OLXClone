package com.example.olxclone.ui.fragment.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.olxclone.databinding.FragmentFavoriteBinding;


public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate (inflater, container, false);

        return binding.getRoot();
    }
}