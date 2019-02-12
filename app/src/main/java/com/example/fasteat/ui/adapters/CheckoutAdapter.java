package com.example.fasteat.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fasteat.R;
import com.example.fasteat.datamodels.Cibo;
import com.example.fasteat.datamodels.Ordine;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter{
    Ordine ordine;
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<Cibo> arrayList;
    public CheckoutAdapter(Context context,Ordine ordine){
        this.context=context;
        this.layoutInflater= LayoutInflater.from(context);
        this.ordine= ordine;
        this.arrayList= ordine.getArrayList();
    }

    public interface onRemoveRowListner{
        void onChange (float price);
    }
    private onRemoveRowListner onRemoveRowListner;
    public CheckoutAdapter.onRemoveRowListner getOnRemoveRowListner(){
        return onRemoveRowListner;
    }

    public void setOnRemoveRowListner(CheckoutAdapter.onRemoveRowListner onRemoveRowListner) {
        this.onRemoveRowListner = onRemoveRowListner;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_checkout, viewGroup, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CheckoutViewHolder cvh = (CheckoutViewHolder) viewHolder;
        cvh.quantity.setText(String.valueOf(ordine.getArrayList().get(i).getQuantity()));
        cvh.name_br.setText(ordine.getArrayList().get(i).getNome());
        cvh.price_br.setText(String.valueOf(ordine.getArrayList().get(i).getPrice()));
    }

    @Override
    public int getItemCount() {
        return ordine.getArrayList().size();
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder{

        TextView name_br, price_br, quantity;
        Button delete;
        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity= itemView.findViewById(R.id.quantity);
            name_br=itemView.findViewById(R.id.name_br);
            price_br=itemView.findViewById(R.id.price_br);
            delete=itemView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder alert= new AlertDialog.Builder(context);
                    alert.setMessage("Sicuro di eliminare il prodotto dal carrello?");
                    alert.setPositiveButton("si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Cibo cibo= arrayList.get(getAdapterPosition());
                            float price = cibo.getPrice()*cibo.getQuantity();
                            arrayList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            onRemoveRowListner.onChange(price);
                        }
                    });
                    alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return ;
                        }
                    });
                    alert.create().show();

                }
            });



        }
    }
}