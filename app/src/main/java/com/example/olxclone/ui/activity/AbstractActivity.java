package com.example.olxclone.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.olxclone.util.View;

/**
 * Created by João Bosco on 18/11/2022.
 */
public abstract class AbstractActivity extends AppCompatActivity implements View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInject();
    }

    @Override
    public void showProgressBar() {}

    @Override
    public void hideProgressBar() {}

    protected abstract void onInject();
}
