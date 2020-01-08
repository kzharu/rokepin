package com.android.firebaseapp;

import android.widget.EditText;

public class Member {
    private String Name;
    private String Category;
    private Double Longitude;
    private Double Latitude;

    public Member() {
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Cate) {
        Category = Cate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public void setLongitude(Double Longi) {
        Longitude = Longi;
    }

    public  Double getLongitude(){
        return Longitude;
    }

    public void setLatitude(Double Lati) {
        Latitude = Lati;
    }

    public Double getLatitude(){
        return Latitude;
    }

    public void setString(EditText editTextName2) {
    }
}
