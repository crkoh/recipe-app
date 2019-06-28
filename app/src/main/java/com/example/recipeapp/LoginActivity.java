package com.example.recipeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView register;
    Toolbar mToolBar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_editemail);
        password = findViewById(R.id.et_editpassword);
        login = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();
        register = findViewById(R.id.tv_register);

        //tool bar
//        mToolBar = findViewById(R.id.login_toolbar);
//        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Calories Counter");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, MainPageActivity.class));
        }

    }

    private void login() {
        final String em = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        if (em.isEmpty()){
            email.setError("Email required.");
            email.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            password.setError("Password required.");
            password.requestFocus();
            return;
        }
        if (pass.length() < 6){
            password.setError("Password should be at least 6 characters.");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(LoginActivity.this, MainPageActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}