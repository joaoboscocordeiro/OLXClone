package com.example.olxclone.model;

import java.io.Serializable;

/**
 * Created by João Bosco on 07/11/2022.
 */
public class Category implements Serializable {

    private int path;
    private String name;

    public Category(int path, String name) {
        this.path = path;
        this.name = name;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
