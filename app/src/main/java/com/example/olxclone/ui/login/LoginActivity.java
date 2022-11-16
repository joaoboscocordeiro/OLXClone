package com.example.olxclone.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.databinding.ActivityLoginBinding;
import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.ui.register.RecoveryAccountActivity;
import com.example.olxclone.ui.register.SignUpActivity;
import com.example.olxclone.ui.activity.MainActivity;

/**
 * Created by João Bosco on 21/09/2022.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarLogin.textToolbarTitle.setText(R.string.txt_login_toolbar_title);

        binding.editEmailLogin.addTextChangedListener(watcher);
        binding.editPasswordLogin.addTextChangedListener(watcher);

        binding.textLoginRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
        binding.textLoginRecoverPassword.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecoveryAccountActivity.class);
            startActivity(intent);
        });
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().isEmpty())
                binding.btnSignInLogin.setEnabled(true);
            else
                binding.btnSignInLogin.setEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    public void validate(View view) {

        String email = binding.editEmailLogin.getText().toString();
        String password = binding.editPasswordLogin.getText().toString();

        if (!email.isEmpty()) {
            if (!password.isEmpty()) {

                binding.progressBar.setVisibility(View.VISIBLE);
                logar(email, password);

            } else {
                binding.editPasswordLogin.requestFocus();
                binding.editPasswordLogin.setError("Informe sua senha.");
            }
        } else {
            binding.editEmailLogin.requestFocus();
            binding.editEmailLogin.setError("Informe seu email.");
        }
    }

    private void logar(String email, String password) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
                email, password
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                Toast.makeText(this, "Login efetuado com sucesso.", Toast.LENGTH_LONG).show();
            } else {
                String error = FirebaseHelper.validFirebase(task.getException().getMessage());
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }
}