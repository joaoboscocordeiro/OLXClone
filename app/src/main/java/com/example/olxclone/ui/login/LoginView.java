package com.example.olxclone.ui.login;

import com.example.olxclone.util.View;

/**
 * Created by João Bosco on 16/11/2022.
 */
public interface LoginView extends View {
    void onFailureForm(String emailError, String passwordError);
    void onUserLogged();
}
