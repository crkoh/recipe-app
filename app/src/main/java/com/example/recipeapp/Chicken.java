package com.example.recipeapp;

public class Chicken {

    public String cname, cingredient;
    public Chicken(){}

    public Chicken(String cname, String cingredient) {
        this.cname = cname;
        this.cingredient=cingredient;

    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCingredient() {
        return cingredient;
    }

    public void setCingredient(String cingredient) {
        this.cingredient = cingredient;
    }
}
