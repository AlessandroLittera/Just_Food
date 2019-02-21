package com.example.fasteat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fasteat.R;
import com.example.fasteat.Utils;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        logout= findViewById(R.id.logout);
        logout.setOnClickListener(this);


        sharedPreferences= getSharedPreferences("shared", Context.MODE_PRIVATE);
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.logout) {
            //TODO sharedPreferences set "chiave" null
            sPreferences = sharedPreferences.edit();    //editor dello sharedPreferences
            sPreferences.putString("chiave", null);
            sPreferences.apply();
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Utils.LOGOUT_ACTION));
            finish();
        }
    }

}
