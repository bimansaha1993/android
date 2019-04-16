package com.example.srbd.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //get the intents and its data
        Intent intent=getIntent();
        String message=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView=findViewById(R.id.order_textview);
        textView.setText(message);

        // create the spinner
        Spinner spinner=findViewById(R.id.label_spinner);
        if(spinner!=null)
            spinner.setOnItemSelectedListener(this);
        //crate arrayadapter using the spin array and default spinner layout
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.labels_array,android.R.layout.simple_spinner_item);
        //specify the layout to use when the list of choice s appers
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply the adapter to the spinner
        if(spinner!=null)
            spinner.setAdapter(adapter);
    }

    public void onRadioButtonClicked(View view) {
        //is the button now checked
        boolean checked=((RadioButton)view).isChecked();
        //check which button clicked
        switch(view.getId()){
            case R.id.sameday:
                if(checked)
                    //sameday service
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.next:
                if(checked)
                    //next day delivery
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if(checked)
                    //pickup
                    displayToast(getString(R.string.pick_up));
                break;
            default:
                //do nothing
                break;
        }

    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel=adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
