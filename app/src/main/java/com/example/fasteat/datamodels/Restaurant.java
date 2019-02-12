package com.example.fasteat.datamodels;

import android.support.annotation.DrawableRes;

import java.util.ArrayList;

public class Restaurant {
    String nome;
    String indirizzo;
    float prezzo;
    private @DrawableRes int logo;

    private ArrayList<Cibo> product;
    public Restaurant(String nome, String indirizzo, float prezzo, int logo){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.prezzo=prezzo;
        this.logo=logo;
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

    public @DrawableRes int getLogo() {
        return logo;
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

    public void setLogo(@DrawableRes int logo) {
        this.logo = logo;
    }
}
