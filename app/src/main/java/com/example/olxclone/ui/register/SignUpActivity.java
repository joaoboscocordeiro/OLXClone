package com.example.olxclone.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.databinding.ActivitySignUpBinding;

/**
 * Created by João Bosco on 21/09/2022.
 */
public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    public void validate(View view) {

        String name = binding.editName.getText().toString();
        String email = binding.editEmail.getText().toString();
        String phone = binding.editPhone.getText().toString();
        String password = binding.editPassword.getText().toString();

        if (!name.isEmpty()) {
            if (!email.isEmpty()) {
                if (!phone.isEmpty()) {
                    if (!password.isEmpty()) {

                        Toast.makeText(this, "Tudo certo...", Toast.LENGTH_LONG).show();

                    } else {
                        binding.editPassword.requestFocus();
                        binding.editPassword.setError("Informe sua senha!");
                    }
                } else {
                    binding.editPhone.requestFocus();
                    binding.editPhone.setError("Informe seu telefone!");
                }
            } else {
                binding.editEmail.requestFocus();
                binding.editEmail.setError("Informe seu e-mail!");
            }
        } else {
            binding.editName.requestFocus();
            binding.editName.setError("Informe seu nome!");
        }
    }
}