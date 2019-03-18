package com.example.srbd.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private EditText mReply; //constant variable for message in second
    public static final String EXTRA_REPLY = "com.example.android.twoActivities.extra.REPLY"; //string for reply message
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mReply = findViewById(R.id.editText_second); //get the message id
        Intent intent = getIntent(); //get the intent action
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);  //get the message string
        TextView textView = findViewById(R.id.text_message); //message reference
        textView.setText(message); //set the textview message
    }

    public void returnReply(View view) {
        String reply = mReply.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        Log.d(LOG_TAG, "End SecondActivity");
        finish();
    }
}
