package com.lumos.lumos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
        private FirebaseAuth firebaseAuth;
        private Button buttonSignUp;
        private EditText email;
        private EditText password;
        private TextView signIn;
        private ProgressDialog progressDialog;
        private String emailText;
        private String passwordText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            firebaseAuth = FirebaseAuth.getInstance();
            buttonSignUp = findViewById(R.id.buttonSignUp);
            email = findViewById(R.id.editTextEmail);
            password = findViewById(R.id.editTextPassword);
            signIn = findViewById(R.id.textViewSignIn);
            progressDialog = new ProgressDialog(this);

            //check if user is already logged in
            if(firebaseAuth.getCurrentUser() != null){
                //start profile activity
                finish();
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
            }

            buttonSignUp.setOnClickListener(this);
            signIn.setOnClickListener(this);

        }

        /*Firebase ref = new Firebase(FIREBASE_URL);
        SimpleLogin authClient = new SimpleLogin(ref);
    authClient.createUser(email, password, new SimpleLoginAuthenticatedHandler() {
            @Override
            public void authenticated(com.firebase.simplelogin.enums.Error error, User user) {
                if(error != null) {
                    Log.e(TAG, "Error attempting to create new Firebase User: " + error);
                }
                else {
                    Log.d(TAG, "User successfully registered for Firebase");
                    application.setLoggedIntoChat(true);
                }
            }
        });*/

        private void registerUser(){

            emailText = email.getText().toString().trim();
            passwordText = password.getText().toString().trim();

            if(TextUtils.isEmpty(emailText)){
                //email is empty
                Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show();

                //return will stop the function from executing further
                return;
            }

            if(TextUtils.isEmpty(passwordText)){
                //password is empty
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();

                //return will stop the function from executing further
                return;
            }

            //If email and password are valid, first show a progress dialog
            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(task.isSuccessful()){
                                //user is successfully registered and logged in
                                //check if user is already logged in
                                finish();
                                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                            }
                            else{
                                Toast.makeText(SignUpActivity.this, "Registration Failed. Try Again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

        @Override
        public void onClick(View view) {
            if(view == buttonSignUp){
                registerUser();
            }

            if(view == signIn){
                //will open login activity
                startActivity(new Intent(this, SignInActivity.class));
            }
        }

    }