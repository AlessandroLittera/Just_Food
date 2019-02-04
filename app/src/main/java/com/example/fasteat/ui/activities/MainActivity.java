package com.example.fasteat.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.fasteat.R;
import com.example.fasteat.Utils;
import com.example.fasteat.datamodels.Restaurant;
import com.example.fasteat.ui.activities.adapters.RestaurantAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this,getData());

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donald's", "via Tiburtina 235", 8.00f));
        arrayList.add(new Restaurant("Burgher King","Via Tibutina 245", 6.00f));
        arrayList.add(new Restaurant("KFC","Via Cesenatico 101",10.00f));

        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.manu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.login_menu){
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }else if(item.getItemId()==R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
