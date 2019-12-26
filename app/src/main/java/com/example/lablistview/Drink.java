package com.example.lablistview;

import java.util.ArrayList;

public class Drink {
    private String title;
    private String description;
    private int logo;
    public static final Drink[] drinks={
            new Drink(R.drawable.coffee_1,"Latte","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Hic facere incidunt saepe expedita alias delectus laudantium et. A autem consequatur nemo sapiente nisi, atque cupiditate, aperiam tenetur magni recusandae, sunt."),
            new Drink(R.drawable.coffee_2,"Cappocuino","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Hic facere incidunt saepe expedita alias delectus laudantium et. A autem consequatur nemo sapiente nisi, atque cupiditate, aperiam tenetur magni recusandae, sunt."),
            new Drink(R.drawable.coffee_3,"Frappe","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Hic facere incidunt saepe expedita alias delectus laudantium et. A autem consequatur nemo sapiente nisi, atque cupiditate, aperiam tenetur magni recusandae, sunt."),
    };

    Drink(){}
    Drink(int logo, String title, String description){
        this.logo=logo;
        this.title=title;
        this.description=description;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getLogo() {
        return logo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
    public String toString(){
        return title;
    }



}
