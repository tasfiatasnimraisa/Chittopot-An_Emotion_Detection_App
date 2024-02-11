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

public class JokesActivity extends AppCompatActivity {
    EditText jokes;
    Button btn4;
    DatabaseReference databaseReference;

    String[] item = {"Happy","Sad","Angry","Love"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;
    public String jokescatagoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        jokes = findViewById(R.id.jokes);

        btn4 = findViewById(R.id.button4);
        databaseReference = FirebaseDatabase.getInstance().getReference("jokes");
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(JokesActivity.this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                jokescatagoryText = adapterView.getItemAtPosition(i).toString();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values from the EditText fields





                String jokesText = "\"" + jokes.getText().toString().trim() + "\"";
                //String jokescatagoryText = jokescatagory.getText().toString().trim();

                // Check if the fields are not empty before saving to Firebase
                if (!jokesText.isEmpty() && !jokescatagoryText.isEmpty()) {
                    // Create a unique key for each entry in the "jokes" branch
                    String jokesId = databaseReference.push().getKey();

                    // Create a Jokes object to hold the information
                    Jokes jokesObj = new Jokes(jokesId, jokesText, jokescatagoryText);

                    // Save the Jokes object to Firebase under the "jokes" branch
                    databaseReference.child(jokesId).setValue(jokesObj);

                    // Inform the user that the data has been saved
                    Toast.makeText(JokesActivity.this, "Jokes added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Inform the user that fields cannot be empty
                    Toast.makeText(JokesActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
