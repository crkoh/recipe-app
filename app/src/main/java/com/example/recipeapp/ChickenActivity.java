package com.example.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChickenActivity extends AppCompatActivity {

    Button addchicken, viewchicken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);

        addchicken = findViewById(R.id.btn_addchicken);
        viewchicken = findViewById(R.id.btn_viewchicken);


        addchicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChickenActivity.this,AddChickenActivity.class);
                startActivity(intent);

            }
        });

        viewchicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChickenActivity.this,ChickenListActivity.class);
                startActivity(intent);

            }
        });


    }
}
