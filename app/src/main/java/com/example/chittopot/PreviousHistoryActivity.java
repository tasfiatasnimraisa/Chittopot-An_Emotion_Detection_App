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

public class PreviousHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    List<HistoryEntry> list;
    String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_history);

        recyclerView = findViewById(R.id.userList2);




        Intent intent = getIntent();
        if (intent != null) {
            userEmail = intent.getStringExtra("abc");
            Log.d("PreviousHistoryActivity", "User Email: " + userEmail);
        }

        database = FirebaseDatabase.getInstance().getReference("history").child(userEmail);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        recyclerView.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emotion, parent, false);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                HistoryEntry item = list.get(position);
                holder.emotionTextView.setText(item.getEmotion());
                holder.emotionTextView2.setText(item.getFormattedTime());
                holder.emotionTextView3.setText(item.getFormattedDate());
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
                    HistoryEntry historyEntry = dataSnapshot.getValue(HistoryEntry.class);
                    if (historyEntry != null) {
                        list.add(historyEntry);
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
        TextView emotionTextView,emotionTextView2,emotionTextView3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emotionTextView = itemView.findViewById(R.id.emo);  // Updated this line
            emotionTextView2 = itemView.findViewById(R.id.emo2);
            emotionTextView3 = itemView.findViewById(R.id.emo3);
        }
    }
}
