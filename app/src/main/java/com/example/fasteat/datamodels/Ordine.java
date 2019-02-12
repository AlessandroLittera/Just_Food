package com.example.fasteat.datamodels;

import android.widget.TextView;

import java.util.ArrayList;

public class Ordine {
    Restaurant ristorante;
    ArrayList<Cibo> arrayList;
    float totale;


    public Ordine(Restaurant ristorante, ArrayList<Cibo> arrayList){
        this.ristorante=ristorante;
        this.arrayList= arrayList;
        this.totale=calcoloTotale();
    }


    public float calcoloTotale(){

        float totale=0;

        for(int i=0; i<arrayList.size(); i++){
            totale += arrayList.get(i).getPrice()*arrayList.get(i).getQuantity();
        }
        return totale;
    }

    public ArrayList<Cibo> getArrayList() {
        return arrayList;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}

