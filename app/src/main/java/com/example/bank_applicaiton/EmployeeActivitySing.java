package com.example.bank_applicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmployeeActivitySing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_sing);
    }


    public void loginEmployee(View view)
    {
        TextView login = (TextView) findViewById(R.id.editTextLoignEmp);
        TextView pass = (TextView) findViewById(R.id.editTextLoginPassEmp);
        TextView error = (TextView) findViewById(R.id.textViewErrorEmp);
        if (!login.getText().toString().equals("") && !pass.getText().toString().equals(""))
        {
            if (login.getText().toString().equals("admin") && pass.getText().toString().equals("admin"))
            {
                Intent intent = new Intent("com.example.myapplication.ChooseEmployeeActivity");
                startActivity(intent);
            }
            else {
                error.setText("Неверные данные");
            }
        }
        else {
            error.setText("Введите данные");
        }
    }
}