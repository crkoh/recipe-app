package com.example.recipeapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddChickenActivity extends AppCompatActivity {

    private EditText editTextcname, editTextcingredient;
    Button chickenbtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chicken);
        editTextcname = findViewById(R.id.et_cname);
        editTextcingredient = findViewById(R.id.et_cingredient);

//        mToolBar = findViewById(R.id.addfood_toolbar);
//        setSupportActionBar(mToolBar);
//        getSupportActionBar().setTitle("Add Food");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chickenbtn = findViewById(R.id.btnchicken);

        mAuth = FirebaseAuth.getInstance();

        chickenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChicken();
            }
        });

    }

    private void addChicken(){
        final String cname = editTextcname.getText().toString().trim();
        final String cingredient = editTextcingredient.getText().toString().trim();

        if (cname.isEmpty()){
            editTextcname.setError("Name required.");
            editTextcname.requestFocus();
            return;
        }

        if (cingredient.isEmpty()){
            editTextcingredient.setError("Ingredient required.");
            editTextcingredient.requestFocus();
            return;
        }
        else{

            int n =10;
            String cid =RandomString.getAlphaNumericString(n);
            Chicken chicken = new Chicken(
                    cname,
                    cingredient
            );

            FirebaseDatabase.getInstance().getReference("Chicken")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(cid).setValue(chicken).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddChickenActivity.this, "Recipe Added!", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }
            });

        }


    }

}

class RandomString {

    // function to generate a random string of length n
    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
