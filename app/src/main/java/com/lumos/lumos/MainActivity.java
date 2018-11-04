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

    private int clickCount = 0;
    private FirebaseAuth firebaseAuth;

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

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogOut) {
            finish();
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        }

        if (view == mainButton) {
            clickCount++;

            if(clickCount == 1){
                startActivity(new Intent(getApplicationContext(), MessageActivity.class));
            } else if(clickCount == 2){
                startActivity(new Intent(getApplicationContext(), RecordVideoActivity.class));
            }
        }

        if (view == settings) {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
        }

    }
}