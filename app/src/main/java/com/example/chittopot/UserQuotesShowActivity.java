package com.example.chittopot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class UserQuotesShowActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    List<Jokes> list;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_quotes_show);

        recyclerView = findViewById(R.id.userList2);
        database = FirebaseDatabase.getInstance().getReference("jokes");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        Log.d("Input Data", "hhhhhhhhhhhhhhhhhh") ;
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("valuepass3");
            Log.d("UserQuotesShowActivity", "Input Text: " + str);
        }

        recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemjokes2, parent, false);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Jokes item = list.get(position);
                holder.contentTextView.setText(item.getJokesText());
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Jokes joke = dataSnapshot.getValue(Jokes.class);

                    // Check if jokescategoryText matches str
                    if (joke != null && joke.getJokescatagoryText().equals(str)) {
                        list.add(joke);
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView contentTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contentTextView = itemView.findViewById(R.id.contentjokes);
        }
    }
}
