package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardActivity extends AppCompatActivity {

    //Variables
    EditText nameET;
    AppCompatButton submitData;
    Spinner genreSpinner;

    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Hook all XML components
        nameET = findViewById(R.id.nameET);
        submitData = findViewById(R.id.addArtist);
        genreSpinner = findViewById(R.id.genreSpinner);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artist");

        //Save data to database
        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();
                nameET.setText("");
            }
        });

        //Sign Out User using Firebase Authentication
        AppCompatButton signOut = findViewById(R.id.signOutBtn);
        signOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            finish();
        });

    }

    private void addArtist(){

        String name = nameET.getText().toString().trim();
        String genre = genreSpinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
            String id = databaseArtists.push().getKey();

            Artist artist = new Artist(id, name, genre);
            assert id != null;
            databaseArtists.child(id).setValue(artist);

            Toast.makeText(this, "Artist Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
        }
    }
}