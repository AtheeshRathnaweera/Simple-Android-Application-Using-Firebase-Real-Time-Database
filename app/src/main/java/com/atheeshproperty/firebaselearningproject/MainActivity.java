package com.atheeshproperty.firebaselearningproject;

import android.content.Context;
import android.net.ConnectivityManager;
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
        viewButton();

    }

    private void viewButton(){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDataInDatabase();
            }
        });
    }

    private void SaveButton(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkDataStatus()){
                    int bound = 10000;

                    Random rand = new Random();
                    int randomNumber = rand.nextInt(bound);
                    String random = String.valueOf(randomNumber);

                    String firstName = first.getText().toString();
                    String lastName = last.getText().toString();
                    String age = ageIn.getText().toString();
                    String phoneNu = phoneIn.getText().toString();

                    UserProfileData user = new UserProfileData(firstName, lastName,age,phoneNu);

                    myRef.child("users").push().setValue(user);

                    clearTheFields();
                }else{
                    //Nothing will happen
                }



            }
        });

    }

    public void clearTheFields(){
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
        if (wifi.isConnectedOrConnecting ()) {
            Log.e("Network","Wifi");
            return true;
        } else if (mobile.isConnectedOrConnecting ()) {
            Log.e("Network","Mobile data");
            return true;
        } else {
            Toast.makeText(this, "Please check your network connection! ", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void viewDataInDatabase(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists()){
                    Log.e("Date exists","True");
                    UserProfileData post = dataSnapshot.getValue(UserProfileData.class);
                    Log.e("Date exists","Data"+post.toString());
            }
                Log.e("Date exists","False");
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e("Error occured",databaseError.toException().toString());
                // ...
            }
        };
        myRef.addValueEventListener(postListener);

    }


}
