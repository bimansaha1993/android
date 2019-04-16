package com.example.srbd.phonedailer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the edit_text main view and asssign it to edittext
        EditText editText = findViewById(R.id.editText_main);
        if (editText != null){

            //if view is not found set the listener for edittext
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        dialNumber();
                        handled = true;
                    }
                    return handled;
                }
            });
        }
    }

    private void dialNumber() {
        // Find the editText_main view.
        EditText editText = findViewById(R.id.editText_main);
        String phoneNum = null;

        // If the editText field is not null,
        // concatenate "tel: " with the phone number string.
        if (editText != null) phoneNum = "tel:" + editText.getText().toString();

        // Log the concatenated phone number for dialing.
        Log.d(TAG, "dialNumber: " + phoneNum);

        // Specify the intent.
        Intent intent = new Intent(Intent.ACTION_DIAL);

        // Set the data for the intent as the phone number.
        intent.setData(Uri.parse(phoneNum));

        // If the intent resolves to a package (app),
        // start the activity with the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "ImplicitIntents: Can't handle this!");
        }
    }
}
