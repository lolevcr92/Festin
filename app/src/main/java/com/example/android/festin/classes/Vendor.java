package com.example.android.festin.classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vendor {

    private int mImageResourceId;
    private String mName;
    private float mPrice;
    private ArrayList<Product> mVendorProducts;

    @Override
    public String toString() {
        return "Vendor{" + "Field0 = " + mName + "Field1 = " + mImageResourceId;
    }

    /*Constructor 1*/
    public Vendor (String name, int imageResourceId, ArrayList<Product> vendorProducts ){
        mName = name;
        mImageResourceId = imageResourceId;
        mVendorProducts = vendorProducts;
    }

    /*Constructor 2*/
    public Vendor (String name, int imageResourceId, float price ){
        mName = name;
        mImageResourceId = imageResourceId;
        mPrice = price;
    }

    /*Methods (getter here)*/
    public String getName(){
        return mName;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public ArrayList<Product> getVendorProducts() {
        return mVendorProducts;
    }

    public float getPrice() {
        return mPrice;
    }
}
