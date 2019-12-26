package com.example.lablistview;

public class Food {
    private String title;
    private String description;
    private int logo;

    public Food(String title, String description, int logo) {
        this.title = title;
        this.description = description;
        this.logo = logo;
    }
    public static final Food[] food={
            new Food("burger","juicy burgers",R.drawable.coffee_1),
            new Food("pizza","cheesy pizza",R.drawable.coffee_2),
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
    public String toString(){
        return title;
    }
}
