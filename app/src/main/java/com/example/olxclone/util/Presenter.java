package com.example.olxclone.util;

/**
 * Created by João Bosco on 18/11/2022.
 */
public interface Presenter {
    void onSuccess();
    void onError(String message);
    void onComplete();
}
