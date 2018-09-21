package com.lumos.lumos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

    public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

        private FirebaseAuth firebaseAuth;

        private TextView textViewUserEmail;
        private Button buttonLogOut;

        private DatabaseReference databaseReference;
        private EditText name, address;
        private Button save;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_profile);

            firebaseAuth = FirebaseAuth.getInstance();

            if(firebaseAuth.getCurrentUser() == null){
                finish();
                startActivity(new Intent(this, SignInActivity.class));
            }

            databaseReference = FirebaseDatabase.getInstance().getReference();

            name = findViewById(R.id.EditTextName);
            address = findViewById(R.id.EditTextAddress);
            save = findViewById(R.id.buttonAddInfo);


            FirebaseUser user = firebaseAuth.getCurrentUser();

            textViewUserEmail = findViewById(R.id.textViewUserEmail);
            textViewUserEmail.setText("Welcome " + user.getEmail());

            buttonLogOut = findViewById(R.id.buttonLogOut);

            buttonLogOut.setOnClickListener(this);
            save.setOnClickListener(this);
        }

        private void saveUserInformation(){
            String nameText = name.getText().toString().trim();
            String addressText = address.getText().toString().trim();

            UserInformationClass userInformation = new UserInformationClass(nameText, addressText);

            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(userInformation);

            Toast.makeText(this, "Information Saved", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onClick(View view) {
            if(view == buttonLogOut){
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, SignInActivity.class));
            }

            if(view == save){
                saveUserInformation();
                startActivity(new Intent(this, AddContacts.class));
            }
        }
    }