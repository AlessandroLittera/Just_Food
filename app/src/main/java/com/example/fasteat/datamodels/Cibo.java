package com.example.fasteat.datamodels;

public class Cibo {

        String nome;
        int quantity=0;
        float price;


        public Cibo(String nome, float price){
            this.nome=nome;
            this.price=price;
        }

        public String getNome() {
            return nome;
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