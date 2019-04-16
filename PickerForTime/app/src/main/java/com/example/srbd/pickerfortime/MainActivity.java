package com.example.srbd.pickerfortime;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showTimePicker(View view) {
        DialogFragment newFragment= new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),getString(R.string.time_picker));
    }

    public void processTimePickerResult(int hour,int minute){
        String hour_string=Integer.toString(hour);
        String minute_string=Integer.toString(minute);
        String timeMessage=(hour_string+":"+minute_string);
        Toast.makeText(this,getString(R.string.time)+timeMessage,Toast.LENGTH_SHORT).show();
    }


}
