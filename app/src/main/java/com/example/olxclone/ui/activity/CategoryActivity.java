package com.example.olxclone.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.olxclone.R;
import com.example.olxclone.databinding.ActivityCategoryBinding;
import com.example.olxclone.model.Category;
import com.example.olxclone.ui.adapter.AdapterCategory;
import com.example.olxclone.util.CategoryList;

/**
 * Created by João Bosco on 07/11/2022.
 */
public class CategoryActivity extends AppCompatActivity implements AdapterCategory.OnClickListener {

    private ActivityCategoryBinding binding;
    private AdapterCategory adapterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarCategory.textToolbarTitle.setText(R.string.toolbar_category_title);
        binding.toolbarCategory.imbBack.setOnClickListener(v -> finish());

        initRV();
    }

    private void initRV() {
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCategory.setHasFixedSize(true);
        adapterCategory = new AdapterCategory(CategoryList.getCategoryList(false), this);
        binding.rvCategory.setAdapter(adapterCategory);
    }

    @Override
    public void onClick(Category category) {
        Toast.makeText(this, category.getName(), Toast.LENGTH_LONG).show();
    }
}