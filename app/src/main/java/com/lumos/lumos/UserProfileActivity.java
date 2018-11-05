package com.lumos.lumos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
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

        private DatabaseReference databaseReference;
        private EditText name, phoneNum;
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
            phoneNum = (EditText)findViewById(R.id.EditTextPhoneNum);
            phoneNum.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
            save = findViewById(R.id.buttonAddInfo);

            FirebaseUser user = firebaseAuth.getCurrentUser();

            textViewUserEmail = findViewById(R.id.textViewLoggedIn);
           // textViewUserEmail.setText("Hello " + user.getEmail());

            save.setOnClickListener(this);
        }

        private void saveUserInformation(){
            String nameText = name.getText().toString().trim();
            String phoneNumText = phoneNum.getText().toString().trim();

            UserInformationClass userInformation = new UserInformationClass(nameText, phoneNumText);

            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(userInformation);

            Toast.makeText(this, "Information Saved", Toast.LENGTH_LONG).show();

        }
        private void saveAccount() {
            String newName = name.getText().toString().trim();
            String newPhone = phoneNum.getText().toString().trim();
            if (TextUtils.isEmpty(newName)) {
                Toast.makeText(this, "Please enter Name ", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(newPhone)) {
                Toast.makeText(this, "Please enter Phone Number ", Toast.LENGTH_LONG).show();
                return;
            }
            saveUserInformation();
            finish();
            startActivity(new Intent(this, AddContacts.class));
        }

        @Override
        public void onClick(View view) {
            if(view == save){
               saveAccount();
            }
        }
    }