package com.hmkcode.android;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hmkcode.android.vo.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvIsConnected;
    EditText etName,etCountry,etTwitter;
    Button btnPost;

    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
        etName = (EditText) findViewById(R.id.etName);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etTwitter = (EditText) findViewById(R.id.etTwitter);
        btnPost = (Button) findViewById(R.id.btnPost);

        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        } else{
            tvIsConnected.setText("You are NOT conncted");
        }
        btnPost.setOnClickListener(this);
    }


    private boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPost:
                if(!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean validate() {
        if(etName.getText().toString().trim().equals(""))
            return false;
        else if (etCountry.getText().toString().trim().equals(""))
            return false;
        else if (etTwitter.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            person = new Person();
            person.setName(etName.getText().toString());
            person.setCountry(etCountry.getText().toString());
            person.setTwitter(etTwitter.getText().toString());

            return POST(urls[0],person);
        }
    }

    private String POST(String url, Person person) {
        return null;
    }
}
