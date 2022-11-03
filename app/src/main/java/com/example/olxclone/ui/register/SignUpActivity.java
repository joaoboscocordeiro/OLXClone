package com.example.olxclone.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.databinding.ActivitySignUpBinding;
import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.model.User;
import com.example.olxclone.ui.activity.MainActivity;

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

        binding.toolbarCreateAccount.textToolbarTitle.setText(R.string.txt_sing_up_toolbar_title);
        binding.toolbarCreateAccount.imbBack.setOnClickListener(v -> finish());

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

                        binding.progressBar.setVisibility(View.VISIBLE);

                        User user = new User();
                        user.setName(name);
                        user.setEmail(email);
                        user.setPhone(phone);
                        user.setPassword(password);

                        createUser(user);

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

    private void createUser(User user) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String idUser = task.getResult().getUser().getUid();
                user.setId(idUser);
                user.save(binding.progressBar, getBaseContext());

                startActivity(new Intent(this, MainActivity.class));
                finish();
                binding.progressBar.setVisibility(View.GONE);
            } else {
                String error = FirebaseHelper.validFirebase(task.getException().getMessage());
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });
    }
}