package com.example.olxclone.ui.activity;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.R;
import com.example.olxclone.api.CEPService;
import com.example.olxclone.data.FirebaseHelper;
import com.example.olxclone.databinding.ActivityFormAdsBinding;
import com.example.olxclone.model.Address;
import com.example.olxclone.model.Category;
import com.example.olxclone.model.Cep;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormAdsActivity extends AppCompatActivity {

    private static final int REQUEST_CATEGORY = 100;
    private ActivityFormAdsBinding binding;
    private String selectCategory;
    private Address addressUser;
    private Cep cepLocal;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormAdsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarFormAds.textToolbarTitle.setText(R.string.toolbar_form_ads_title);
        binding.toolbarFormAds.imbBack.setOnClickListener(v -> finish());

        initRetrofit();
        getAddress();
        configClick();
    }

    private void configClick() {
        binding.imgFormAds0.setOnClickListener(v -> showBottomSheet(0));
        binding.imgFormAds1.setOnClickListener(v -> showBottomSheet(1));
        binding.imgFormAds2.setOnClickListener(v -> showBottomSheet(2));
    }

    public void getCategory(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);

        startActivityForResult(intent, REQUEST_CATEGORY);
    }

    private void getAddress() {
        configCEP();
        DatabaseReference addressRef = FirebaseHelper.getDatabaseReference()
                .child("enderecos")
                .child(FirebaseHelper.getIdFirebase());
        addressRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addressUser = snapshot.getValue(Address.class);
                binding.editFormAdsCep.setText(addressUser.getCep());
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showBottomSheet(int requestCode) {
        View bottomSheet = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(bottomSheet);
        bottomSheetDialog.show();

        bottomSheet.findViewById(R.id.btn_sheet_camera).setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            verifyPermissionCamera(requestCode);
        });
        bottomSheet.findViewById(R.id.btn_sheet_gallery).setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            verifyPermissionGallery(requestCode);
        });
        bottomSheet.findViewById(R.id.btn_sheet_close).setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
        });
    }

    private void verifyPermissionCamera(int requestCode) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openCamera(requestCode);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FormAdsActivity.this, "É preciso aceitar as permissões para adicionar fotos.", Toast.LENGTH_LONG).show();
            }
        };

        showDialogPermission(permissionListener,
                new String[]{Manifest.permission.CAMERA},
                "Se você não aceitar a permissão, não poderá acessar a Camera do dispositivo. Deseja ativar a permissão agora?");
    }

    private void verifyPermissionGallery(int requestCode) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openGallery(requestCode);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(FormAdsActivity.this, "É preciso aceitar as permissões para acessar a galeria.", Toast.LENGTH_LONG).show();
            }
        };
        showDialogPermission(permissionListener,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                "Se você não aceitar a permissão, não poderá acessar a Galeria do dispositivo. Deseja ativar a permissão agora?");

    }

    private void openCamera(int requestCode) {}

    private void openGallery(int requestCode) {}

    private void showDialogPermission(PermissionListener permissionListener, String[] permissions, String message) {
        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedTitle("Permissão Necessárias.")
                .setDeniedMessage(message)
                .setDeniedCloseButtonText("NÃO")
                .setGotoSettingButtonText("SIM")
                .setPermissions(permissions)
                .check();
    }

    private void configCEP() {
        binding.editFormAdsCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String cep = s.toString().replaceAll("_", "").replace("-", "");
                Log.i(TAG, "onTextChanged: " + s);

                if (cep.length() == 8) {
                    getCEP(cep);
                } else {
                    cepLocal = null;
                    configAddressCEP();
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void getCEP(String cep) {
        binding.progressBar.setVisibility(View.VISIBLE);

        CEPService cepService = retrofit.create(CEPService.class);
        Call<Cep> call = cepService.getCEP(cep);

        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {
                if (response.isSuccessful()) {
                    cepLocal = response.body();
                    if (cepLocal.getLocalidade() == null) {
                        Toast.makeText(FormAdsActivity.this, "CEP Inválido!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(FormAdsActivity.this, "Tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                }
                configAddressCEP();
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t) {
                Toast.makeText(FormAdsActivity.this, "Tente novamente mais tarde!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void configAddressCEP() {
        if (cepLocal != null) {
            if (cepLocal.getLocalidade() != null) {
                String address = cepLocal.getLocalidade() + ", " + cepLocal.getBairro() + " - DDD " + cepLocal.getDdd();
                binding.txtFormLocality.setText(address);
            } else {
                binding.txtFormLocality.setText("");
            }
        } else {
            binding.txtFormLocality.setText("");
        }
        binding.progressBar.setVisibility(View.GONE);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CATEGORY) {
                assert data != null;
                Category category = (Category) data.getSerializableExtra("selectCategory");
                selectCategory = category.getName();
                binding.btnFormCategory.setText(selectCategory);
            } else if (true) {
            } else {
            }
        }
    }
}