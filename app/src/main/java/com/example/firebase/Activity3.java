package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Activity3 extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    form formFrag = new form();
    MapsFragment mapFrag = new MapsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.mapF);
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.mapF) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, mapFrag).commit();
        } else if (id==R.id.formF) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, formFrag).commit();
        } else if (id == R.id.logout) {
            Intent dash1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(dash1);
            Toast.makeText(Activity3.this, "Successfully logged Out", Toast.LENGTH_LONG).show();
        }
        return false;
    }

}