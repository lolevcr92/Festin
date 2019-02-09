package com.example.android.festin.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.festin.R;
import com.example.android.festin.activities.ActivityIntro;
import com.example.android.festin.activities.ActivityOrder;
import com.example.android.festin.classes.Order;

import java.util.ArrayList;

public class OrderPreviewAdapter extends ArrayAdapter<Order> {
    public OrderPreviewAdapter(@NonNull Context context, ArrayList<Order> order) {
        super(context, 0, order);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_order, parent, false);
        }

        final Order currentOrder = getItem(position);

        TextView itemName = (TextView) convertView.findViewById(R.id.order_product_name);
        TextView itemDescription = (TextView) convertView.findViewById(R.id.order_product_details);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.order_product_price);
        TextView deleteItem = (TextView) convertView.findViewById(R.id.delete_product_from_order);

        itemName.setText(currentOrder.getQuantity() + " X " + currentOrder.getName());
        itemDescription.setText("Details: " + currentOrder.getDescription());
        itemPrice.setText(String.valueOf(currentOrder.getPrice()) + " RON");

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.removeFromTotal(currentOrder.getPrice());
                remove(currentOrder);
                notifyDataSetChanged();
                ActivityOrder.totalOrderText.setText("Total: " + Order.getTotal() + " RON");
            }
        });

        return convertView;
    }
}
