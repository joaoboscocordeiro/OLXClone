package com.example.olxclone.ui.login.datasource;

import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.util.Presenter;

/**
 * Created by João Bosco on 18/11/2022.
 */
public class LoginRemoteDataSource implements LoginDataSource {

    @Override
    public void login(String email, String password, Presenter presenter) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
                email, password
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                presenter.onSuccess();
            } else {
                String error = FirebaseHelper.validFirebase(task.getException().getMessage());
                presenter.onError(error);
            }
            presenter.onComplete();
        });
    }
}
