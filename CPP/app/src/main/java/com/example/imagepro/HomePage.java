package com.example.imagepro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

public class HomePage extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent mit = new Intent(HomePage.this,LoginPage.class);
        startActivity(mit);
        SharedPreferences sp=getSharedPreferences(LoginPage.PREF_NAME,0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("hasLoggedIn",false);
        editor.commit();
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu,menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setTitle("Let's Communicate");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient));
        TextView tv = findViewById(R.id.intro);
        SharedPreferences sharedPreferences=getSharedPreferences(LoginPage.PREF_NAME,0);
            tv.setText("Hello "+sharedPreferences.getString("User","")+" !");
        SM sessionManager = new SM(getApplicationContext());
        sessionManager.setLogin(true);
        ShapeableImageView opencam = (ShapeableImageView) findViewById(R.id.opencam);
        ShapeableImageView openlist = (ShapeableImageView) findViewById(R.id.openlist);
        ShapeableImageView openutube = (ShapeableImageView) findViewById(R.id.openutube);
        opencam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, TextToSign.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        openutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,LearnSignLanguage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

}