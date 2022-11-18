package com.example.olxclone.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.olxclone.R;
import com.example.olxclone.databinding.ActivityLoginBinding;
import com.example.olxclone.ui.activity.AbstractActivity;
import com.example.olxclone.ui.activity.MainActivity;
import com.example.olxclone.ui.login.datasource.LoginDataSource;
import com.example.olxclone.ui.login.datasource.LoginRemoteDataSource;
import com.example.olxclone.ui.login.presenter.LoginPresenter;
import com.example.olxclone.ui.register.RecoveryAccountActivity;
import com.example.olxclone.ui.register.SignUpActivity;

/**
 * Created by João Bosco on 21/09/2022.
 */
public class LoginActivity extends AbstractActivity implements LoginView, TextWatcher {

    private ActivityLoginBinding binding;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLogin.textToolbarTitle.setText(R.string.txt_login_toolbar_title);
        binding.toolbarLogin.imbBack.setOnClickListener(v -> finish());

        binding.editEmailLogin.addTextChangedListener(this);
        binding.editPasswordLogin.addTextChangedListener(this);

        binding.textLoginRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
        binding.textLoginRecoverPassword.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecoveryAccountActivity.class);
            startActivity(intent);
        });

        binding.btnSignInLogin.setOnClickListener(v -> {
            presenter.login(binding.editEmailLogin.getText().toString(), binding.editPasswordLogin.getText().toString());
        });
    }

    @Override
    protected void onInject() {
        LoginDataSource dataSource = new LoginRemoteDataSource();
        presenter = new LoginPresenter(this, dataSource);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!binding.editEmailLogin.getText().toString().isEmpty()
                && !binding.editPasswordLogin.getText().toString().isEmpty())
            binding.btnSignInLogin.setEnabled(true);
        else
            binding.btnSignInLogin.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailureForm(String emailError, String passwordError) {
        if (emailError != null) {
            binding.editEmailLogin.requestFocus();
            binding.editEmailLogin.setError(emailError);
        }
        if (passwordError != null) {
            binding.editPasswordLogin.requestFocus();
            binding.editPasswordLogin.setError(passwordError);
        }
    }

    @Override
    public void onUserLogged() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        Toast.makeText(this, "Login efetuado com sucesso.", Toast.LENGTH_LONG).show();
    }

}