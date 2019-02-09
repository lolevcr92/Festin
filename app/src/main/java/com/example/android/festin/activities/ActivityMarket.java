package com.example.android.festin.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.android.festin.R;
import com.example.android.festin.adapters.VendorAdapter;
import com.example.android.festin.adapters.ViewPagerAdapter;
import com.example.android.festin.classes.Product;
import com.example.android.festin.classes.Vendor;
import com.example.android.festin.fragments.FragmentFigure;
import com.example.android.festin.fragments.FragmentList;

import java.util.ArrayList;


public class ActivityMarket extends AppCompatActivity {

    public ArrayList<Vendor> vendors = new ArrayList<>();
    public GridView gridView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("TestLog", "onCreate");
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_market);
        gridView = findViewById(R.id.icon_grid);
        populateVendors();

        VendorAdapter adapter = new VendorAdapter(this, vendors, "thumbnail");

        gridView.setAdapter(adapter);

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("SearchView: ", newText);
                ArrayList<Vendor> results = new ArrayList<>();

                for(Vendor vendor: vendors){
                    if(vendor.getName().contains(newText)){
                        Log.v("SearchView ","true " + vendor.getName());
                        results.add(vendor);
                    }
                }

                Log.v("SearchView ", "" + results.size());
                ((VendorAdapter) gridView.getAdapter()).update(results);

                return false;
            }
        });

    }

    public void fragmentVersion(){
        TabLayout tabLayout = findViewById( R.id.tablayout );
        ViewPager viewPager = findViewById( R.id.viewPager );
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        adapter.AddFragment( new FragmentFigure(), "Thumb" );
        adapter.AddFragment( new FragmentList(), "List" );

        viewPager.setAdapter( adapter );
        tabLayout.setupWithViewPager( viewPager );
    }

    public void populateVendors(){
        ArrayList<Product> burgerVan = new ArrayList<>();
        burgerVan.add(new Product("Burger Van P1", R.drawable.burgervanp1, 10, "Product 1 description and others"));
        burgerVan.add(new Product("Burger Van P2", R.drawable.burgervanp2, 15, "Product 1 description and others"));
        burgerVan.add(new Product("Burger Van P3", R.drawable.burgervanp3, 20, "Product 1 description and others"));
        burgerVan.add(new Product("Burger Van P4", R.drawable.burgervanp4, 10, "Product 1 description and others"));
        burgerVan.add(new Product("Burger Van P5", R.drawable.burgervanp5, 10, "Product 1 description and others"));

        ArrayList<Product> rawVegan = new ArrayList<>();
        rawVegan.add( new Product("Raw Vegan P1", R.drawable.rawveganp1, 25, "Product 1 description and others" ));
        rawVegan.add( new Product("Raw Vegan P2", R.drawable.rawveganp2, 35, "Product 2 description and others" ));
        rawVegan.add( new Product("Raw Vegan P3", R.drawable.rawveganp3, 15, "Product 3 description and others" ));
        rawVegan.add( new Product("Raw Vegan P4", R.drawable.rawveganp4, 20, "Product 4 description and others" ));
        rawVegan.add( new Product("Raw Vegan P5", R.drawable.rawveganp5, 25, "Product 5 description and others" ));

        ArrayList<Product> zagerman = new ArrayList<>();
        zagerman.add(new Product("Zagerman P1", R.drawable.zagermanp1, 25, "Product 1 description and others" ));
        zagerman.add(new Product("Zagerman P2", R.drawable.zagermanp2, 35, "Product 2 description and others" ));
        zagerman.add(new Product("Zagerman P3", R.drawable.zagermanp3, 15, "Product 3 description and others" ));
        zagerman.add(new Product("Zagerman P4", R.drawable.zagermanp4, 20, "Product 4 description and others" ));
        zagerman.add(new Product("Zagerman P5", R.drawable.zagermanp5, 25, "Product 5 description and others" ));

        ArrayList<Product> bettyIce = new ArrayList<>();
        bettyIce.add(new Product("BettyIce P1", R.drawable.bettyicep1, 10, "Product 1 description and others" ));
        bettyIce.add(new Product("BettyIce P2", R.drawable.bettyicep2, 15, "Product 1 description and others" ));
        bettyIce.add(new Product("BettyIce P3", R.drawable.bettyicep3, 20, "Product 1 description and others" ));
        bettyIce.add(new Product("BettyIce P4", R.drawable.bettyicep4, 10, "Product 1 description and others" ));
        bettyIce.add(new Product("BettyIce P5", R.drawable.bettyicep5, 10, "Product 1 description and others" ));

        ArrayList<Product> sundayBagels = new ArrayList<>();
        sundayBagels.add(new Product("Sunday Bagels P1", R.drawable.sundaybagelp1, 25, "Product 1 description and others" ));
        sundayBagels.add(new Product("Sunday Bagels P2", R.drawable.sundaybagelp2, 35, "Product 2 description and others" ));
        sundayBagels.add(new Product("Sunday Bagels P3", R.drawable.sundaybagelp3, 15, "Product 3 description and others" ));
        sundayBagels.add(new Product("Sunday Bagels P4", R.drawable.sundaybagelp4, 20, "Product 4 description and others" ));
        sundayBagels.add(new Product("Sunday Bagels P5", R.drawable.sundaybagelp5, 25, "Product 5 description and others" ));

        ArrayList<Product> laPlacinte = new ArrayList<>();
        laPlacinte.add(new Product("La placinte P1", R.drawable.laplacintep1, 25, "Product 1 description and others" ));
        laPlacinte.add(new Product("La placinte P2", R.drawable.laplacintep2, 35, "Product 2 description and others" ));
        laPlacinte.add(new Product("La placinte P3", R.drawable.laplacintep3, 15, "Product 3 description and others" ));
        laPlacinte.add(new Product("La placinte P4", R.drawable.laplacintep4, 20, "Product 4 description and others" ));
        laPlacinte.add(new Product("La placinte P5", R.drawable.laplacintep5, 25, "Product 5 description and others" ));

        ArrayList<Product> rdp = new ArrayList<>();
        rdp.add(new Product("RDP P1", R.drawable.rdpp1, 25, "Product 1 description and others" ));
        rdp.add(new Product("RDP P2", R.drawable.rdpp2, 35, "Product 2 description and others" ));
        rdp.add(new Product("RDP P3", R.drawable.rdpp3, 15, "Product 3 description and others" ));
        rdp.add(new Product("RDP P4", R.drawable.rdpp4, 20, "Product 4 description and others" ));
        rdp.add(new Product("RDP P5", R.drawable.rdpp5, 25, "Product 5 description and others" ));

        ArrayList<Product> switchEat = new ArrayList<>();
        switchEat.add(new Product("Switch eat P1", R.drawable.switcheatp1, 25, "Product 1 description and others" ));
        switchEat.add(new Product("Switch eat P2", R.drawable.switcheatp2, 35, "Product 2 description and others" ));
        switchEat.add(new Product("Switch eat P3", R.drawable.switcheatp3, 15, "Product 3 description and others" ));
        switchEat.add(new Product("Switch eat P4", R.drawable.switcheatp4, 20, "Product 4 description and others" ));
        switchEat.add(new Product("Switch eat P5", R.drawable.switcheatp5, 25, "Product 5 description and others" ));


        vendors.add(new Vendor("Burger Van", R.drawable.burgervan, burgerVan));
        vendors.add(new Vendor("Raw Vegan", R.drawable.rawvegan, rawVegan));
        vendors.add(new Vendor("Zagerman", R.drawable.zagerman, zagerman));
        vendors.add(new Vendor("Betty Ice", R.drawable.bettyice, bettyIce));
        vendors.add(new Vendor("Sunday Bagels", R.drawable.sundaybagels, sundayBagels));
        vendors.add(new Vendor("La placinte", R.drawable.laplacinte, laPlacinte));
        vendors.add(new Vendor("Raionul de peste", R.drawable.rdp, rdp));
        vendors.add(new Vendor("Switch eat", R.drawable.switcheat, switchEat));
    }
}
