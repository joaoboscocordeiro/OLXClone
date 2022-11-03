package com.example.olxclone.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.databinding.ActivityProfileBinding;
import com.example.olxclone.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by João Bosco on 26/10/2022.
 */
public class ProfileActivity extends AppCompatActivity {

    private static final int PERMISSION_GALLERY = 100;
    private ActivityProfileBinding binding;
    private String imgPath;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarCreateAccount.textToolbarTitle.setText(R.string.toolbar_profile_title);

        configClicks();
        getProfile();
    }

    private void saveImageProfile() {
        StorageReference storageReference = FirebaseHelper.getStorageReference()
                .child("imagens")
                .child("perfil")
                .child(FirebaseHelper.getIdFirebase() + ".jpeg");

        UploadTask uploadTask = storageReference.putFile(Uri.parse(imgPath));
        uploadTask.addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl().addOnCompleteListener(task -> {

            String urlImage = task.getResult().toString();
            user.setImageProfile(urlImage);
            user.save(binding.progressBar, getBaseContext());

        })).addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
    }

    private void getProfile() {
        binding.progressBar.setVisibility(View.VISIBLE);
        DatabaseReference profileRef = FirebaseHelper.getDatabaseReference()
                .child("usuarios")
                .child(FirebaseHelper.getIdFirebase());
        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                getData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getData() {
        binding.editName.setText(user.getName());
        binding.editPhone.setText(user.getPhone());
        binding.editEmail.setText(user.getEmail());

        binding.progressBar.setVisibility(View.GONE);

        if (user.getImageProfile() != null) {
            Picasso.get().load(user.getImageProfile()).into(binding.imgProfile);
        }
    }

    public void validate(View view) {
        String name = binding.editName.getText().toString();
        String phone = binding.editPhone.getUnMasked();

        if (!name.isEmpty()) {
            if (!phone.isEmpty()) {
                if (phone.length() == 11) {

                    binding.progressBar.setVisibility(View.VISIBLE);
                    if (imgPath != null) {
                        saveImageProfile();
                    } else {
                        user.save(binding.progressBar, getBaseContext());
                    }
                } else {
                    binding.editPhone.requestFocus();
                    binding.editPhone.setError("Preencha um número de telefone válido.");
                }
            } else {
                binding.editPhone.requestFocus();
                binding.editPhone.setError("Preencha o telefone.");
            }
        } else {
            binding.editName.requestFocus();
            binding.editName.setError("Preencha seu nome.");
        }
    }

    private void configClicks() {
        binding.imgProfile.setOnClickListener(v -> permissionGallery());
    }

    private void permissionGallery() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openGallery();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ProfileActivity.this, "Permissões negadas.", Toast.LENGTH_LONG).show();
            }
        };

        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedTitle("Permissões negadas!")
                .setDeniedMessage("Se você não aceitar a permissão não poderá acessar a Galeria do dispositivo, deseja ativar a permissão agora?")
                .setDeniedCloseButtonText("Não")
                .setGotoSettingButtonText("Sim")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PERMISSION_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri imgSelected = data.getData();
            Bitmap imgBitmap;

            try {
                imgBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgSelected);
                binding.imgProfile.setImageBitmap(imgBitmap);

                imgPath = imgSelected.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}