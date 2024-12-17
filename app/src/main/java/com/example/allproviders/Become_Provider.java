package com.example.allproviders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Become_Provider extends AppCompatActivity {

    Button buttonUpload, buttonSubmit;
    EditText editTextName, editTextProfession,editTextBio, editTextDivision, editTextDistrict, editTextSubdistrict,
            editTextNumber, editTextEmail, editTextAge;
    ImageView imageViewProvider;
    String encodeImageString;
    public static final String url = "https://mychatroom12345.000webhostapp.com/all_providers.php";
    Bitmap bitmap;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_provider);

        //====Connecting with item=========================================//
        buttonUpload = findViewById(R.id.buttonUpload);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        editTextName = findViewById(R.id.editTextName);
        editTextProfession = findViewById(R.id.editTextProfession);
        editTextBio = findViewById(R.id.editTextBio);
        editTextDivision = findViewById(R.id.editTextDivision);
        editTextDistrict = findViewById(R.id.editTextDistrict);
        editTextSubdistrict = findViewById(R.id.editTextSubdistrict);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        imageViewProvider = findViewById(R.id.imageViewProvider);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //---------------------------*****--------------------------------//

        //==== Upload Image =========================================//
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadbotton();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploaddatatodb();

            }
        });
        //---------------------------*****--------------------------------//

        NavigationBotton();
    }
    //==== Bottom Navigation Method =========================================//
    private void NavigationBotton() {
        bottomNavigationView.setSelectedItemId(R.id.buttonProvider);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.buttonAllProvider:
                        Intent intent = new Intent(Become_Provider.this, All_Providers.class);
                        intent.putExtra("Name", "All");
                        startActivity(intent);

                        overridePendingTransition(0,0);
                        return false;

                    case R.id.buttonProvider:
                        return false;
                }
                return false;
            }
        });

    }
    //---------------------------*****-------------------------------//

    //==== Upload Bottom Method =========================================//
    private void uploadbotton() {

        Dexter.withContext(Become_Provider.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse)
                    {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }
    //---------------------------*****--------------------------------//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {

        if(requestCode == 1 && resultCode==RESULT_OK)
        {
            Uri filepath=data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewProvider.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);

            }catch (Exception ex)
            {

            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void encodeBitmapImage(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);

    }

    //=== Upload Data tp Database =========================================//
    private void uploaddatatodb() {

        final String name = editTextName.getText().toString().trim();
        final String profession = editTextProfession.getText().toString().trim();
        final String bio = editTextBio.getText().toString().trim();
        final String division = editTextDivision.getText().toString().trim();
        final String district = editTextDistrict.getText().toString().trim();
        final String subdistrict = editTextSubdistrict.getText().toString().trim();
        final String number = editTextNumber.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                editTextName.setText("");
                editTextProfession.setText("");
                editTextBio.setText("");
                editTextDivision.setText("");
                editTextDistrict.setText("");
                editTextSubdistrict.setText("");
                editTextNumber.setText("");
                editTextEmail.setText("");
                editTextAge.setText("");
                imageViewProvider.setImageResource(R.drawable.blank_profile);
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                Map<String,String> map = new HashMap<String, String>();
                map.put("name", name);
                map.put("profession", profession);
                map.put("bio", bio);
                map.put("division", division);
                map.put("district", district);
                map.put("subdistrict", subdistrict);
                map.put("number", number);
                map.put("email", email);
                map.put("age", age);
                map.put("image", encodeImageString);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
    //------------------------------*****---------------------------------//

}