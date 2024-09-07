package com.example.imagepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences(LoginPage.PREF_NAME,0);
                if(sharedPreferences.getBoolean("hasLoggedIn",false))
                {
                    Intent it = new Intent(MainActivity.this,HomePage.class);
                    startActivity(it);
                    finish();
                }
                else{
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
                finish();}
            }
        },1000);
    }
}