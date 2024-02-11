package com.example.chittopot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

// Import the correct package for AyatAdapter
import com.example.chittopot.AyatAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowAyatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    AyatAdapter myadapter;
    ArrayList<Ayat> list;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ayat);
        recyclerView = findViewById(R.id.userList);
        database= FirebaseDatabase.getInstance().getReference("ayat");
        Intent intent =getIntent();
        str = intent.getStringExtra("valuepass");
        Log.d("SuggestionActivity", "Value: " + str);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myadapter = new AyatAdapter(this, list);
        recyclerView.setAdapter(myadapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Ayat user = dataSnapshot.getValue(Ayat.class);
                    list.add(user);
                }
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
