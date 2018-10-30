package com.lumos.lumos;

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AccountActivity extends AppCompatActivity {
=======
import android.accounts.Account;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "";
    private EditText userName;
    private EditText userPhone;
    private Button save;
    private Button buttonEditContacts;
    private Button buttonDelete;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUserId;
    private DatabaseReference databaseReference;
    private String oldName;
    private String oldPhone;
>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
<<<<<<< HEAD
    }
}
=======

        userName = findViewById(R.id.EditTextName);
        userPhone = findViewById(R.id.EditTextPhone);
        save = findViewById(R.id.buttonSaveNewNameAndPhone);
        buttonEditContacts = findViewById(R.id.buttonEditContacts);
        buttonDelete = findViewById(R.id.buttonDelete);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUserId = firebaseAuth.getCurrentUser() ;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(currentUserId.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                oldName = dataSnapshot.child("name").getValue(String.class);
                oldPhone = dataSnapshot.child("phone").getValue(String.class);
                userName.setHint(oldName);
                userPhone.setHint(oldPhone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        save.setOnClickListener(this);
        buttonEditContacts.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    private void saveNewUserInformation(){
        String newName = userName.getText().toString().trim();
        String newPhone = userPhone.getText().toString().trim();

        if (newName.matches("") && newPhone.matches("")){
            return;
        }
        else if (newName.matches("") && (!newPhone.matches(""))) {
            UserInformationClass userInformation = new UserInformationClass(oldName, newPhone);

            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(userInformation);

            Toast.makeText(this, "New Information Saved", Toast.LENGTH_LONG).show();
        }
        else if ((!newName.matches("")) && newPhone.matches("")){
            UserInformationClass userInformation = new UserInformationClass(newName, oldPhone);

            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(userInformation);

            Toast.makeText(this, "New Information Saved", Toast.LENGTH_LONG).show();
        }
        else {
            UserInformationClass userInformation = new UserInformationClass(newName, newPhone);

            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(userInformation);

            Toast.makeText(this, "New Information Saved", Toast.LENGTH_LONG).show();
        }

    }

    private void deleteAccount() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        DatabaseReference drUser = FirebaseDatabase.getInstance().getReference().child(currentUserId.getUid());
                        drUser.removeValue();

                        currentUserId.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG,"Account has been deleted");
                                    Toast.makeText(AccountActivity.this, "Account has been deleted", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(AccountActivity.this, SignInActivity.class));
                                    finish();
                                } else {
                                    Log.w(TAG,"Something is wrong!");
                                }
                            }
                        });
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Are you sure you want to delete your account?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    @Override
    public void onClick(View view) {
        if(view == save){
            saveNewUserInformation();
        }

        if(view == buttonDelete){
            startActivity(new Intent(this, AddContacts.class));
        }

        if(view == buttonDelete){
            deleteAccount();
        }
    }
}

>>>>>>> b8ebb0e0c8f0fdd3bdb80d748d022f8da5c6334b
