package com.lumos.lumos;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //private static int VIDEO_REQUEST = 101;
    //private Uri videoUri = null;

    private Button buttonLogOut;
    private Button mainButton;
    private Button settings;

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
        if(view == buttonLogOut){
            finish();
            startActivity(new Intent(this, SignInActivity.class));
        }

        if(view == mainButton){
            //captureVideo();
            startActivity(new Intent(this, RecordVideoActivity.class));
        }

        if(view == settings){
            startActivity(new Intent(this, AccountActivity.class));
        }

    }

    //opens camera
    /*
    public void captureVideo(){
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if(videoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==VIDEO_REQUEST && resultCode==RESULT_OK){
            videoUri = data.getData();
        }
    }


 //Create intent to take video.
/*
    public static Intent createTakeVideoIntent() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        Uri uri = getOutputVideoUri();  // create a file to save the video in specific folder
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high

        return intent;
    }
*/ /*

    @CheckForNull
    private static Uri getOutputVideoUri() {
        if (Environment.getExternalStorageState() == null) {
            return null;
        }

        File mediaStorage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "YOUR_APP_VIDEO");
        if (!mediaStorage.exists() &&
                !mediaStorage.mkdirs()) {
            Log.e(YourApplication.TAG, "failed to create directory: " + mediaStorage);
            return null;
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        File mediaFile = new File(mediaStorage, "VID_" + timeStamp + ".mp4");
        return Uri.fromFile(mediaFile);
    }
    */
}

