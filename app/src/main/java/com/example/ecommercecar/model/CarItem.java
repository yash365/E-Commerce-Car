package com.example.ecommercecar.model;

public class CarItem {
    private int mCarImage;
    private String mCarName;
    private String mCarPrice;

    public CarItem(int mCarImage, String mCarName, String mCarPrice) {
        this.mCarImage = mCarImage;
        this.mCarName = mCarName;
        this.mCarPrice = mCarPrice;
    }

    public int getmCarImage() {
        return mCarImage;
    }

    public String getmCarName() {
        return mCarName;
    }

    public String getmCarPrice() {
        return mCarPrice;
    }
}
