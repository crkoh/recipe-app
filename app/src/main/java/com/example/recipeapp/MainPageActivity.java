package com.example.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainPageActivity extends AppCompatActivity {
    Button chicken,dessert, vege, pasta, profile, logout;
    Toolbar mToolBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        firebaseAuth = FirebaseAuth.getInstance();

        chicken = findViewById(R.id.btnChicken);
        dessert = findViewById(R.id.btnDessert);
//        vege = findViewById(R.id.btnVege);
//        pasta = findViewById(R.id.btnPasta);
        profile = findViewById(R.id.btnProfile);
        logout = findViewById(R.id.btnLogout);

//        mToolBar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Main Page");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this,ChickenActivity.class);
                startActivity(intent);
            }
        });

        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this,DessertActivity.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}
