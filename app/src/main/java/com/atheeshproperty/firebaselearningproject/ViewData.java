package com.atheeshproperty.firebaselearningproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewData extends AppCompatActivity {

    private TextView firstNameText, lastNameText, ageText, phoneText;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);

        firstNameText = findViewById(R.id.uFirstName);
        lastNameText = findViewById(R.id.uLastName);
        ageText = findViewById(R.id.uAge);
        phoneText = findViewById(R.id.uPhoneNumber);

        myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = myRef.child("users").child("-La_V8Qs95BLOastYRyA");//Set user id as the child

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Log.e("Data ", "Found");
                    Map<String, String> map = (Map) dataSnapshot.getValue();

                    String firstName = map.get("firstName");
                    String lastName = map.get("lname");
                    String age = map.get("uage");
                    String uPhone = map.get("uphone");

                    Log.e("First Name", " " + firstName);
                    Log.e("Last Name", " " + lastName);
                    Log.e("Age", " " + age);
                    Log.e("Phone number"," "+ uPhone);

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    ageText.setText(age);
                    phoneText.setText(uPhone);


                   /* for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        String usert = ds.getValue(String.class);
                        Log.e("Rec FName "," :"+usert);
                    }*/

                } else {
                    Log.e("Data ", "Not Found");
                }

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e("Data", "Post getting failed.");
                // ...
            }
        };
        uidRef.addValueEventListener(postListener);


    }
}
