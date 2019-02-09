package com.example.android.festin.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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

    private String mcontrol;
    private ArrayList<Vendor> mvendors;

    public VendorAdapter(Activity context, ArrayList<Vendor> vendors, String control) {
        super(context, 0, vendors);
        mcontrol = control;
        mvendors = vendors;
    }

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

        switch (mcontrol){
            case "thumbnail":
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
                /*vendorImace.setImageResource(currentVendor.getImageResourceId());*/
                vendorName.setText(String.valueOf(currentVendor.getName()));

                vendorFigureLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ActivityVendor.class);
                        intent.putExtra("Products", currentVendor.getVendorProducts());
                        getContext().startActivity(intent);
                    }
                });

                break;

            case "list":
                if(convertView == null){
                    convertView = LayoutInflater.from(getContext()).inflate(
                            R.layout.item_list, parent, false);
                }

                final Vendor currentVendorList = getItem(position);

                TextView vendorNameList = convertView.findViewById(R.id.list_item_text);
                LinearLayout vendorListLayout = convertView.findViewById(
                        R.id.item_list_layout);

                vendorNameList.setText(String.valueOf(currentVendorList.getName()));

                vendorListLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ActivityVendor.class);
                        intent.putExtra("Products", currentVendorList.getVendorProducts());
                        getContext().startActivity(intent);
                    }
                });

                break;
        }

        return convertView;
    }
}
