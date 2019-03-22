package com.atheeshproperty.firebaselearningproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewUsersList extends AppCompatActivity {

    private ListView mUsersList;
    private ArrayList<String> mUserNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_list);

        mUsersList = findViewById(R.id.usersList);

        mUserNames = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mUserNames);
        mUsersList.setAdapter(arrayAdapter);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = mRef.child("users");

        uidRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                HashMap<String, UserProfileData> value = dataSnapshot.getValue(HashMap.class);

                UserProfileData yt = value.get(value.keySet());
                mUserNames.add(yt.getFirstName());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
