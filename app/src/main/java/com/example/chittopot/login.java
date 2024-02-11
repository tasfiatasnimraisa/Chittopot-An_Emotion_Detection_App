package com.example.chittopot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chittopot.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.email.getText().toString().trim();
                String password=binding.password.getText().toString().trim();
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.cancel();
                                String userEmail = authResult.getUser().getEmail();

                                if ("rubaiyaraktinraha@gmail.com".equals(userEmail)) {
                                    Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this,AdminActivity.class));
                                } else {
                                    Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                    intent.putExtra("usermail",userEmail);
                                    startActivity(intent);
                                }
                            }

                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();
                            }
                        });

            }
        });

        binding.fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.email.getText().toString();
                progressDialog.setTitle("Sending Mail");
                progressDialog.show();
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.cancel();
                                Toast.makeText(login.this,"Email sent",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}