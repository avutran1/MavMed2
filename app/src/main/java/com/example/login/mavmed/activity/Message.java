package com.example.login.mavmed.activity;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Message {
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
