package com.example.chittopot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class SuggestionActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
String str,a1;
    ImageView imageMenu;
    CardView c1,c2,c3,c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        getSupportActionBar().hide();
        c1=(CardView) findViewById(R.id.card1);
        c2=(CardView) findViewById(R.id.card2);
        c3=(CardView) findViewById(R.id.card3);
        c4=(CardView) findViewById(R.id.card4);
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("valuepass");
            a1=intent.getStringExtra("abc");
            Log.d("SuggestionActivity", "Received value: " + str);
        }

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserVideoShowActivity.class);
                intent.putExtra("videopass",str);
                startActivity(intent);
            }
        });

        // Set OnClickListener for c2
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserQuotesShowActivity.class);
                intent.putExtra("valuepass3",str);
                startActivity(intent);
            }
        });

        // Set OnClickListener for c3
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserAyatShowActivity.class);
                intent.putExtra("valuepass1",str);
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
                SuggestionActivity.this,
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

                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                       startActivity(intent);

                        drawerLayout.closeDrawers();
                        break;


                    case R.id.mDashboard:
                        Intent historyIntent = new Intent(getApplicationContext(), PreviousHistoryActivity.class);
                        historyIntent.putExtra("abc", a1);
                        startActivity(historyIntent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.Policy:

                        Intent intent4=new Intent(getApplicationContext(),login.class);
                      startActivity(intent4);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mRate:

                        if ("Happy".equals(str))
                        {
                            Intent intent2 = new Intent(getApplicationContext(),happyyt.class);
                            intent2.putExtra("kkk",str);
                            startActivity(intent2);
                            drawerLayout.closeDrawers();
                            break;
                        }
                        else if("Sad".equals(str))
                        {
                            Intent intent2 = new Intent(getApplicationContext(),sadyt.class);
                            intent2.putExtra("kkk",str);
                            startActivity(intent2);
                            drawerLayout.closeDrawers();
                            break;

                        }
                      else if("Love".equals(str))
                        {
                            Intent historyIntent4 = new Intent(getApplicationContext(), loveyt.class);
                            historyIntent4.putExtra("kkk",str);
                            startActivity(historyIntent4);
                            drawerLayout.closeDrawers();
                            break;

                        }
                       else
                        {
                            Intent historyIntent5 = new Intent(getApplicationContext(), angryyt.class);
                            historyIntent5.putExtra("kkk",str);
                            startActivity(historyIntent5);
                            drawerLayout.closeDrawers();
                            break;


                        }


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