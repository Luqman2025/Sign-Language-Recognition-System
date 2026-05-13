package com.example.imagepro;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    public static String user_name,gmail,password;
    AlertDialog dialog;
    ProfileFragment(String un,String mail,String pass){
        user_name=un;
        gmail=mail;
        password=pass;
    }

    EditText name,email,pwd;
    TextView disp;
    Button edit,save,cancel,logout,verifyOtpButton;
    ViewGroup relative;
    String otp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        disp = view.findViewById(R.id.displayname);
        disp.setText("Hello " + user_name + " !");

        name = view.findViewById(R.id.username);
        name.setText(user_name);

        email = view.findViewById(R.id.emailid);
        email.setText(gmail);

        pwd = view.findViewById(R.id.password);
        pwd.setText(password);

        relative = view.findViewById(R.id.editinglayout);
        edit = view.findViewById(R.id.edit);
        save = view.findViewById(R.id.save);
        cancel = view.findViewById(R.id.cancel);
        logout = view.findViewById(R.id.logout);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp=RegisterPage.otp();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.otp2, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                new SendMailTask(getActivity()).execute(email.getText().toString(),otp);
                EditText otpno1 = view.findViewById(R.id.otpno2);
                verifyOtpButton = view.findViewById(R.id.verifyotp2);
                verifyOtpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String otpno = otpno1.getText().toString();
                        if (otpno.equals(otp)) {
                            Toast.makeText(getActivity(), "Verified Successfully", Toast.LENGTH_SHORT).show();
                            name.setEnabled(true);
                            email.setEnabled(true);
                            pwd.setEnabled(true);
                            edit.setVisibility(View.GONE);
                            relative.setVisibility(View.VISIBLE);
                            dialog.hide();
                        }
                        else
                            Toast.makeText(getActivity(), "Invalid OTP", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag=RegisterPage.isValidPassword(pwd.getText().toString());
                if(flag==true){
                    SQLLitedb db1=new SQLLitedb(getContext());
                    db1.updatePass(name.getText().toString(),pwd.getText().toString());

                        Toast.makeText(getActivity(), "Password Updated Successfully!!", Toast.LENGTH_SHORT).show();
                        name.setEnabled(false);
                        email.setEnabled(false);
                        pwd.setEnabled(false);
                        edit.setVisibility(View.VISIBLE);
                        relative.setVisibility(View.GONE);
                    }
                else
                    Toast.makeText(getActivity(), "Enter Valid Password", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(user_name);
                email.setText(gmail);
                pwd.setText(password);
                name.setEnabled(false);
                email.setEnabled(false);
                pwd.setEnabled(false);
                edit.setVisibility(View.VISIBLE);
                relative.setVisibility(View.GONE);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginPage.class));
            }
        });
        return view;
    }
    public static void setValues(String un,String pass,String email){
        user_name=un;
        password=pass;
        gmail=email;
    }
}