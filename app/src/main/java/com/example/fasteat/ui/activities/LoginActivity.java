package com.example.fasteat.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
//import android.widget.Switch;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.fasteat.R;
import com.example.fasteat.Utils;
import com.example.fasteat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<String> {
    Button loginBtn;
    Button registerBtn;

    LinearLayout window;

    EditText emailEt;
    EditText passwordEt;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sPreferences;

    final static int LUNGHEZZA_PASSWORD=6;

    protected void onCreate(@Nullable Bundle sevedInstanceState){
        super.onCreate(sevedInstanceState);
        Log.i("LoginActivity","Activity created");
        //Attach to this Activity the activity_layout.xml
        setContentView(R.layout.activity_login);
        emailEt = findViewById(R.id.email_Et);
        passwordEt= findViewById(R.id.password_Et);
        loginBtn = findViewById(R.id.login_btn);
        registerBtn= findViewById(R.id.register_btn);

        window=findViewById(R.id.window);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        sharedPreferences= getSharedPreferences("shared", Context.MODE_PRIVATE);


        //aSwitch= (Switch) findViewById(R.id.a_switch);
        //Boolean switchCase= aSwitch.isChecked();
       /* aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    window.setBackgroundColor(R.color.colorBlack);
                    emailEt.setBackgroundColor(Color.WHITE);
                    passwordEt.setBackgroundColor(Color.WHITE);
                }else{
                    window.setBackgroundColor(Color.WHITE);
                }

            }
        });
        */

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login_btn){
            doLogin();
        }else if(v.getId()==R.id.register_btn){
            doRegister();
        }
    }

    private void doRegister() {
        Utils.printToast(this,R.string.registrati);
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);

    }


    private void doLogin() {

        String email= emailEt.getText().toString();
        String epassword= passwordEt.getText().toString();
        String endpoint= "auth/local";
        RestController restController= new RestController(this);


        if(!Utils.verifyEmail(email)){
            Utils.printToast(this ,R.string.email_novalida);
            return;
        }if(!Utils.verifyPassword(epassword)){
            Utils.printToast(this, R.string.password_invalida);
            return;
        }
        Utils.printToast(this, R.string.accesso_eseguito);
        restController.postLoginRequest(endpoint, this, this, epassword, email);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
       // Intent i=new Intent(this, WelcomeActivity.class); //passare da un activity ad un'altra con onClick
        //i.putExtra("benvenuto", email);
       // startActivity(i);    //il sistema va
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Errore Login", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        Log.i("tag","ok");
        try {
            JSONObject jsonObject= new JSONObject(response);
            String token = jsonObject.getString("jwt");
            sPreferences = sharedPreferences.edit();    //editor dello sharedPreferences
            sPreferences.putString("chiave", token);
            sPreferences.apply();


            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Utils.LOGIN_ACTION));
            finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Benvenuto",Toast.LENGTH_LONG).show();
    }
}

