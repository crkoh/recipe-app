package com.example.recipeapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Toolbar mToolBar;
    FirebaseDatabase db;
    DatabaseReference ref2;
    EditText Name,Email;
    EditText Pass;
    Button btneditprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

//        mToolBar = findViewById(R.id.editprofile_toolbar);
//        setSupportActionBar(mToolBar);
//        getSupportActionBar().setTitle("Edit Profile");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Name = (EditText) findViewById(R.id.et_editname);
        Pass = (EditText) findViewById(R.id.et_editpassword);

        Email = (EditText) findViewById(R.id.et_editemail);

        btneditprofile = (Button) findViewById(R.id.btn_editprofile);

        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile();
            }
        });

    }

    private void EditProfile() {
        FirebaseDatabase  database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        final String name = Name.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String password = Pass.getText().toString().trim();

        if (name.isEmpty()){
            Name.setError("Name required.");
            Name.requestFocus();
            return;
        }

        if (email.isEmpty()){
            Email.setError("Email required.");
            Email.requestFocus();
            return;
        }


        if (password.isEmpty()){
            Pass.setError("Password required.");
            Pass.requestFocus();
            return;
        }

        if (password.length() < 6){
            Pass.setError("Password should be at least 6 characters.");
            Pass.requestFocus();
            return;
        }




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential(FirebaseAuth.getInstance().getCurrentUser().getEmail(),Pass.getText().toString() ); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        user.updatePassword(Pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(EditProfileActivity.this, "Edit Successful!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        user.updateEmail(Email.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditProfileActivity.this, "Edit Successful!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
        mDatabaseRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(Name.getText().toString());
        mDatabaseRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(Email.getText().toString());
        mDatabaseRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("password").setValue(Pass.getText().toString());
    }


    @Override
    protected void onStart() {
        super.onStart();

        db = FirebaseDatabase.getInstance();
        ref2 = db.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                Name.setText(user.getName());
                Email.setText(user.getEmail());
                Pass.setText(user.getPassword());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
