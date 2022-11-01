package com.example.olxclone.model;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.olxclone.data.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

/**
 * Created by João Bosco on 31/10/2022.
 */
public class Address {

    private String cep;
    private String uf;
    private String county;
    private String district;

    public Address() {
    }

    public void save(String idUser, Context context, ProgressBar progressBar) {
        DatabaseReference addressRef = FirebaseHelper.getDatabaseReference()
                .child("enderecos")
                .child(idUser);
        addressRef.setValue(this).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Endereço salvo com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.GONE);
        });
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
