package com.example.allproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Provider extends AppCompatActivity {

    TextView NName, PProfession, DDivision,EEmail,BBio,DDistrict,SSubdistrict,NNumber,AAge;
    ImageView IImage;

    public static Bitmap myBitmap= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        NName = findViewById(R.id.NName);
        PProfession = findViewById(R.id.PProfession);
        DDivision = findViewById(R.id.DDivision);
        IImage = findViewById(R.id.IImage);
        EEmail = findViewById(R.id.EEmail);
        BBio = findViewById(R.id.BBio);
        DDistrict = findViewById(R.id.DDistrict);
        SSubdistrict = findViewById(R.id.SSubdistrict);
        NNumber = findViewById(R.id.NNumber);
        AAge = findViewById(R.id.AAge);

        String name = getIntent().getStringExtra("Name");
        String profession = getIntent().getStringExtra("Profession");
        String division = getIntent().getStringExtra("Division");
        String email = getIntent().getStringExtra("email");
        String bio = getIntent().getStringExtra("bio");
        String district = getIntent().getStringExtra("district");
        String subdistrict = getIntent().getStringExtra("subdistrict");
        String number = getIntent().getStringExtra("number");
        String age = getIntent().getStringExtra("age");

        NName.setText(name);
        PProfession.setText(profession);
        DDivision.setText(division);
        EEmail.setText(email);
        BBio.setText(bio);
        DDistrict.setText(district);
        SSubdistrict.setText(subdistrict);
        NNumber.setText(number);
        AAge.setText(age);

        if(myBitmap!=null) IImage.setImageBitmap(myBitmap);
    }
}