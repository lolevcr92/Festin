package com.example.android.festin.classes;

public class Order {
    private String mName;
    private String mDescription;
    private float mPrice;
    private int mQuantity;
    private static float mTotal = 0;

    public Order(int quantity, String name, String description, float price) {
        mQuantity = quantity;
        mName = name;
        mDescription = description;
        mPrice = price;
        mTotal += price;

    }

    public String getName(){
        return mName;
    }

    public int getQuantity(){
        return mQuantity;
    }

    public String getDescription(){
        return mDescription;
    }

    public float getPrice(){
        return mPrice;
    }

    public static float getTotal(){
        return mTotal;
    }

    public static float removeFromTotal(float priceToBeRemoved){
        return mTotal -= priceToBeRemoved ;
    }
}
