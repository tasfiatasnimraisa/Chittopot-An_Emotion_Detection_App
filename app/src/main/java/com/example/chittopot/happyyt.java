package com.example.chittopot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class happyyt extends AppCompatActivity {
    Button btn;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happyyt);
        btn = findViewById(R.id.ytb1);
        Intent intent = getIntent();
        if (intent != null) {
            userEmail = intent.getStringExtra("kkk");
            Log.d("happyyt", "Testing: " + userEmail);
        }

       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent3 = new Intent(getApplicationContext(), web.class);
                historyIntent3.putExtra("kkk",userEmail);
                startActivity(historyIntent3);

            }
        });
    }
}