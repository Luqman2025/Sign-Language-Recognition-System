package com.example.imagepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class NavigationPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
        SharedPreferences sharedPreferences=getSharedPreferences(LoginPage.PREF_NAME,0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.navbarcolor));
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.abc,R.string.xyz);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View headerView = navigationView.getHeaderView(0);

        TextView usernameTextView = headerView.findViewById(R.id.uname);
        TextView emailTextView = headerView.findViewById(R.id.emailid);
        usernameTextView.setText(sharedPreferences.getString("User",""));
        emailTextView.setText(sharedPreferences.getString("email",""));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(sharedPreferences.getString("User",""))).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }

        Button log_out=findViewById(R.id.logout_nav);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(it);
                SharedPreferences sp=getSharedPreferences(LoginPage.PREF_NAME,0);
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean("hasLoggedIn",false);
                editor.commit();
                finish();
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        SharedPreferences sharedPreferences=getSharedPreferences(LoginPage.PREF_NAME,0);
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(sharedPreferences.getString("User",""))).commit();
        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment(sharedPreferences.getString("User",""),sharedPreferences.getString("email",""),sharedPreferences.getString("pass",""))).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}