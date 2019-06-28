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

import java.util.HashMap;
import java.util.Map;

public class AddDessertActivity extends AppCompatActivity {

    private EditText editTextdname, editTextdingredient;
    Button dessertbtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dessert);
        editTextdname = findViewById(R.id.et_dname);
        editTextdingredient = findViewById(R.id.et_dingredient);

//        mToolBar = findViewById(R.id.adddessert_toolbar);
//        setSupportActionBar(mToolBar);
//        getSupportActionBar().setTitle("Add Food");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dessertbtn = findViewById(R.id.btndessert);

        mAuth = FirebaseAuth.getInstance();

        dessertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDessert();
            }
        });

    }

    private void addDessert(){
        final String dname = editTextdname.getText().toString().trim();
        final String dingredient = editTextdingredient.getText().toString().trim();

        if (dname.isEmpty()){
            editTextdname.setError("Name required.");
            editTextdname.requestFocus();
            return;
        }

        if (dingredient.isEmpty()){
            editTextdingredient.setError("Ingredient required.");
            editTextdingredient.requestFocus();
            return;
        }
        else{

            int n =10;
            String did =RandomStringG.getAlphaNumericString(n);
            Dessert chicken = new Dessert(
                    dname,
                    dingredient
            );

            FirebaseDatabase.getInstance().getReference("Dessert")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(did).setValue(chicken).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddDessertActivity.this, "Recipe Added!", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }
            });

        }


    }

}

class RandomStringG {

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


