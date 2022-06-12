package com.example.bank_applicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_employee);
    }

    public void lookApp(View view)
    {
        Intent intent = new Intent("com.example.myapplication.EmployeeActivity");
        startActivity(intent);
    }

    public void patchApp(View view)
    {
        Intent intent = new Intent("com.example.myapplication.PatchEmployeeActivity");
        startActivity(intent);
    }

}