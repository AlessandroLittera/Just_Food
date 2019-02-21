package com.example.fasteat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.fasteat.R;
import com.example.fasteat.datamodels.Cibo;
import com.example.fasteat.datamodels.Restaurant;
import com.example.fasteat.services.RestController;
import com.example.fasteat.ui.adapters.MenuAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements MenuAdapter.OnQuantityChangedListner, Response.ErrorListener, Response.Listener<String> {

    private float total=0;
    Restaurant restaurant;
    ImageView imageRestaurant;
    TextView textTitle;
    TextView textIndirizzo;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recycleLayout;
    MenuAdapter menuAdapter;
    TextView totale;
    ProgressBar progressBar;
    Button checkout;
    ArrayList<Cibo> arrayList= new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String endpoint= "restaurants/" + getIntent().getStringExtra("idRestaurant");
        setContentView(R.layout.activity_shop);
        recyclerView =findViewById(R.id.recycle);
        totale=findViewById(R.id.tot);
        checkout=findViewById(R.id.checkout);

        RestController restController= new RestController(this);
        restController.getRequest(endpoint,this,this);

        recycleLayout= new LinearLayoutManager(this);
        menuAdapter= new MenuAdapter(this, arrayList);
        menuAdapter.setOnQuantityChangedListner(this);
        progressBar = findViewById(R.id.progress);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(recycleLayout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSharedPreferences("shared", Context.MODE_PRIVATE).getString("chiave", null)!=null){
                Intent i = new Intent(ShopActivity.this, CheckoutActivity.class);
                startActivity(i);
            }else {
                    Toast.makeText(view.getContext(), "loggati",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ShopActivity.this, LoginActivity.class);
                    startActivity(i);
                }
        }});


        progressBar.setMax(10*100);
        restaurant= getRestaurant();
        imageRestaurant= findViewById(R.id.logo_tv);
        textTitle=findViewById(R.id.name_tv);
        textIndirizzo=findViewById(R.id.address_tv);
        String urlRestaurant= getIntent().getStringExtra("id_url");
        Glide.with(this).load(urlRestaurant).into(imageRestaurant);
        textTitle.setText(getIntent().getStringExtra("nomeRestaurant"));
        textIndirizzo.setText(getIntent().getStringExtra("addressRestaurant"));


    }

    /*private ArrayList<Cibo> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Cibo("Cheesburger", 1.0f));
        arrayList.add(new Cibo("Hamburger", 1.00f));
        arrayList.add(new Cibo("Cheesburger",1.00f));
        arrayList.add(new Cibo("McMenu",1.00f));
        arrayList.add(new Cibo("Hamburger",1.00f));
        arrayList.add(new Cibo("hamburger",1.00f));
        return arrayList;
    }*/

    private void updateTotal(float item){
        total= total + item;
        totale.setText(String.format("totale: %s", total));

        if(total>=10){
            checkout.setEnabled(true);
        }else{
            checkout.setEnabled(false);
        }
    }
    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgress((int)(total * 100));
    }


    private Restaurant getRestaurant() {
        return new Restaurant("Panino", "via san giuseppe", 1.00f,"https://rovato5stelle.files.wordpress.com/2013/11/mcdonald.jpg");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Errore",error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject= new JSONObject(response);
            JSONArray jsonArray= jsonObject.getJSONArray("products");
            for(int i=0; i<jsonArray.length(); i++){
                Cibo c= new Cibo(jsonArray.getJSONObject(i));
                arrayList.add(c);
            }
            menuAdapter.setData(arrayList);
        } catch (JSONException e) {
            //e.printStackTrace();
            Log.e("exceptionMassage", e.getMessage());
        }
    }
}

