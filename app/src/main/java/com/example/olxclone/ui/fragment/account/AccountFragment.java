package com.example.olxclone.ui.fragment.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.olxclone.R;
import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.databinding.FragmentAccountBinding;
import com.example.olxclone.model.User;
import com.example.olxclone.ui.activity.AddressActivity;
import com.example.olxclone.ui.activity.MainActivity;
import com.example.olxclone.ui.activity.ProfileActivity;
import com.example.olxclone.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private User user;

    @BindView(R.id.txt_account) TextView txtAccount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);

        configClick();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        getUser();
    }

    private void getUser() {
        if (FirebaseHelper.getAuthentication()) {
            DatabaseReference userRef = FirebaseHelper.getDatabaseReference()
                    .child("usuarios")
                    .child(FirebaseHelper.getIdFirebase());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.getValue(User.class);
                    configAccount();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
    }

    private void configAccount() {
        binding.txtAccountNameUser.setText(user.getName());

        if (user.getImageProfile() != null) {
            Picasso.get().load(user.getImageProfile())
                    .placeholder(R.drawable.progress)
                    .into(binding.imgAccountProfile);
        }
    }

    private void configClick() {

        binding.clProfile.setOnClickListener(v -> redirectUser(ProfileActivity.class));
        binding.clAddress.setOnClickListener(v -> redirectUser(AddressActivity.class));

        if (FirebaseHelper.getAuthentication()) {
            binding.txtAccount.setText("Sair");
        } else {
            binding.txtAccount.setText("Clique aqui");
        }

        binding.txtAccount.setOnClickListener(v -> {
            if (FirebaseHelper.getAuthentication()) {
                FirebaseHelper.getAuth().signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    private void redirectUser(Class<?> aClass) {
        if (FirebaseHelper.getAuthentication()) {
            startActivity(new Intent(getActivity(), aClass));
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}