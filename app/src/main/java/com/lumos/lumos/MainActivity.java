package com.lumos.lumos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonLogOut;
    private Button mainButton;
    private Button settings;
    private FirebaseAuth firebaseAuth;

    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogOut = findViewById(R.id.buttonLogOut);
        mainButton = findViewById(R.id.mainButton);
        settings = findViewById(R.id.buttonSettings);

        buttonLogOut.setOnClickListener(this);
        mainButton.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogOut) {
            finish();
            firebaseAuth.getInstance().signOut();
               //setContentView(R.layout.activity_sign_in);
                startActivity(new Intent(this, SignInActivity.class));

        }

        if (view == mainButton) {
            clickCount++;

            if(clickCount == 1){
                startActivity(new Intent(this, MessageActivity.class));
                clickCount=0;
            } else if(clickCount == 2){
                startActivity(new Intent(this, RecordVideoActivity.class));
                clickCount=0;
            }
            else{
                clickCount=0;
            }
        }

        if (view == settings) {
            startActivity(new Intent(this, AccountActivity.class));
        }

    }
}