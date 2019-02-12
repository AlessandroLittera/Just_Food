package com.example.fasteat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.fasteat.R;
import com.example.fasteat.datamodels.Cibo;
import com.example.fasteat.ui.adapters.MenuAdapter;
import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements MenuAdapter.OnQuantityChangedListner {

    private float total=0;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recycleLayout;
    MenuAdapter menuAdapter;
    TextView totale;
    ProgressBar progressBar;
    Button checkout;
    ArrayList<Cibo> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recyclerView =findViewById(R.id.recycle);
        totale=findViewById(R.id.tot);
        checkout=findViewById(R.id.checkout);
        recycleLayout= new LinearLayoutManager(this);
        menuAdapter= new MenuAdapter(this, getData());
        menuAdapter.setOnQuantityChangedListner(this);
        progressBar = findViewById(R.id.progress);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(recycleLayout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShopActivity.this, CheckoutActivity.class);
                startActivity(i);
            }
        });

        progressBar.setMax(10*100);
    }

    private ArrayList<Cibo> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Cibo("Cheesburger", 1.0f));
        arrayList.add(new Cibo("Hamburger", 1.00f));
        arrayList.add(new Cibo("Cheesburger",1.00f));
        arrayList.add(new Cibo("McMenu",1.00f));
        arrayList.add(new Cibo("Hamburger",1.00f));
        arrayList.add(new Cibo("hamburger",1.00f));
        return arrayList;
    }

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

}

