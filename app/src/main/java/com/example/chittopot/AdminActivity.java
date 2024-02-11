package com.example.chittopot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {
    CardView c1,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().hide();
        c1=(CardView) findViewById(R.id.c1);
        c2=(CardView) findViewById(R.id.c2);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for c1
                // For example, start a new activity
                Intent intent = new Intent(AdminActivity.this, AdminAddActivity.class);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for c1
                // For example, start a new activity
                Intent intent = new Intent(AdminActivity.this, AdminShowActivity.class);
                startActivity(intent);
            }
        });


    }
}