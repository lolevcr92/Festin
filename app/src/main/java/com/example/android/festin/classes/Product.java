package com.example.android.festin.classes;

import java.util.HashMap;

public class Product implements java.io.Serializable {

    private int mImageResourceId;
    private String mName;
    private float mPrice;
    private String mDescription;

    @Override
    public String toString() {
        return "Product{" + "Field0 = " + mName + "Field1 = " + mImageResourceId
                + "Field2 = " + mPrice + "Field3" + mDescription;
    }

    /*Constructor 1*/
    public Product (String name, int imageResourceId, float price, String description){
        mName = name;
        mImageResourceId = imageResourceId;
        mPrice = price;
        mDescription = description;
    }

    public String getName(){
        return mName;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public float getPrice(){
        return mPrice;
    }

    public String getDescription(){
        return mDescription;
    }
}
