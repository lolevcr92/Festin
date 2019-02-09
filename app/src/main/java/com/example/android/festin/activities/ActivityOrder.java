package com.example.android.festin.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.festin.R;
import com.example.android.festin.adapters.OrderPreviewAdapter;
import com.example.android.festin.classes.Order;

import java.util.ArrayList;


public class ActivityOrder extends Activity {

    public static ArrayList<Order> orderPreview = new ArrayList<Order>();
    public static TextView totalOrderText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_preview);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics( dm );

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.75),(int)(height*.75));

        OrderPreviewAdapter orderAdapter = new OrderPreviewAdapter(this, orderPreview);
        ListView listView = (ListView) findViewById(R.id.order_preview_list);

        listView.setAdapter(orderAdapter);

        totalOrderText = (TextView) findViewById(R.id.order_preview_total);
        totalOrderText.setText("Total: " + Order.getTotal() + " RON");

        Button sendOrderButton = findViewById(R.id.send_order_button);
        sendOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityOrder.this, ActivityCountdown.class));
            }
        });
    }
}
