package com.example.fasteat.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.fasteat.R;
import com.example.fasteat.Utils;
import com.example.fasteat.datamodels.Restaurant;
import com.example.fasteat.services.RestController;
import com.example.fasteat.ui.adapters.RestaurantAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    private final static String endpoint="restaurants";
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList = new ArrayList<>();
        Menu menu;
        private LoginReceiver receiver;
        private LogoutReceiver receiverLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this,arrayList);

        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);

        RestController restController= new RestController(this);
        restController.getRequest(endpoint, this, this);

        receiver = new LoginReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Utils.LOGIN_ACTION));

        receiverLogout= new LogoutReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiverLogout, new IntentFilter(Utils.LOGOUT_ACTION));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiverLogout);
    }

    /*private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("Mc Donald's", "Via Tiburtina, 235", 8.00f,"https://rovato5stelle.files.wordpress.com/2013/11/mcdonald.jpg"));
        arrayList.add(new Restaurant("Burgher King","Via Tibutina, 245", 6.00f,"https://rovato5stelle.files.wordpress.com/2013/11/mcdonald.jpg"));
        arrayList.add(new Restaurant("KFC","Via Cesenatico, 101",10.00f,"https://rovato5stelle.files.wordpress.com/2013/11/mcdonald.jpg"));
        arrayList.add(new Restaurant("Sushi San","Via Eugenio Checchi, 81",15.00f,"https://rovato5stelle.files.wordpress.com/2013/11/mcdonald.jpg"));

        return arrayList;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.manu_main,menu);
        this.menu= menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId()==R.id.modify_window){
            setLayoutManager();
            return true;
        }else if(item.getItemId()==R.id.login_menu){
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }else if(item.getItemId()==R.id.checkout_menu){
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        }else if(item.getItemId()==R.id.register_menu){
            startActivity(new Intent(this, RegisterActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void setLayoutManager() {
        layoutManager=new LinearLayoutManager(this);
        adapter.setGriglia(!adapter.getGriglia());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Errore",error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray= new JSONArray(response);
            for(int i=0; i<jsonArray.length();i++){
                Restaurant r= new Restaurant(jsonArray.getJSONObject(i));
                arrayList.add(r);
            }
            adapter.setData(arrayList);
        }catch (JSONException e){
            Log.e ("exceptionMessage", e.getMessage());
        }
    }

    public class LoginReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO manage login
            menu.findItem(R.id.login_menu).setTitle(R.string.profile).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    startActivity(new Intent(MainActivity.this, UserActivity.class));
                    return true;
                }
            });
        }
    }

    public class LogoutReceiver extends  BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            menu.findItem(R.id.login_menu).setTitle(R.string.login).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    return false;
                }
            });
        }
    }


}
