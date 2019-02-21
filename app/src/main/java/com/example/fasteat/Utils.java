package com.example.fasteat;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static final String LOGIN_ACTION= "LOGIN_ACTION";
    public static final String LOGOUT_ACTION = "LOGOUT_ACTION";


    public static void printToast(Context contesto, int contenuto)
    {
        Toast.makeText(contesto, contenuto, Toast.LENGTH_LONG).show();
    }

    public static boolean verifyPassword(String password) {
        if(password.length()>=6){
            return true;
        }
       /* if(password.equals(password.toLowerCase())){
            return false;
        }
        if(password.equals(password.toUpperCase())){
            return false;
        }
        if(!password.contains("0")||password.contains("1")||password.contains("2")||password.contains("3")||password.contains("4")||password.contains("5")||password.contains("6")||password.contains("7")||password.contains("8")||password.contains("9")){
            return false;
        }*/
        return false;
    }

    public static boolean verifyEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean verifyNumber(String number){
        if(number.length()==10)
            return true;
        return false;
    }
    public static boolean confirmPassword (String password, String password1){
        if(password.equals(password1))
            return true;
        return false;
    }
}
