package com.atheeshproperty.firebaselearningproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button save,view;
    private EditText first, last, ageIn, phoneIn;
    private FirebaseApp mRef;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference();

        save = findViewById(R.id.saveData);
        view = findViewById(R.id.viewData);

        first= findViewById(R.id.firstName);
        last = findViewById(R.id.lastName);
        ageIn = findViewById(R.id.age);
        phoneIn = findViewById(R.id.phone);

        SaveButton();

    }

    private void SaveButton(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int bound = 10000;

                Random rand = new Random();
                int randomNumber = rand.nextInt(bound);
                String random = String.valueOf(randomNumber);

                String firstName = first.getText().toString();
                String lastName = last.getText().toString();
                String age = ageIn.getText().toString();
                String phoneNu = phoneIn.getText().toString();

                UserProfileData user = new UserProfileData(firstName, lastName,age,phoneNu);

                myRef.child("users").child(random).setValue(user);

                clearTheFields();

            }
        });

    }

    public void clearTheFields(){
        first.setText("");
        last.setText("");
        ageIn.setText("");
        phoneIn.setText("");
    }
}
