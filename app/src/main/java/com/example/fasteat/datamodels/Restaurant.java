package com.example.fasteat.datamodels;

public class Restaurant {
    String nome;
    String indirizzo;
    float prezzo;

    public Restaurant(String nome, String indirizzo, float prezzo){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.prezzo=prezzo;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
}
