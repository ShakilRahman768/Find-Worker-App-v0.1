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

public class SingUp extends AppCompatActivity {

    private EditText singupname,singuppassword;
    private Button singupbutton;
    private FirebaseAuth mAuth;
    private TextView logintext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        getWindow().setStatusBarColor(ContextCompat.getColor(SingUp.this,R.color.white));

        singupname = findViewById(R.id.SingUpName);
        singuppassword= findViewById(R.id.SingUpPassword);
        singupbutton= findViewById(R.id.SingUp);
        mAuth = FirebaseAuth.getInstance();
        logintext = findViewById(R.id.TextViewLogId);


        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingUp.this, LogIn.class);
                startActivity(intent);
            }
        });

        singupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRegister();
            }
        });
    }

    private void UserRegister() {
        String email = singupname.getText().toString().trim();
        String password = singuppassword.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            singupname.setError("Enter an email address");
            singupname.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            singupname.setError("Enter a valid email address");
            singupname.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            singuppassword.setError("Enter a password");
            singuppassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(getApplicationContext(),"Sucessfull",Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(),"Not Sucessfull",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}