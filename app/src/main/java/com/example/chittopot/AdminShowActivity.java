package com.example.chittopot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class AdminShowActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    String str;
    ImageView imageMenu;
    CardView c1,c2,c3,c4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show);
        getSupportActionBar().hide();
        c1=(CardView) findViewById(R.id.card1);
        c2=(CardView) findViewById(R.id.card2);
        c3=(CardView) findViewById(R.id.card3);
        c4=(CardView) findViewById(R.id.card4);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminShowActivity.this, dashboard.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for c2
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminShowActivity.this, ShowJokesActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for c3
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowAyatActivity.class);

                startActivity(intent);
            }
        });

        // Set OnClickListener for c4
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Show_image.class);

                startActivity(intent);
            }
        });




        // Navagation Drawar------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu2);

        toggle = new ActionBarDrawerToggle(
                AdminShowActivity.this,
                drawerLayout,
                R.string.navigation_drawer_open, // Use a string resource for the open drawer description
                R.string.navigation_drawer_close // Use a string resource for the close drawer description
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mHome:

                        Intent intent=new Intent(getApplicationContext(),AdminActivity.class);
                        startActivity(intent);

                        drawerLayout.closeDrawers();
                        break;



                    case R.id.mRate:

                        Intent intent4=new Intent(getApplicationContext(),ShowJokesActivity.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.Policy:
                         Intent intent5=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawers();
                        break;

                }

                return false;
            }
        });
        //------
        imageMenu = findViewById(R.id.imageMenu2);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }
}