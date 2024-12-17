package com.example.allproviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class All_Providers extends AppCompatActivity implements SelectListner {

    RecyclerView recyclerView;
    String url;
    List<Model> imagelist;
    Model model;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    BottomNavigationView bottomNavigationView;
    ProgressBar progressBar;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_providers);

        //=== Connecting with item =========================================//
        recyclerView = findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        progressBar = findViewById(R.id.progressBar);
        searchView = findViewById(R.id.searchView);
        //---------------------------*****--------------------------------//

        imagelist = new ArrayList<>();
        adapter = new Adapter(this,imagelist, this);
        recyclerView.setAdapter(adapter);
        searchView.clearFocus();


        //=== Category of Provider =========================================//
        String category = getIntent().getStringExtra("Name");

        if(category.equals("Driver")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/driver_fetch.php";
        }

        else if(category.equals("Electrician")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/electrician_fetch.php";
        }

        else if(category.equals("Tutor")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/tutor_fetch.php";
        }

        else if(category.equals("Painter")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_painter.php";
        }

        else if(category.equals("Sweeper")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_sweeper.php";
        }

        else if(category.equals("PC Repairer")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_pc_repairer.php";
        }

        else if(category.equals("Plumber")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_plumber.php";
        }

        else if(category.equals("Chef")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_chef.php";
        }

        else if(category.equals("Cleaner")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_cleaner.php";
        }

        else if(category.equals("Gardener")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_gardener.php";
        }

        else if(category.equals("Laborer")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_laborer.php";
        }

        else if(category.equals("Photographer")) {
            bottomNavigationView.setVisibility(View.GONE);
            url = "https://mychatroom12345.000webhostapp.com/fetch_photographer.php";
        }

        else{
            url = "https://mychatroom12345.000webhostapp.com/all_providers_fetch.php";
        }

        //---------------------------*****--------------------------------//


        getimages();

        navigationbotton();

        //=== Search View =========================================//
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }
        });

        //---------------------------*****--------------------------------//

    }

    //=== Search Function =========================================//
    private void fileList(String text) {

        List<Model> filteredList = new ArrayList<>();
        for(Model item: imagelist){
            if(item.getProfession().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
            else if(item.getDivision().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.setFilteredList(filteredList);

        }
    }
    //---------------------------*****--------------------------------//

    //====*** Get Image and data Method ***=========================================//
    private void getimages(){

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                imagelist.clear();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if(success.equals("1")){
                        for(int i = 0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String url2 = object.getString("image");
                            String name = object.getString("name");
                            String profession = object.getString("profession");
                            String division = object.getString("division");
                            String email = object.getString("email");
                            String bio = object.getString("bio");
                            String district = object.getString("district");
                            String subdistrict = object.getString("subdistrict");
                            String number = object.getString("number");
                            String age = object.getString("age");

                            String urlimage = "https://mychatroom12345.000webhostapp.com/All_Providers/"+url2;

                            model = new Model(id,urlimage,name,profession,division,email,bio,district,subdistrict,number,age);
                            imagelist.add(model);
                            adapter.notifyDataSetChanged();
                        }

                    }

                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(All_Providers.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(All_Providers.this);
        queue.add(request);

    }
    //---------------------------*****--------------------------------//

    //=== Bottom Navigation =========================================//
    private void navigationbotton() {

        bottomNavigationView.setSelectedItemId(R.id.buttonAllProvider);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.Home){
                    Intent intent = new Intent(All_Providers.this, MainActivity.class);
                    startActivity(intent);
                    return false;

                }
                else if(item.getItemId()==R.id.buttonAllProvider){
                    return false;

                }

                else if(item.getItemId()==R.id.buttonProvider){
                    Intent intent = new Intent(All_Providers.this, Become_Provider.class);
                    startActivity(intent);
                    return false;

                }

                return true;
            }
        });
    }
    //---------------------------*****-------------------------------//

    //=== Profile class =========================================//
    @Override
    public void onItemClicked(Model myModel) {
        Intent intent = new Intent(All_Providers.this, Provider.class);

        intent.putExtra("Name", myModel.getName());
        intent.putExtra("Division", myModel.getDivision());
        intent.putExtra("Profession", myModel.getProfession());
        intent.putExtra("myImage", myModel.getImage());
        intent.putExtra("email", myModel.getEmail());
        intent.putExtra("bio", myModel.getBio());
        intent.putExtra("district", myModel.getDistrict());
        intent.putExtra("subdistrict", myModel.getSubdistrict());
        intent.putExtra("number", myModel.getNumber());
        intent.putExtra("age", myModel.getAge());


        startActivity(intent);

    }

    //---------------------------*****-------------------------------//


}