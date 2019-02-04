package com.example.fasteat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
//import android.widget.Switch;

import com.example.fasteat.R;
import com.example.fasteat.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button loginBtn;
    Button registerBtn;

    //Switch aSwitch;
    LinearLayout window;

    EditText emailEt;
    EditText passwordEt;

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
        //aSwitch=findViewById(R.id.a_switch);

        window=findViewById(R.id.window);


        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

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


        if(!Utils.verifyEmail(email)){
            Utils.printToast(this ,R.string.email_novalida);
            return;

        }if(!Utils.verifyPassword(epassword)){
            Utils.printToast(this, R.string.password_invalida);
            return;

        }
        Utils.printToast(this, R.string.accesso_eseguito);
       // Intent i=new Intent(this, WelcomeActivity.class); //passare da un activity ad un'altra con onClick
        //i.putExtra("benvenuto", email);
       // startActivity(i);    //il sistema va


    }




}

