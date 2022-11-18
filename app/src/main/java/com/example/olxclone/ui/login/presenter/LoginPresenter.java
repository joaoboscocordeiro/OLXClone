package com.example.olxclone.ui.login.presenter;

import com.example.olxclone.ui.login.LoginView;
import com.example.olxclone.ui.login.datasource.LoginDataSource;
import com.example.olxclone.util.Presenter;

/**
 * Created by João Bosco on 18/11/2022.
 */
public class LoginPresenter implements Presenter {

    private final LoginView view;
    private final LoginDataSource dataSource;

    public LoginPresenter(LoginView view, LoginDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void login(String email, String password) {
        view.showProgressBar();
        dataSource.login(email, password, this);
    }

    @Override
    public void onSuccess() {
        view.onUserLogged();
    }

    @Override
    public void onError(String message) {
        view.onFailureForm(message, message);
    }

    @Override
    public void onComplete() {
        view.hideProgressBar();
    }
}
