package com.example.android.festin.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.festin.R;
import com.example.android.festin.activities.ActivityVendor;
import com.example.android.festin.classes.Vendor;

import java.util.ArrayList;

public class VendorAdapter extends ArrayAdapter<Vendor> {

    private ArrayList<Vendor> mvendors;

    public VendorAdapter(Activity context, ArrayList<Vendor> vendors) {
        super(context, 0, vendors);
        mvendors = vendors;
    }

    /*Update method that checks what results are in the ArrayList, after user is searching*/
    public void update(ArrayList<Vendor> results){
        mvendors = new ArrayList<>();
        mvendors.addAll(results);
        Log.v("Update ", mvendors.size() + "");
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mvendors.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_figure, parent, false);
        }

        final Vendor currentVendor = getItem(position);

        /*Views setter*/
        ImageView vendorImage = convertView.findViewById(
                R.id.figure_item_image);
        TextView vendorName =  convertView.findViewById(R.id.figure_item_text);
        LinearLayout vendorFigureLayout = convertView.findViewById(
                R.id.item_figure_layout);

        vendorImage.setImageResource(currentVendor.getImageResourceId());
        vendorImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        vendorName.setText(String.valueOf(currentVendor.getName()));

        /*Method for entering the vendor who was clicked*/
        vendorFigureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*To be adjusted when linking with data base. Passing id for querying desired products*/
                Intent intent = new Intent(getContext(), ActivityVendor.class);
                intent.putExtra("Products", currentVendor.getVendorProducts());
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
