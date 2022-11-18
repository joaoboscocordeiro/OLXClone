package com.example.olxclone.ui.login.datasource;

import com.example.olxclone.util.Presenter;

/**
 * Created by João Bosco on 18/11/2022.
 */
public interface LoginDataSource {
    void login(String email, String password, Presenter presenter);
}
