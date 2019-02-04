package com.example.fasteat.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fasteat.R;
import com.example.fasteat.Utils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Button registerBtn;

    EditText emailEt;
    EditText passwordEt;
    EditText passwordEt1;
    EditText numberTelefono;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("RegisterActivity","Register Created");

        setContentView(R.layout.activity_register);

        emailEt = findViewById(R.id.email_Et);
        passwordEt= findViewById(R.id.password_Et);
        passwordEt1= findViewById(R.id.password_Et1);
        numberTelefono= findViewById(R.id.numero_telefono);
        registerBtn=findViewById(R.id.register_btn1);

        registerBtn.setOnClickListener(this);

        emailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                doRegister();
            }
        });


        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                doRegister();
            }
        });


        passwordEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                doRegister();
            }
        });

        numberTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                doRegister();
            }
        });




    }


    private void doRegister(){

        String email= emailEt.getText().toString();
        String password= passwordEt.getText().toString();
        String password1=passwordEt1.getText().toString();
        String number=numberTelefono.getText().toString();


        if(Utils.verifyEmail(email)){
            if(Utils.verifyPassword(password)){
                if(Utils.confirmPassword(password, password1)){
                    if(Utils.verifyNumber(number)){
                        registerBtn.setEnabled(true);
                        Utils.printToast(this, R.string.benveuto);
                    }else{
                        registerBtn.setEnabled(false);
                        numberTelefono.setError("Inserire un numero corretto");
                    }
                }else{
                    registerBtn.setEnabled(false);
                    passwordEt1.setError("Password non corretta");
                }
            }else {
                registerBtn.setEnabled(false);
                passwordEt.setError("Password invalida");
            }
        }else{
            registerBtn.setEnabled(false);
            emailEt.setError("Email non valida");
        }

    }





    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.register_btn1){
            doRegister();
        }
    }
}
