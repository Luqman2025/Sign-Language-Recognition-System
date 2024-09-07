package com.example.imagepro;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextToSign extends AppCompatActivity implements SignsAdapter.ItemClicked{
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<SignsList> signs;
    ImageView iv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_sign);
        getSupportActionBar().hide();
        Map<String, String> downloadUrls = new HashMap<>();
        iv=findViewById(R.id.img);

        downloadUrls.put("A", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FA%2F1.jpeg?alt=media&token=27f5e68a-701e-4afa-9c8b-5496f3015980");
        downloadUrls.put("B", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FB%2F1.jpeg?alt=media&token=89cd7d82-359c-4ecc-8718-a95af1bfe5aa");
        downloadUrls.put("C", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FC%2F1.jpeg?alt=media&token=aaf28bd4-0af4-4c5b-b845-9105273718b2");
        downloadUrls.put("D", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FD%2F1.jpeg?alt=media&token=7c3df25c-c778-4eb5-9789-1267d55c2b32");
        downloadUrls.put("E", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FE%2F1.jpeg?alt=media&token=674646e7-0882-410e-b939-6ba7ad9f31fe");
        downloadUrls.put("F", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FF%2F1.jpeg?alt=media&token=b81946d0-4582-4996-b903-4a68ca5cf345");
        downloadUrls.put("G", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FG%2F1.jpeg?alt=media&token=81ca80e3-bae5-45a6-bc79-986409c582d4");
        downloadUrls.put("H", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FH%2F1.jpeg?alt=media&token=c369a85d-3940-465f-8b2a-baf5a3dd03bc");
        downloadUrls.put("I", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FI%2F1.jpeg?alt=media&token=395666db-f753-422a-9eb5-28686c23c86b");
        downloadUrls.put("J", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FJ%2F1.jpeg?alt=media&token=f54535e0-3157-481d-b083-a81bc3159819");
        downloadUrls.put("K", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FK%2F1.jpeg?alt=media&token=b6796787-1ac0-4d4c-aeb0-53e8c3006585");
        downloadUrls.put("L", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FL%2F1.jpeg?alt=media&token=c53c5d80-1361-47a7-a594-5fc7f72974f4");
        downloadUrls.put("M", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FM%2F1.jpeg?alt=media&token=45201b5b-4c29-433e-bd76-38b607e39529");
        downloadUrls.put("N", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FN%2F1.jpeg?alt=media&token=71e9ed1c-f8f9-46fa-aed8-d3e8138339df");
        downloadUrls.put("O", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FO%2F1.jpeg?alt=media&token=3649e753-3c59-4773-9e61-50612c60008b");
        downloadUrls.put("P", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FP%2F1.jpeg?alt=media&token=455e6471-4d61-4061-b7a3-4addd154e6d4");
        downloadUrls.put("Q", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FQ%2F1.jpeg?alt=media&token=cc59cbf7-ccbe-4b99-bc07-cb43cee6aa02");
        downloadUrls.put("R", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FR%2F1.jpeg?alt=media&token=22f8d203-d9f2-4f13-9baf-a5836567d8d6");
        downloadUrls.put("S", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FS%2F1.jpeg?alt=media&token=23b82974-0d05-4e3c-af98-c488ad127312");
        downloadUrls.put("T", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FT%2F1.jpeg?alt=media&token=f82020b0-6d9d-4d0d-adc3-ecb705f7a3f3");
        downloadUrls.put("U", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FU%2F1.jpeg?alt=media&token=2808137f-df6a-4ca7-81a1-e9cb4352d6da");
        downloadUrls.put("V", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FV%2F1.jpeg?alt=media&token=28e0d846-5e58-41a1-bdfe-e3f30ed0ef79");
        downloadUrls.put("W", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FW%2F1.jpeg?alt=media&token=69ed66f2-12df-4814-8a3e-32c8bb18923d");
        downloadUrls.put("X", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FX%2F1.jpeg?alt=media&token=0fc312b9-f0de-4f70-8595-e88cab7f756d");
        downloadUrls.put("Y", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FY%2F1.jpeg?alt=media&token=0d10d271-ed48-481e-8c4a-0bed10dd1610");
        downloadUrls.put("Z", "https://firebasestorage.googleapis.com/v0/b/letscommunicateapp.appspot.com/o/Dataset%2FZ%2F1.jpeg?alt=media&token=aa63a0b7-69f6-465b-bfc8-26a79f43e7ba");
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        signs = new ArrayList<SignsList>();
        myAdapter = new SignsAdapter(this,signs);
        recyclerView.setAdapter(myAdapter);
        et = (EditText) findViewById(R.id.ip);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                iv.setVisibility(View.GONE);
                if(s.length() > 0) {
                    char lastChar = s.charAt(s.length() - 1);
                    if (count > before && !Character.isDigit(lastChar)) {
                        // Check if the last character is not a whitespace and not a backspace
                        if (!Character.isWhitespace(lastChar)) {
                            char letter = Character.toUpperCase(lastChar);
                            String letterTUrl = downloadUrls.get(String.valueOf(letter));
//                            System.out.println(letterTUrl);
                            signs.add(new SignsList(String.valueOf(letter), letterTUrl));
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                    else if (before > count && !Character.isWhitespace(lastChar)) {
                        // Backspace was pressed
                        signs.remove(signs.size() - 1);
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    signs.clear();
                    myAdapter.notifyDataSetChanged();
                    iv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onItemClicked(int index) {
        //null
    }
}