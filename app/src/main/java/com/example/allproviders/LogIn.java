package com.example.allproviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    private EditText name,singinpassword;
    private Button login;
    private FirebaseAuth mAuth;
    private TextView singuptextview;

    public static String profile_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getWindow().setStatusBarColor(ContextCompat.getColor(LogIn.this,R.color.white));

        name = findViewById(R.id.Name);
        singinpassword= findViewById(R.id.Password);
        login= findViewById(R.id.LogIn);
        mAuth = FirebaseAuth.getInstance();
        singuptextview = findViewById(R.id.TextViewSingUpId);

        singuptextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this,SingUp.class);
                startActivity(intent);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });
    }

    private void userlogin() {
        String email = name.getText().toString().trim();
        String password = singinpassword.getText().toString().trim();

        profile_email = email;

        //checking the validity of the email
        if(email.isEmpty())
        {
            name.setError("Enter an email address");
            name.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            name.setError("Enter a valid email address");
            name.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            singinpassword.setError("Enter a password");
            singinpassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(),"Log in Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}