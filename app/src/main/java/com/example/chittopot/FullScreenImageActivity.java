package com.example.chittopot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreenImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        ImageView fullscreenImageView = findViewById(R.id.fullscreenImageView);

        // Get the image URL from the intent
        String imageURL = getIntent().getStringExtra("imageURL");

        // Load the image into ImageView using a library like Picasso or Glide
        // Example using Glide:
        Glide.with(this).load(imageURL).into(fullscreenImageView);
    }
}
