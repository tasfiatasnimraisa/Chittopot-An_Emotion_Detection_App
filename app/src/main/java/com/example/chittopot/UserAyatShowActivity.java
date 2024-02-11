package com.example.chittopot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserAyatShowActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<Ayat> list;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ayat_show);

        recyclerView = findViewById(R.id.userList1);
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("valuepass1");
            Log.d("UserAyatShowActivity", "value: " + str);
        }

        database = FirebaseDatabase.getInstance().getReference("ayat");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the list before adding items
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Ayat ayat = dataSnapshot.getValue(Ayat.class);

                    // Check if ayatCategoryText matches str
                    if (ayat != null && ayat.getAyatCategoryText().equals(str)) {
                        list.add(ayat);
                    }
                }

                // Set up the RecyclerView using an inline adapter
                recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
                    @NonNull
                    @Override
                    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayat2, parent, false);
                        return new MyViewHolder(view);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                        Ayat ayat = list.get(position);

                        // Display only ayatCategoryText
                        holder.ayattype.setText(ayat.getAyatText());
                    }

                    @Override
                    public int getItemCount() {
                        return list.size();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ayattype;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ayattype = itemView.findViewById(R.id.contentayat);
        }
    }
}
