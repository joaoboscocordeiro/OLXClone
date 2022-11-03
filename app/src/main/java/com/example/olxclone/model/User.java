package com.example.olxclone.model;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.olxclone.data.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

/**
 * Created by João Bosco on 30/09/2022.
 */
public class User {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String imageProfile;

    public User() {
    }

    public void save(ProgressBar progressBar, Context context) {
        DatabaseReference userRef = FirebaseHelper.getDatabaseReference();
        userRef.child("usuarios")
                .child(this.getId())
                .setValue(this).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Imagem salva com sucesso...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "ERRO ao salvar imagem...", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}
