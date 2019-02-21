package com.example.fasteat.datamodels;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class Cibo {

        String nome;
        int quantity=0;
        float price;
        public float totale;
        String url;


        public Cibo(String nome, float price, int quantity, String url){
            this.nome=nome;
            this.price=price;
            this.quantity=quantity;
            this.url=url;
        }

        public Cibo(JSONObject jsonObject){
            try {
                this.nome= jsonObject.getString("name");
                this.price= (float)jsonObject.getDouble("price");
                this.quantity=0;
                this.totale=0;
                this.url= jsonObject.getString("image_url");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        public String getNome() {
            return nome;
        }


    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getQuantity() {
            return quantity;
        }

        public float getPrice() {
                return price;
            }


        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setPrice(float price) {
                this.price = price;
            }


            public void increaseQuantity(){
            this.quantity++;
            }

            public void decreaseQuantity(){
                if(quantity==0) return;
                this.quantity--;
            }

}