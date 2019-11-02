package com.example.ecommercecar.model;

public class CarItem {
    private int mCarImage;
    private String mPrice;
    private String mYear;
    private String mKmUsed;
    private String mCarName;
    private String mSellerName;
    private String mCity;

    public CarItem(int mCarImage, String mPrice, String mYear, String mKmUsed, String mCarName, String mSellerName, String mCity) {
        this.mCarImage = mCarImage;
        this.mPrice = mPrice;
        this.mYear = mYear;
        this.mKmUsed = mKmUsed;
        this.mCarName = mCarName;
        this.mSellerName = mSellerName;
        this.mCity = mCity;
    }

    public int getmCarImage() {
        return mCarImage;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmYear() {
        return mYear;
    }

    public String getmKmUsed() {
        return mKmUsed;
    }

    public String getmCarName() {
        return mCarName;
    }

    public String getmSellerName() {
        return mSellerName;
    }

    public String getmCity() {
        return mCity;
    }
}
