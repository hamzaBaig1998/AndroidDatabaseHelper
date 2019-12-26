package com.example.lablistview;

import androidx.annotation.NonNull;

public class Store {
    private String title;
    private String description;
    private int logo;

    public Store(String title, String description, int logo) {
        this.title = title;
        this.description = description;
        this.logo = logo;
    }
    public static final Store[] store={
            new Store("McDonalds","24/7 service",R.drawable.coffee_1),
            new Store("Pizza Hut","24/7 service",R.drawable.coffee_2)
    };
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
