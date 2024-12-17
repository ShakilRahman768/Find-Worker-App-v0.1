package com.example.allproviders;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Profile extends AppCompatActivity {

    TextView emailtextview;
    Button deletebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailtextview = findViewById(R.id.emailid);
        deletebutton =  findViewById(R.id.deleteid);


        String Enameprofile = LogIn.profile_email;

        emailtextview.setText(""+Enameprofile);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://mychatroom12345.000webhostapp.com/delete_provider.php?email="+Enameprofile;

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        new AlertDialog.Builder(Profile.this)
                                .setTitle("Server Response")
                                .setMessage(response)
                                .show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Error: ",error.toString());
                    }
                }

                );

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
            }
        });


    }
}