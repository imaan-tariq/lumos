package com.lumos.lumos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.telephony.PhoneNumberFormattingTextWatcher;
>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b
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
<<<<<<< HEAD
        private EditText name, address;
=======
        private EditText name, phoneNum;
>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b
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
<<<<<<< HEAD
            address = findViewById(R.id.EditTextAddress);
=======
            phoneNum = findViewById(R.id.EditTextPhoneNum);
            phoneNum.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b
            save = findViewById(R.id.buttonAddInfo);


            FirebaseUser user = firebaseAuth.getCurrentUser();

<<<<<<< HEAD
            textViewUserEmail = findViewById(R.id.textViewUserEmail);
            textViewUserEmail.setText("Welcome " + user.getEmail());
=======
            textViewUserEmail = findViewById(R.id.textViewLoggedIn);
           // textViewUserEmail.setText("Hello " + user.getEmail());
>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b

            buttonLogOut = findViewById(R.id.buttonLogOut);

            buttonLogOut.setOnClickListener(this);
            save.setOnClickListener(this);
        }

        private void saveUserInformation(){
            String nameText = name.getText().toString().trim();
<<<<<<< HEAD
            String addressText = address.getText().toString().trim();

            UserInformationClass userInformation = new UserInformationClass(nameText, addressText);
=======
            String phoneNumText = phoneNum.getText().toString().trim();

            UserInformationClass userInformation = new UserInformationClass(nameText, phoneNumText);
>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b

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
<<<<<<< HEAD
                startActivity(new Intent(this, AddContacts.class));
=======
                startActivity(new Intent(this, AccountActivity.class));
                //startActivity(new Intent(this, AddContacts.class));
>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b
            }
        }
    }