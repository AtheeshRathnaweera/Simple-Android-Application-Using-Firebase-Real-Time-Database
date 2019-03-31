package com.atheeshproperty.firebaselearningproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity{

    private EditText emailText, passwordText;
    private Button logIn, signIn, signWithGoogle;

    private FirebaseAuth myAuth;

    private LinearLayout myLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        myAuth = FirebaseAuth.getInstance();

       /* myAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(LogIn.this, MainActivity.class));

                }
            }
        };*/

        myLayout = findViewById(R.id.mainLayout);

        emailText = findViewById(R.id.emailField);
        passwordText = findViewById(R.id.passwordField);

        logIn = findViewById(R.id.logInButton);
        signIn = findViewById(R.id.signInButton);
        signWithGoogle = findViewById(R.id.signInGoogle);

        logInOrSignInButtonOnClick();


    }

    @Override
    protected void onStart() {
        super.onStart();

       // myAuth.addAuthStateListener(myAuthListner);

        FirebaseUser currentUser = myAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser user){

        if(user == null){
            Snackbar.make(myLayout,"Please log in or Create a new account.",Snackbar.LENGTH_LONG).show();
        }else{
            startActivity(new Intent(LogIn.this, MainActivity.class));
        }

    }

    private void logInOrSignInButtonOnClick(){

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startLogIn();

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAnAccount();
            }
        });

    }

    private void startLogIn(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

            myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Snackbar.make(myLayout,"Sign in problem",Snackbar.LENGTH_LONG).show();
                        updateUI(null);

                    }else{

                        FirebaseUser user = myAuth.getCurrentUser();
                        updateUI(user);
                    }
                }
            });

        }else{

            Snackbar.make(myLayout,"Please fill the Email and Password properly.",Snackbar.LENGTH_LONG).show();
        }


    }

    private void createAnAccount(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

           myAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Sign in", "createUserWithEmail:success");
                                FirebaseUser user = myAuth.getCurrentUser();

                                Snackbar.make(myLayout," Sign in successfully",Snackbar.LENGTH_LONG).show();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Sign in", "createUserWithEmail:failure", task.getException());
                                Snackbar.make(myLayout,"Sign in problem. Please try again later.",Snackbar.LENGTH_LONG).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });

        }else{

            Snackbar.make(myLayout,"Please fill the Email and Password properly.",Snackbar.LENGTH_LONG).show();
        }

    }
}
