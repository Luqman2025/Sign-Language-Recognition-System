package com.example.imagepro;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
public class HomeFragment extends Fragment {
    public String user_name;
    HomeFragment(String un){
        this.user_name=un;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tv = view.findViewById(R.id.intro);
        tv.setText("Hello " + user_name + " !");
        ShapeableImageView opencam = view.findViewById(R.id.opencam);
        ShapeableImageView openlist = view.findViewById(R.id.openlist);
        ShapeableImageView openutube = view.findViewById(R.id.openutube);

        opencam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Opening Camera", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Text To Sign !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), TextToSign.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        openutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Learn Sign Language", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),LearnSignLanguage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        return view;
    }
}