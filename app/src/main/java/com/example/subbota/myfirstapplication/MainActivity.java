package com.example.subbota.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            Uri contactData = data.getData();
            Cursor cursor = getContentResolver().query(contactData, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
            String message;
            if (cursor == null || !cursor.moveToFirst())
                message = "Has no ContactsContract.Contacts.DISPLAY_NAME";
            else {
                Snackbar.make(findViewById(R.id.button), cursor.getString(0), Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
