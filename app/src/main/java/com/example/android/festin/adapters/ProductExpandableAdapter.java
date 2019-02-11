package com.example.android.festin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.festin.R;
import com.example.android.festin.activities.ActivityOrder;
import com.example.android.festin.classes.Order;
import com.example.android.festin.classes.Product;
import com.example.android.festin.Utils.ImageFormating;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ProductExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listProducts;
    private HashMap<String, float[]>  productQuantityPrice = new HashMap<String, float[]>();
    private HashMap<String, Boolean> checkIfInitialized = new HashMap<String, Boolean>();
    private TreeMap<String,Product> expandableProducts;

    /*Constructor*/
    public ProductExpandableAdapter(Context context, List<String> listProducts,
                                    TreeMap<String, Product> expandableProducts) {
        this.context = context;
        this.listProducts = listProducts;
        for(String i : listProducts){
            checkIfInitialized.put(i, false);
        }
        this.expandableProducts = expandableProducts;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final Product product = (Product) getChild(groupPosition, 0);

        if(convertView == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.product_group_item, null);
        }

        if(!checkIfInitialized.get(product.getName())){
            int[] productQuantity = {1};
            float[] quantityPrice = new float[] {productQuantity[0], product.getPrice() * productQuantity[0]};
            productQuantityPrice.put(product.getName(), quantityPrice);
            checkIfInitialized.put(product.getName(), true);
        }

        ImageView productImageView = convertView.findViewById(R.id.vendor_group_product_image);
        productImageView.setImageBitmap(ImageFormating.decodeSampledBitmapFromResource(context.getResources(), product.getImageResourceId(),250,250));

        final TextView productName = (TextView) convertView.findViewById(R.id.vendor_group_product_name);
        productName.setText(product.getName());

        final TextView productPrice = (TextView) convertView.findViewById(R.id.vendor_group_product_price);
        productPrice.setText(String.valueOf(productQuantityPrice.get(product.getName())[1]) + " RON");

        final TextView quantityTextView = (TextView) convertView.findViewById(R.id.vendor_group_product_quantity);

        TextView substractQuantity = (TextView) convertView.findViewById(R.id.vendor_group_product_substract);
        TextView addQuantity = (TextView) convertView.findViewById(R.id.vendor_group_product_add);

        substractQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productQuantityPrice.get(product.getName())[0] > 1){
                    float quantity = productQuantityPrice.get(product.getName())[0] - 1;
                    float[] quantityPrice = new float[] {quantity, product.getPrice() * quantity };
                    productQuantityPrice.put(product.getName(), quantityPrice);
                    quantityTextView.setText(String.valueOf(Math.round(quantity)));
                    productPrice.setText(String.valueOf(productQuantityPrice.get(product.getName())[1])  + " RON");
                }
            }
        });

        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float quantity = productQuantityPrice.get(product.getName())[0] + 1;
                float[] quantityPrice = new float[] {quantity, product.getPrice() * quantity };
                productQuantityPrice.put(product.getName(), quantityPrice);
                quantityTextView.setText(String.valueOf(Math.round(quantity)));
                productPrice.setText(String.valueOf(productQuantityPrice.get(product.getName())[1])  + " RON");
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Product product = (Product) getChild(groupPosition, childPosition);

        if (convertView == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.product_child_item, null);
        }


        TextView productDescription = (TextView) convertView.findViewById(R.id.vendor_child_product_description);
        productDescription.setText(product.getDescription());

        Button addToOrderButton = (Button) convertView.findViewById(R.id.vendor_child_product_button);
        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), product.getName() + " added to cart", Toast.LENGTH_LONG).show();
                ActivityOrder.orderPreview.add(new Order(Math.round(productQuantityPrice.get(product.getName())[0]),
                        product.getName(), "Description " + product.getName(),
                        productQuantityPrice.get(product.getName())[1] ));

            }
        });

        return convertView;
    }

    @Override
    public int getGroupCount() {
        return this.listProducts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listProducts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableProducts.get(this.listProducts.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
