package com.example.chittopot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ShowJokesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    JokesAdapter jokesAdapter;
    List<Jokes> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jokes);

        recyclerView = findViewById(R.id.userList2);
        database = FirebaseDatabase.getInstance().getReference("jokes");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        jokesAdapter = new JokesAdapter(this, list);
        recyclerView.setAdapter(jokesAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Jokes joke = dataSnapshot.getValue(Jokes.class);
                    list.add(joke);
                }
                jokesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}
