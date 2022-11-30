package com.example.olxclone.model;

import com.example.olxclone.data.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Bosco on 29/11/2022.
 */
public class Adss {

    private String id;
    private String idUser;
    private String title;
    private double pryce;
    private String category;
    private String description;
    private Cep local;
    private long dataPublicacao;
    private List<String> urlImages = new ArrayList<>();

    public Adss() {
        DatabaseReference adsRef = FirebaseHelper.getDatabaseReference();
        this.setId(adsRef.push().getKey());
    }

    public void save(boolean newAds) {
        DatabaseReference adsPublicRef = FirebaseHelper.getDatabaseReference()
                .child("anuncios_publicos")
                .child(this.getId());
        adsPublicRef.setValue(this);

        DatabaseReference myAdsRef = FirebaseHelper.getDatabaseReference()
                .child("meus_anuncios")
                .child(FirebaseHelper.getIdFirebase())
                .child(this.getId());
        myAdsRef.setValue(this);

        if (newAds) {
            DatabaseReference dateAdsPublic = adsPublicRef
                    .child("dataPublicacao");
            dateAdsPublic.setValue(ServerValue.TIMESTAMP);

            DatabaseReference dateMyAds = myAdsRef
                    .child("dataPublicacao");
            dateMyAds.setValue(ServerValue.TIMESTAMP);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPryce() {
        return pryce;
    }

    public void setPryce(double pryce) {
        this.pryce = pryce;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cep getLocal() {
        return local;
    }

    public void setLocal(Cep local) {
        this.local = local;
    }

    public long getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(long dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<String> getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(List<String> urlImages) {
        this.urlImages = urlImages;
    }
}
