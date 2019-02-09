package com.example.android.festin.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.android.festin.classes.Product;
import com.example.android.festin.adapters.ProductExpandableAdapter;
import com.example.android.festin.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ActivityVendor extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> listProductNames;
//    public static List<String> orderPreview;
    private TreeMap<String, Product> products;
    private int lastExpandedPosition = -1;
    private ArrayList<Product> vendorProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        vendorProducts = (ArrayList<Product>) getIntent().getSerializableExtra("Products");

        expandableListView = (ExpandableListView) findViewById(R.id.list_menu_vendor);

        products = new TreeMap<>();
        Log.i("From vendor", "before if");

        for(int i = 0; i < vendorProducts.size(); ++i){
            String productName = vendorProducts.get(i).getName();
            products.put(productName, vendorProducts.get(i));
        }

//        orderPreview = new ArrayList<String>();

        listProductNames = new ArrayList<>(products.keySet());
        expandableListAdapter = new ProductExpandableAdapter(this, listProductNames, products);

        expandableListView.setAdapter((expandableListAdapter));

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition  = groupPosition;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_button_vendor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityVendor.this, ActivityOrder.class));

            }
        });

    }
}
