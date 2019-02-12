package com.example.fasteat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fasteat.R;
import com.example.fasteat.datamodels.Cibo;
import com.example.fasteat.datamodels.Ordine;
import com.example.fasteat.datamodels.Restaurant;
import com.example.fasteat.ui.adapters.CheckoutAdapter;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements CheckoutAdapter.onRemoveRowListner{

    private TextView restaurantTV, restaurantAddress, minOrder, totale;
    private RecyclerView productRv;
    private ImageView logo;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    CheckoutAdapter checkoutAdapter;
    private Ordine ordine;
    private Restaurant restaurant;
    private ArrayList<Cibo> arrayList;
    private float total=0;
   // private Order order;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        restaurant= getRestaurant();
        ordine= new Ordine(restaurant,getOrdine());
        restaurantTV = findViewById(R.id.restaurantTV);
        restaurantAddress= findViewById(R.id.restaurantAddress);
        logo= findViewById(R.id.logo_tv);
        minOrder= findViewById(R.id.minOrder);
        //totalTV= findViewById(R.id.total_tv);
        productRv= findViewById(R.id.product_rv);
        payBtn= findViewById(R.id.payBtn);
        totale= findViewById(R.id.totale);

        layoutManager= new LinearLayoutManager(this);
        checkoutAdapter = new CheckoutAdapter(this,ordine);
        checkoutAdapter.setOnRemoveRowListner(this);
        productRv.setAdapter(checkoutAdapter);
        productRv.setLayoutManager(layoutManager);

        restaurantTV.setText(restaurant.getNome());
        restaurantAddress.setText(restaurant.getIndirizzo());
        logo.setImageResource(restaurant.getLogo());
        minOrder.setText(String.valueOf(restaurant.getPrezzo()));
        totale.setText(String.valueOf(ordine.calcoloTotale()));
    }

    private void updateTotal(float item){
        total= total + item;
        totale.setText(String.format("totale: %s", totale));

        payBtn.setEnabled(true);


    }

    private ArrayList<Cibo> getOrdine(){
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(new Cibo("Cheesburger", 1.0f));
        arrayList.add(new Cibo("Hamburger", 1.00f));
        arrayList.add(new Cibo("Cheesburger",1.00f));
        arrayList.add(new Cibo("McMenu",1.00f));
        return arrayList;
    }

    private Restaurant getRestaurant() {
        return new Restaurant("Panino", "via san giuseppe", 1.00f, R.drawable.mcdonal);
    }

    @Override
    public void onChange(float price) {
        ordine.calcoloTotale();
        totale.setText(String.valueOf(ordine.getTotale()));
    }
}
