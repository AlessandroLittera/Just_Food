package com.example.fasteat.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.fasteat.R;
import com.example.fasteat.datamodels.Cibo;
import com.example.fasteat.datamodels.Restaurant;

import java.util.ArrayList;


public class MenuAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;

    private ArrayList<Cibo> data;
    Context context;


    public MenuAdapter(Context context, ArrayList<Cibo> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    public interface OnQuantityChangedListner {
        void onChange(float price);
    }

    private OnQuantityChangedListner onQuantityChangedListner;

    public OnQuantityChangedListner getOnQuantityChangedListner() {
        return onQuantityChangedListner;
    }

    public void setOnQuantityChangedListner(OnQuantityChangedListner onQuantityChangedListner) {
        this.onQuantityChangedListner = onQuantityChangedListner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //metodi astratti
        View view = inflater.inflate(R.layout.item_menu, viewGroup, false);
        return new MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuViewHolder vh = (MenuViewHolder) viewHolder;           //la riga che vuole essere visualizzata e dov'è l'oggetto
        vh.nameBurger.setText(data.get(i).getNome());
        vh.price.setText(String.valueOf(data.get(i).getPrice()));
        vh.residuo.setText(String.valueOf(data.get(i).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameBurger, address;
        public TextView price;
        public TextView residuo;
        public Button meno;
        public Button piu;

        public ProgressBar progressBar;
        public Restaurant restaurant;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            nameBurger = itemView.findViewById(R.id.name_br);
            address = itemView.findViewById(R.id.address_tv);
            price = itemView.findViewById(R.id.price_br);
            residuo = itemView.findViewById(R.id.residuo_br);
            meno = itemView.findViewById(R.id.meno_br);
            piu = itemView.findViewById(R.id.piu_br);
            progressBar = itemView.findViewById(R.id.progress);

            piu.setOnClickListener(this);
            meno.setOnClickListener(this);
            restaurant = getRestaurant();


            nameBurger.setText(restaurant.getNome());


        }


        @Override
        public void onClick(View view) {

            Cibo cibo = data.get(getAdapterPosition());    //prendo l'oggetto prodotto che è stato cliccato

            if (view.getId() == R.id.piu_br) {
                cibo.increaseQuantity();
                notifyItemChanged(getAdapterPosition());  //cambia un solo elemento
                onQuantityChangedListner.onChange(cibo.getPrice());
            } else if (view.getId() == R.id.meno_br) {

                if (cibo.getQuantity() == 0) return;
                cibo.decreaseQuantity();
                notifyItemChanged(getAdapterPosition());  //cambia un solo elemento
                onQuantityChangedListner.onChange(cibo.getPrice() * -1);
            }





        }
    }


    private Restaurant getRestaurant() {
        return new Restaurant("Panino", "via san giuseppe", 1.00f, R.drawable.mcdonal);
    }
}

