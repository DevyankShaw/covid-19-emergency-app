package com.example.covid_19_emergency_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button signout_btn;
    BottomNavigationView navView;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int var = getIntent().getIntExtra("choice", 1);

        final String mmobile = getIntent().getStringExtra("mmobile");
        navView=findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Emergency_contacts:
                        Toast.makeText(MainActivity.this, "Emergency contacts", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.help:
                        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Corona:
                        Toast.makeText(MainActivity.this, "updates", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Notifications:
                        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        signout_btn = findViewById(R.id.signOut_btn);
        signout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Signing out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();

                finish();
                Intent intent = new Intent(MainActivity.this, login.class);

                startActivity(intent);
            }
        });

        Log.e("TAG", "betA");


        if (var != 3) {
            if (var == 2) {
                reff = FirebaseDatabase.getInstance().getReference().child("Aid_Helper").child(mmobile);
            } else {
                reff = FirebaseDatabase.getInstance().getReference().child("Nomodular").child(mmobile);
            }

            reff.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Toast.makeText(MainActivity.this, "Welcome !!", Toast.LENGTH_SHORT).show();
                        Log.e("bro", "code");
                        //      String temp_name = dataSnapshot.child("full_name").getValue().toString();
                        //     String temp_college = dataSnapshot.child("college").getValue().toString();
                        //    String temp_email = dataSnapshot.child("email").getValue().toString();
                        //   String temp_mobile = dataSnapshot.child("mobile_no").getValue().toString();

                        // show_name.setText(temp_name);
                        //  show_college.setText(temp_college);
                        //  show_email.setText(temp_email);
                        //  show_mobile.setText(temp_mobile);
                    } else {
                        Log.e("bro", "dude");
                        finish();
                        Toast.makeText(MainActivity.this, "You should first sign up and then come", Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();

                        Intent goto_signup = new Intent(MainActivity.this, SignUp.class);
                        startActivity(goto_signup);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });


        }
    }
}
