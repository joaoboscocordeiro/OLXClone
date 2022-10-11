package com.example.olxclone.model;

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

    public User() {
    }

    public void save() {
        DatabaseReference userRef = FirebaseHelper.getDatabaseReference();
        userRef.child("usuarios")
                .child(this.getId())
                .setValue(this);
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
}
