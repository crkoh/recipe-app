package com.example.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DessertActivity extends AppCompatActivity {

    Button adddessert, viewdessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);

        adddessert = findViewById(R.id.btn_adddessert);
        viewdessert = findViewById(R.id.btn_viewdessert);


        adddessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DessertActivity.this,AddDessertActivity.class);
                startActivity(intent);

            }
        });




    }
}
