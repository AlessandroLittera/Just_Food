package com.example.fasteat.ui.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fasteat.R;
import com.example.fasteat.datamodels.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;

        public RestaurantAdapter(Context context, ArrayList<Restaurant> data){
            inflater= LayoutInflater.from(context);
            this.data=data;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {  //metodi astratti
        View view= inflater.inflate(R.layout.item_restaurant,viewGroup,false);
        return new RestaurantViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder;           //la riga che vuole essere visualizzata e dov'è l'oggetto
        vh.restaurantName.setText(data.get(i).getNome());
        vh.resturantAddress.setText(data.get(i).getIndirizzo());
        vh.resturantPrice.setText(String.valueOf(data.get(i).getPrezzo()));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        public TextView restaurantName;
        public TextView resturantAddress;
        public TextView resturantPrice;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName= itemView.findViewById(R.id.name_tv);
            resturantAddress=itemView.findViewById(R.id.address_tv);
            resturantPrice=itemView.findViewById(R.id.price_tv);
        }


    }


}

