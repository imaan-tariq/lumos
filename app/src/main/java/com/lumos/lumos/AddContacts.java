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
import android.widget.Button;
import android.widget.EditText;


public class AddContacts extends AppCompatActivity implements View.OnClickListener{

    private static final int RESULT_PICK_CONTACT = 1;
    //public static final int REQUEST_CODE_PICK_CONTACT = 1;
    //public static final int  MAX_PICK_CONTACT= 1;
    //private static final String TAG = "MyActivity";

    private EditText name;
    private EditText phone;

    private EditText name1;
    private EditText name2;
    private EditText name3;
    private EditText name4;
    private EditText name5;

    private EditText phone1;
    private EditText phone2;
    private EditText phone3;
    private EditText phone4;
    private EditText phone5;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button save;
    private Button skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        contact1();
        contact2();
        contact3();
        contact4();
        contact5();

        save = findViewById(R.id.buttonSave);
        skip = findViewById(R.id.buttonSkip);
    }

    public void pickContact(View v)
    {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (requestCode == RESULT_PICK_CONTACT) {
            if (resultCode == RESULT_OK) {
                    contactPicked(data);
            }
        } else {
            Log.e("AddContacts", "Failed to pick contact");
        }
    }

    /**
     * Query the Uri and read contact details. Handle the picked contact data.
     * @param data
     */
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNumber = null ;
            String contactName = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNumber = cursor.getString(phoneIndex);
            contactName = cursor.getString(nameIndex);
            // Set the value to the textviews
            name.setText(contactName);
            phone.setText(phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void contact1(){
        name1 = findViewById(R.id.EditTextContactName1);
        phone1 = findViewById(R.id.EditTextPhone1);
        phone1.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        button1 = findViewById(R.id.buttonContact1);

        button1.setOnClickListener(this);
    }

    private void contact2(){
        name2 = findViewById(R.id.EditTextContactName2);
        phone2 = findViewById(R.id.EditTextPhone2);
        phone2.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        button2 = findViewById(R.id.buttonContact2);

        button2.setOnClickListener(this);
    }

    private void contact3(){
        name3 = findViewById(R.id.EditTextContactName3);
        phone3 = findViewById(R.id.EditTextPhone3);
        phone3.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        button3 = findViewById(R.id.buttonContact3);

        button3.setOnClickListener(this);
    }

    private void contact4(){
        name4 = findViewById(R.id.EditTextContactName4);
        phone4 = findViewById(R.id.EditTextPhone4);
        phone4.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        button4 = findViewById(R.id.buttonContact4);

        button4.setOnClickListener(this);
    }

    private void contact5(){
        name5 = findViewById(R.id.EditTextContactName5);
        phone5 = findViewById(R.id.EditTextPhone5);
        phone5.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        button5 = findViewById(R.id.buttonContact5);

        button5.setOnClickListener(this);
    }

    private void saveContacts(){
        /*String nameText = name.getText().toString().trim();
        String addressText = address.getText().toString().trim();

        UserInformationClass userInformation = new UserInformationClass(nameText, addressText);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Information Saved", Toast.LENGTH_LONG).show();1*/

    }

    @Override
    public void onClick(View view) {
        if(view == button1){
            pickContact(button1);
            name = name1;
            phone = phone1;
        }

        if(view == button2){
            pickContact(button2);
            name = name2;
            phone = phone2;
        }

        if(view == button3){
            pickContact(button3);
            name = name3;
            phone = phone3;
        }

        if(view == button4){
            pickContact(button4);
            name = name4;
            phone = phone4;
        }

        if(view == button5){
            pickContact(button5);
            name = name5;
            phone = phone5;
        }

        if(view == save){
            saveContacts();
        }

        if(view == skip){
           // finish();
            //startActivity(new Intent(this, UserProfileActivity.class));
        }
    }


    /***Alternative code to select a single contact
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
     ***/

   /***Alternative to pick multiple contacts
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

                String name =contacts.toString();
                name1.setText(name);
                //Log.i(TAG, "launchMultiplePhonePicker bundle.toString()= " + contacts.toString() );
            }
        }
    }***/
}
