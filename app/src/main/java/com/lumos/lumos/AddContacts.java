package com.lumos.lumos;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class AddContacts extends AppCompatActivity {

    public static final int REQUEST_CODE_PICK_CONTACT = 1;
    public static final int  MAX_PICK_CONTACT= 5;
    private static final String TAG = "MyActivity";
    private EditText phone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        phone1 = findViewById(R.id.EditTextPhone1);
        phone1.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

//    @Override
//    public void onClick(View view) {
//
//    }

//      private final int PICK_CONTACT = 1;
//
//      @Override
//      protected void onCreate(Bundle savedInstanceState) {
//          super.onCreate(savedInstanceState);
//          setContentView(R.layout.activity_add_contacts);
//      }
//
//      public void callContacts(View v){
//          Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//          startActivityForResult(intent, PICK_CONTACT);
//      }
//
//      @Override
//      protected void onActivityResult(int reqCode, int resultCode, Intent data) {
//          super.onActivityResult(reqCode, resultCode, data);
//
//          if (reqCode == PICK_CONTACT) {
//              if (resultCode == AppCompatActivity.RESULT_OK) {
//                  Uri contactData =  data.getData();
//                  Cursor c = getContentResolver().query(contactData, null, null, null, null);
//
//                  if (c.moveToFirst()) {
//                      String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
//                      Toast.makeText(this, "You've picked:" + name, Toast.LENGTH_LONG).show();
//                  }
//              }
//          }
//      }

    public void launchMultiplePhonePicker(View view) {

        Intent phonebookIntent = new Intent("intent.action.INTERACTION_TOPMENU");
        phonebookIntent.putExtra("additional", "phone-multi");
        phonebookIntent.putExtra("maxRecipientCount", MAX_PICK_CONTACT);
        phonebookIntent.putExtra("FromMMS", true);
        startActivityForResult(phonebookIntent, REQUEST_CODE_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == REQUEST_CODE_PICK_CONTACT )
            {
                Bundle bundle =  data.getExtras();
                String result= bundle.getString("result");
                ArrayList<String> contacts = bundle.getStringArrayList("result");

                Log.i(TAG, "launchMultiplePhonePicker bundle.toString()= " + contacts.toString() );
            }
        }
    }
}
