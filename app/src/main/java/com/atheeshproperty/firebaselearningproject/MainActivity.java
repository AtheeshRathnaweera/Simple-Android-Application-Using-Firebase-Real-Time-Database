package com.atheeshproperty.firebaselearningproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button save;
    private EditText inputText;
    private FirebaseApp mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        save = findViewById(R.id.saveData);
        inputText = findViewById(R.id.inputText);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = inputText.getText().toString();

                DatabaseReference myRef = database.getReference("message");
                myRef.push().setValue(value);

                DatabaseReference myRefTwo = database.getReference("Name");
                myRefTwo.push().setValue(value);
            }
        });
    }
}
