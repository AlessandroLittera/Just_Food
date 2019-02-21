package com.example.fasteat.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fasteat.R;
import com.example.fasteat.datamodels.Restaurant;
import com.example.fasteat.ui.activities.ShopActivity;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;

    private boolean griglia;
    Context context;


    public boolean getGriglia(){
        return griglia;
    }

    public void setGriglia(boolean griglia) {
        this.griglia = griglia;
    }

    public void setData(ArrayList<Restaurant> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ArrayList<Restaurant> getData() {
        return data;
    }

    public RestaurantAdapter(Context context, ArrayList<Restaurant> data){
                inflater= LayoutInflater.from(context);
                this.data=data;
                this.context=context;
            }


            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                    //metodi astratti

            int layout;
            if(griglia==true){
                layout = R.layout.item_restaurant;
            }else{
                layout = R.layout.item_resturant1;
            }
            View view= inflater.inflate(layout,viewGroup,false);
            return new RestaurantViewHolder(view);
            }


            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            RestaurantViewHolder vh = (RestaurantViewHolder) viewHolder;           //la riga che vuole essere visualizzata e dov'è l'oggetto
            vh.restaurantName.setText(data.get(i).getNome());
            vh.resturantAddress.setText(data.get(i).getIndirizzo());
            vh.resturantPrice.append(String.valueOf(data.get(i).getPrezzo()));
            Glide.with(context).load(data.get(i).getUrl()).into(vh.resturantImage);

            }

            @Override
            public int getItemCount() {
                return data.size();
            }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        public TextView restaurantName;
        public TextView resturantAddress;
        public TextView resturantPrice;
        public ImageView resturantImage;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName= itemView.findViewById(R.id.name_tv);
            resturantAddress=itemView.findViewById(R.id.address_tv);
            resturantPrice=itemView.findViewById(R.id.price_tv);
            resturantImage=itemView.findViewById(R.id.logo_tv);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ShopActivity.class);
                    i.putExtra("idRestaurant",data.get(getAdapterPosition()).getId());
                    i.putExtra("id_url",data.get(getAdapterPosition()).getUrl());
                    i.putExtra("nomeRestaurant",data.get(getAdapterPosition()).getNome());
                    i.putExtra("addressRestaurant",data.get(getAdapterPosition()).getIndirizzo());
                    context.startActivity(i);
                }
            });



        }

    }




}

