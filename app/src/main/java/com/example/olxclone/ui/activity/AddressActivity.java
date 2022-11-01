package com.example.olxclone.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.databinding.ActivityAddressBinding;
import com.example.olxclone.model.Address;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class AddressActivity extends AppCompatActivity {

    private ActivityAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarAddress.textToolbarTitle.setText(R.string.toolbar_address);

        recoverAddress();
    }

    public void validate(View view) {
        String cep = binding.editAddressCep.getText().toString();
        String uf = binding.editAddressUf.getText().toString();
        String county = binding.editAddressCity.getText().toString();
        String district = binding.editAddressDistrict.getText().toString();

        if (!cep.isEmpty()) {
            if (!uf.isEmpty()) {
                if (!county.isEmpty()) {
                    if (!district.isEmpty()) {

                        binding.progressBar.setVisibility(View.VISIBLE);

                        Address address = new Address();
                        address.setCep(cep);
                        address.setUf(uf);
                        address.setCounty(county);
                        address.setDistrict(district);
                        address.save(FirebaseHelper.getIdFirebase(), getBaseContext(), binding.progressBar);

                    } else {
                        binding.editAddressDistrict.requestFocus();
                        binding.editAddressDistrict.setError("Preencha seu bairro.");
                    }
                } else {
                    binding.editAddressCity.requestFocus();
                    binding.editAddressCity.setError("Preencha seu município.");
                }
            } else {
                binding.editAddressUf.requestFocus();
                binding.editAddressUf.setError("Preencha o UF.");
            }
        } else {
            binding.editAddressCep.requestFocus();
            binding.editAddressCep.setError("Preencha o CEP.");
        }
    }

    private void recoverAddress() {
        binding.progressBar.setVisibility(View.VISIBLE);
        DatabaseReference addressRef = FirebaseHelper.getDatabaseReference()
                .child("enderecos")
                .child(FirebaseHelper.getIdFirebase());
        addressRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Address address = snapshot.getValue(Address.class);
                    configAddress(address);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void configAddress(Address address) {
        binding.editAddressCep.setText(address.getCep());
        binding.editAddressUf.setText(address.getUf());
        binding.editAddressCity.setText(address.getCounty());
        binding.editAddressDistrict.setText(address.getDistrict());

        binding.progressBar.setVisibility(View.GONE);
    }
}