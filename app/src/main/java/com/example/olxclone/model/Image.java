package com.example.olxclone.model;

/**
 * Created by João Bosco on 28/11/2022.
 */
public class Image {

    private String imgPath;
    private int index;

    public Image(String imgPath, int index) {
        this.imgPath = imgPath;
        this.index = index;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
