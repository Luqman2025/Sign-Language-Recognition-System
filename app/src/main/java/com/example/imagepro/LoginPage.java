package com.example.imagepro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
public class LoginPage extends AppCompatActivity {
    EditText pwd,uname;
    TextView reg;
    Button login;
    DatabaseReference db;
    public static final String PREF_NAME = "LoginPref";
    Intent lit,mit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
//        getSupportActionBar().hide();
        pwd = (EditText) findViewById(R.id.pwd);
        uname = (EditText) findViewById(R.id.uname);
        lit = new Intent(LoginPage.this,RegisterPage.class);
        //login verification of user

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwd.getText().toString().isEmpty() || uname.getText().toString().isEmpty()){
                    Toast.makeText(LoginPage.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                    return;}
                else
                    {
                        SQLLitedb db=new SQLLitedb(getApplicationContext());
                        boolean f=db.getUser(uname.getText().toString(),pwd.getText().toString(),"");
                        if(f==true)
                        {
                            Toast.makeText(getApplicationContext(),"Login Successful!! ",Toast.LENGTH_SHORT).show();
                            SharedPreferences sp=getSharedPreferences(LoginPage.PREF_NAME,0);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putBoolean("hasLoggedIn",true);
                            editor.putString("User",uname.getText().toString());
                            editor.commit();
                            Intent it = new Intent(LoginPage.this,NavigationPage.class);
                            startActivity(it);
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Incorrect Username Or Password ",Toast.LENGTH_SHORT).show();
                    }
                }

        });
        reg = (TextView) findViewById(R.id.regtext);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(lit);
                finish();
            }
        });
    }
}