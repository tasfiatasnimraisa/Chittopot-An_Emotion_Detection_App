package com.example.chittopot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAyatActivity extends AppCompatActivity {
    EditText ayat, ayatcatagory;
    Button btn3;
    DatabaseReference databaseReference;
    String[] item = {"Happy","Sad","Angry","Love"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;
    public String ayatCategoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ayat);

        ayat = findViewById(R.id.ayat);
        //ayatcatagory = findViewById(R.id.ayatcatagory);
        btn3 = findViewById(R.id.button3);
        databaseReference = FirebaseDatabase.getInstance().getReference("ayat");
       // autoCompleteTextView = findViewById(R.id.auto_complete_txt2);
        autoCompleteTextView=findViewById(R.id.auto_complete_txt2);
        adapterItems = new ArrayAdapter<String>(AddAyatActivity.this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ayatCategoryText = adapterView.getItemAtPosition(i).toString();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values from the EditText fields
                String ayatText = ayat.getText().toString().trim();
               // String ayatCategoryText = ayatcatagory.getText().toString().trim();

                // Check if the fields are not empty before saving to Firebase
                if (!ayatText.isEmpty() && !ayatCategoryText.isEmpty()) {
                    // Create a unique key for each entry in the "ayat" branch
                    String ayatId = databaseReference.push().getKey();

                    // Create an Ayat object to hold the information
                    Ayat ayatObj = new Ayat(ayatId, ayatText, ayatCategoryText);

                    // Save the Ayat object to Firebase under the "ayat" branch
                    databaseReference.child(ayatId).setValue(ayatObj);

                    // Inform the user that the data has been saved
                    Toast.makeText(AddAyatActivity.this, "Ayat added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Inform the user that fields cannot be empty
                    Toast.makeText(AddAyatActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
