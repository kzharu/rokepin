package com.android.firebaseapp;

public class Member {
    private String Name;
    private Double Double1;
    private Double Double2;

    public Member() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public void setDouble1(Double double1) {
        Double1 = double1;
    }

    public  Double getDouble1(){
        return Double1;
    }

    public void setDouble2(Double double2) {
        Double2 = double2;
    }

    public Double getDouble2(){
        return Double2;
    }
}
