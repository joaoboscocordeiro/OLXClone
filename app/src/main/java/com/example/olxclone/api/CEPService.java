package com.example.olxclone.api;

import com.example.olxclone.model.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by João Bosco on 09/11/2022.
 */
public interface CEPService {

    @GET("{cep}/json/")
    Call<Cep> getCEP(@Path("cep") String cep);
}
