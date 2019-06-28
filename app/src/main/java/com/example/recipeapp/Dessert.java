package com.example.recipeapp;

public class Dessert {

    public String dname, dingredient;
    public Dessert(){}

    public Dessert(String dname, String dingredient) {
        this.dname = dname;
        this.dingredient = dingredient;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDingredient() {
        return dingredient;
    }

    public void setDingredient(String dingredient) {
        this.dingredient = dingredient;
    }
}
