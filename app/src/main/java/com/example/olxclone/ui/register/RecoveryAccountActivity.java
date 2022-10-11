package com.example.olxclone.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.databinding.ActivityRecoveryAccountBinding;
import com.example.olxclone.data.FirebaseHelper;

/**
 *  Created by João Bosco on 21/09/2022.
 */
public class RecoveryAccountActivity extends AppCompatActivity {

    private ActivityRecoveryAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecoveryAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbarRecovery.textToolbarTitle.setText(R.string.txt_recovery_toolbar_title);
        binding.toolbarRecovery.imbBack.setOnClickListener(v -> finish());
    }

    public void validate(View view) {

        String email = binding.editEmail.getText().toString();

        if (!email.isEmpty()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            sendEmail(email);
        } else {
            binding.editEmail.requestFocus();
            binding.editEmail.setError("Informe seu email!");
        }
    }

    private void sendEmail(String email) {
        FirebaseHelper.getAuth().sendPasswordResetEmail(
                email
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Verifique a caixa de entrada do seu e-mail", Toast.LENGTH_LONG).show();
            } else {
                String error = FirebaseHelper.validFirebase(task.getException().getMessage());
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }
}