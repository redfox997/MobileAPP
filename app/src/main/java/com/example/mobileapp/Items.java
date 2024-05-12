package com.example.mobileapp;

public class Items {
    private String brand;
    private String model;
    private String bancmarkPoint;
    private final int imageResource;

    public Items(String brand, String model, String bancmarkPoint, int imageResource) {
        this.brand = brand;
        this.model = model;
        this.bancmarkPoint = bancmarkPoint;
        this.imageResource = imageResource;
    }

    public String getBrand() {return brand;}

    public String getModel() {return model;}

    public String getBancmarkPoint() {return bancmarkPoint;}

    public int getImageResource() {return imageResource;}
}
