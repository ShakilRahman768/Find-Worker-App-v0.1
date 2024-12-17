package com.example.allproviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    TextView textView4,loginmail;
    ImageView imageViewCategory;
    android.widget.GridView gridView;
    ImageSlider imageSlider;
    BottomNavigationView bottomNavigationView;

    CircleImageView profileimage;
    int[] image = {R.drawable.electrician, R.drawable.driver, R.drawable.tutor, R.drawable.painter,
            R.drawable.sweeper, R.drawable.pc_repairer, R.drawable.plumber, R.drawable.chef,
            R.drawable.cleaner,R.drawable.gardener, R.drawable.laborer, R.drawable.photographer};
    String[] name = {"Electrician", "Driver", "Tutor", "Painter", "Sweeper", "PC Repairer",
            "Plumber", "Chef", "Cleaner", "Gardener", "Laborer", "Photographer"};

    ArrayList<HashMap<String, String>> arrayList = new ArrayList();
    HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //===Connecting with item=========================================//
        imageSlider = findViewById(R.id.image_slider);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        gridView = findViewById(R.id.gridView);
        profileimage = findViewById(R.id.profile_image);
        loginmail = findViewById(R.id.loginmail);

        //---------------------------*****-------------------------------//


        MyAdapter adapter = new MyAdapter();
        gridView.setAdapter(adapter);

        createSlider();

        navigation();

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
            }
        });

    }



    //==== Bottom Navigation Method =========================================//
    private void navigation() {

        bottomNavigationView.setSelectedItemId(R.id.Home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()) {
                    case R.id.Home:
                        return false;

                    case R.id.buttonAllProvider:
                        Intent intent = new Intent(MainActivity.this, All_Providers.class);
                        intent.putExtra("Name", "ALL");
                        startActivity(intent);

                        overridePendingTransition(0, 0);

                        return false;

                    case R.id.buttonProvider:
                        startActivity(new Intent(getApplicationContext(), Become_Provider.class));
                        overridePendingTransition(0, 0);
                        return false;
                }

                return false;
            }
        });

    }
    //---------------------------*****-------------------------------//


    //========================***  Image Slider Method  ***=============================//
    private void createSlider(){

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.icon,  ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.about_us,  ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.contact_us,  ScaleTypes.FIT));

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);

    }
    //------------------------------------------------------------------------------------//

    //========================***  Adapter Class for GridView ***=============================//
    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View myView = layoutInflater.inflate(R.layout.gridview_item, gridView, false);

            imageViewCategory = myView.findViewById(R.id.textView3);
            textView4 = myView.findViewById(R.id.textView4);
            CardView layout = myView.findViewById(R.id.gridViewItem);

            imageViewCategory.setImageResource(image[position]);
            textView4.setText(name[position]);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, All_Providers.class);
                    intent.putExtra("Name", name[position]);
                    startActivity(intent);
                }
            });

            return myView;
        }
    }

    //--------------------------------------------------------------//

    //===* Back Press Method *======================================//
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit!")
                .setMessage("Do you want to exit?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAffinity();
                    }
                })
                .show();
    }
    //--------------------------------------------------------------//
}