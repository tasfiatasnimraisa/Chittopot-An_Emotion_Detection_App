package com.example.chittopot;

import android.content.Intent;
import android.net.Uri;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserVideoShowActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    FirebaseRecyclerAdapter<filemodel, Viewholder> firebaseRecyclerAdapter;
   String str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String str;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_video_show);
        Intent intent = getIntent();
        if (intent != null) {
            str2 = intent.getStringExtra("videopass");
            Log.d("UserVideoShowActivity", "Input Text: " + str2);
        }

        // Initialize FirebaseDatabase
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("myvideos");

        recyclerView = findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Update the FirebaseRecyclerOptions to include the category filter
        FirebaseRecyclerOptions<filemodel> options =
                new FirebaseRecyclerOptions.Builder<filemodel>()
                        .setQuery(databaseReference.orderByChild("catagory").equalTo(str2), filemodel.class)
                        .build();

        firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<filemodel, Viewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull filemodel model) {
                        holder.setVideo(model.getTitle(), model.getVurl(), model.getCatagory());
                    }

                    @NonNull
                    @Override
                    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
                        return new Viewholder(view);
                    }
                };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (firebaseRecyclerAdapter != null) {
            firebaseRecyclerAdapter.stopListening();
        }
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        PlayerView playerView;
        TextView videoTitle;
        SimpleExoPlayer player;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.exoplayer_item);
            videoTitle = itemView.findViewById(R.id.videotitle);

            player = new SimpleExoPlayer.Builder(itemView.getContext()).build();
            playerView.setPlayer(player);
        }

        public void setVideo(String title, String vurl, String category) {
            videoTitle.setText(title);

            // Create a MediaItem using the video URL
            MediaItem mediaItem = MediaItem.fromUri(Uri.parse(vurl));

            // Set the MediaItem to the player and prepare (don't play automatically)
            player.setMediaItem(mediaItem);
            player.prepare();
        }

        public void releasePlayer() {
            if (player != null) {
                player.release();
                player = null;
            }
        }
    }
}