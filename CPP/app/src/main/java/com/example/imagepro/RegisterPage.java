package com.example.imagepro;

//import android.app.AlertDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class RegisterPage extends AppCompatActivity {
//    EditText pwd,uname,email;
//    TextView reg;
//    Button sendotp,verifyOtpButton;
//    DatabaseReference db;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register_page);
//        pwd = (EditText) findViewById(R.id.pwd);
//        uname = (EditText) findViewById(R.id.uname);
//        email = (EditText) findViewById(R.id.email);
//        sendotp = (Button) findViewById(R.id.sendotp);
//        sendotp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db = FirebaseDatabase.getInstance().getReference().child("Users");
//                db.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        boolean usernameExists = false;
//                        boolean emailExists = false;
//                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                            String dbUsername = userSnapshot.child("Username").getValue(String.class);
//                            String dbEmail = userSnapshot.child("Email").getValue(String.class);
//
//                            if (dbUsername.equals(uname.getText().toString())) {
//                                usernameExists = true;
//                            }
//                            if (dbEmail.equals(email.getText().toString())) {
//                                emailExists = true;
//                            }
//                        }
//                        if (usernameExists) {
//                            // Username already exists
//                            Toast.makeText(RegisterPage.this, "Username already exists", Toast.LENGTH_SHORT).show();
//                        } else if (emailExists) {
//                            // Email already exists
//                            Toast.makeText(RegisterPage.this, "Email already exists", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//
//                            //Code to sent OTP
//
//                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterPage.this);
//                            View view = getLayoutInflater().inflate(R.layout.otp, null);
//                            builder.setView(view);
//                            AlertDialog dialog = builder.create();
//                            dialog.show();
//                            TextView tv = (TextView) view.findViewById(R.id.txt1);
//                            String str = tv.getText().toString() + email.getText().toString();
//                            tv.setText(str);
//                            EditText otpno1 = view.findViewById(R.id.otpno1);
//                            EditText otpno2 = view.findViewById(R.id.otpno2);
//                            EditText otpno3 = view.findViewById(R.id.otpno3);
//                            EditText otpno4 = view.findViewById(R.id.otpno4);
//                            EditText otpno5 = view.findViewById(R.id.otpno5);
//                            EditText otpno6 = view.findViewById(R.id.otpno6);
//                            verifyOtpButton = view.findViewById(R.id.verifyotp);
//                            verifyOtpButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    String otpno = otpno1.getText().toString() + otpno2.getText().toString() + otpno3.getText().toString() + otpno4.getText().toString() + otpno5.getText().toString() + otpno6.getText().toString();
//                                    // Verify the OTP
//                                    if (otpno.equals("123456")) { // Change to your actual OTP verification logic
//                                        Toast.makeText(RegisterPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                                        dialog.dismiss();
//                                        Intent it = new Intent(RegisterPage.this, HomePage.class);
//                                        it.putExtra("User", uname.getText().toString());
//                                        startActivity(it);
//                                    } else {
//                                        Toast.makeText(RegisterPage.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                            dialog.setCanceledOnTouchOutside(true);
//
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Log.e("Firebase", "Error : " + error.getMessage());
//                    }
//                });
//            }
//        });
//        reg = (TextView) findViewById(R.id.regtext);
//        reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //intent code to go to register page
//                Intent it = new Intent(RegisterPage.this,LoginPage.class);
//                startActivity(it);
//            }
//        });
//    }
//}
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {
    EditText pwd,uname,email;
    TextView reg;
    Button sendotp,verifyOtpButton;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        getSupportActionBar().hide();
        pwd = (EditText) findViewById(R.id.pwd);
        uname = (EditText) findViewById(R.id.uname);
        email = (EditText) findViewById(R.id.email);
        sendotp = (Button) findViewById(R.id.sendotp);

        SQLLitedb db=new SQLLitedb(this);
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un=uname.getText().toString().trim();
                String pass=pwd.getText().toString();
                String em=email.getText().toString();
                if(un.equals("")||pass.equals("")||em.equals(""))
                    Toast.makeText(getApplicationContext(),"Please Fill All The Fields",Toast.LENGTH_LONG);
                else if(validation(em,pass)){
                String otp=otp();
                SQLLitedb db=new SQLLitedb(getApplicationContext());
                boolean f=db.getUser(un,pass,em);
                if(f==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterPage.this);
                            View view = getLayoutInflater().inflate(R.layout.otp, null);
                            builder.setView(view);
                            AlertDialog dialog = builder.create();
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.show();
                            new SendMailTask(RegisterPage.this).execute(email.getText().toString(),otp);
                            TextView tv = (TextView) view.findViewById(R.id.txt1);
                            String str = tv.getText().toString() + email.getText().toString();
                            tv.setText(str);
                            EditText otpno1 = view.findViewById(R.id.otpno1);
                            verifyOtpButton = view.findViewById(R.id.verifyotp);
                            verifyOtpButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String otpno = otpno1.getText().toString();
                                    if (otpno.equals(otp)) { // Change to your actual OTP verification logic
                                        db.addUser(un,pass,em);
                                        Toast.makeText(getApplicationContext(),"User Registered Successfully!!",Toast.LENGTH_LONG).show();
                                        SharedPreferences sp=getSharedPreferences(LoginPage.PREF_NAME,0);
                                        SharedPreferences.Editor editor=sp.edit();
                                        editor.putBoolean("hasLoggedIn",true);
                                        editor.putString("User",uname.getText().toString());
                                        editor.commit();
                                        Intent it = new Intent(RegisterPage.this,HomePage.class);
                                        startActivity(it);
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterPage.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                    Toast.makeText(getApplicationContext(),"Username Or EmailId Already Exist!!",Toast.LENGTH_LONG).show();
            }
            }
        });

        reg = (TextView) findViewById(R.id.regtext);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent code to go to register page
                Intent it = new Intent(RegisterPage.this,LoginPage.class);
                startActivity(it);
                finish();
            }
        });
    }
    public boolean validation(String email,String pwd){

        boolean em=true;
        boolean pw=true;
        if(!isValidPassword(pwd)){
            pw=false;
            Toast.makeText(getApplicationContext(),"Please Enter Valid Password",Toast.LENGTH_SHORT).show();
        }
        if(!isValidEmail(email)){
            em=false;
            Toast.makeText(getApplicationContext(),"Please Enter an Valid Email ID",Toast.LENGTH_SHORT).show();
        }
        if(em==false||pw==false)
            return false;
        else
            return true;
    }
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public String otp(){
        String numbers = "0123456789";
        String otp = new String();
        Random random = new Random();

        // Generate OTP of specified length
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(numbers.length());
            otp+=numbers.charAt(index);
        }
        return otp;
    }
}