package com.example.srbd.implictintent;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebSiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;
    static final int REQUEST_IMAGE_CAPTURE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebSiteEditText=findViewById(R.id.website_edittext);
        mLocationEditText=findViewById(R.id.location_edittext);
        mShareTextEditText=findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String url=mWebSiteEditText.getText().toString();
        Uri webpage= Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents","Can't handle this");
        }
    }

    public void openLocation(View view) {
        String loc=mLocationEditText.getText().toString();
        Uri addressUri= Uri.parse("geo:0,0?q"+loc);
        Intent intent=new Intent(Intent.ACTION_VIEW,addressUri);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents","Can't handle this");
        }
    }

    public void shareText(View view) {
        String txt=mLocationEditText.getText().toString();
        String mimeType="text/plain";
        ShareCompat.IntentBuilder.from(this)
                                    .setType(mimeType)
                                    .setChooserTitle("Share this text with: ")
                                    .setText(txt)
                                    .startChooser();
    }

    public void takePicture(View view) {
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }
}
