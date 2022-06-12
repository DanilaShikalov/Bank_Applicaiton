package com.example.bank_applicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }

    public void getApplication(View view)
    {
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        String surname = arguments.get("surname").toString();
        Intent intent = new Intent(this, GetApplicationActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        startActivity(intent);
    }

    public void postApplication(View view)
    {
//        Intent intent = new Intent("com.example.myapplication.PostApplicationActivity");
//        startActivity(intent);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        String surname = arguments.get("surname").toString();
        Intent intent = new Intent(this, PostApplicationActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        startActivity(intent);
    }



}