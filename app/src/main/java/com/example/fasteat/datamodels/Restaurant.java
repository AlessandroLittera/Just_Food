package com.example.fasteat.datamodels;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restaurant {
    String nome;
    String indirizzo;
    float prezzo;
    private String url;
    String id;

    private ArrayList<Cibo> product;
    public Restaurant(String nome, String indirizzo, float prezzo, String url){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.prezzo=prezzo;
        this.url=url;
        id="0";
    }

    public Restaurant(JSONObject jsonObject) {
        try {
            this.nome= jsonObject.getString("name");
            this.indirizzo= jsonObject.getString("address");
            this.prezzo= (float)jsonObject.getDouble("min_order");
            this.url= jsonObject.getString("image_url");
            id=jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Cibo> getProduct() {
        return product;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getUrl() {
        return url;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setProduct(ArrayList<Cibo> product) {
        this.product = product;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
