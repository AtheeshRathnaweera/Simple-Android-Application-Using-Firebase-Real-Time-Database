package com.atheeshproperty.firebaselearningproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button save, view, viewAllUsers;
    private EditText first, last, ageIn, phoneIn;
    // private FirebaseApp mRef;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.saveData);
        view = findViewById(R.id.viewData);
        viewAllUsers = findViewById(R.id.viewAllUsers);

        first = findViewById(R.id.firstName);
        last = findViewById(R.id.lastName);
        ageIn = findViewById(R.id.age);
        phoneIn = findViewById(R.id.phone);

        SaveButton();
        viewButton();
        viewAllUsersButton();

    }

    private void viewAllUsersButton() {

        viewAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inB = new Intent(MainActivity.this, ViewUsersList.class);
                startActivity(inB);
            }
        });
    }

    private void viewButton() {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("View button", "View button clicked.");

                Intent in = new Intent(MainActivity.this, ViewData.class);
                startActivity(in);
            }
        });
    }

    private void SaveButton() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkDataStatus()) {

                    Log.e("Save method", "Started");
                    String firstName = first.getText().toString();
                    String lastName = last.getText().toString();
                    String age = ageIn.getText().toString();
                    String phoneNu = phoneIn.getText().toString();

                    UserProfileData user = new UserProfileData(firstName, lastName, age, phoneNu);

                    myRef = FirebaseDatabase.getInstance().getReference();

                    myRef.child("users").push().setValue(user);
                    clearTheFields();
                } else {
                    //Nothing will happen
                    Log.e("Connection", "Connection not found");
                }


            }
        });

    }


    public void clearTheFields() {
        first.setText("");
        last.setText("");
        ageIn.setText("");
        phoneIn.setText("");
    }


    public boolean checkDataStatus() {
        final ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi.isConnectedOrConnecting()) {
            Log.e("Network", "Wifi");
            return true;
        } else if (mobile.isConnectedOrConnecting()) {
            Log.e("Network", "Mobile data");
            return true;
        } else {
            Toast.makeText(this, "Please check your network connection! ", Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
