package com.example.bank_applicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PostApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_application);
    }

    public void postNextPage(View view)
    {
        TextView error = (TextView) findViewById(R.id.textPostError);
        String region_;
        String priority_;
        String average_;
        String per_;
        String source_;
        String sum_; //int
        String category_;
        String name_;
        String val_;
        String first_; //int
        String close_;
        String month_; //int
        try {
            EditText region = (EditText) findViewById(R.id.editTextRegion);
            region_ = region.getText().toString();
            EditText priority = (EditText) findViewById(R.id.editTextPriotity);
            priority_ = priority.getText().toString();
            EditText average = (EditText) findViewById(R.id.editTextTextAverage);
            average_ = average.getText().toString();
            EditText per = (EditText) findViewById(R.id.editTextPer);
            per_ = per.getText().toString();
            EditText source = (EditText) findViewById(R.id.editTextSource);
            source_ = source.getText().toString();
            EditText sum = (EditText) findViewById(R.id.editTextSum);
            sum_ = sum.getText().toString();
            EditText category = (EditText) findViewById(R.id.editTextCat);
            category_ = category.getText().toString();
            EditText name = (EditText) findViewById(R.id.editTextName);
            name_ = name.getText().toString();
            EditText val = (EditText) findViewById(R.id.editTextVal);
            val_ = val.getText().toString();
            EditText first = (EditText) findViewById(R.id.editTextFirst);
            first_ = first.getText().toString();
            EditText close = (EditText) findViewById(R.id.editTextClose);
            close_ = close.getText().toString();
            EditText month = (EditText) findViewById(R.id.editTextTMonth);
            month_ = month.getText().toString();
        } catch (Exception e) {
            error.setText("Введите корректные данные");
            return;
        }
        if (!region_.equals("") && !priority_.equals("") && !average_.equals("") &&
                !per_.equals("") && !source_.equals("") && !sum_.equals("") &&
                !category_.equals("") && !name_.equals("") && !val_.equals("") &&
                !first_.equals("") && !close_.equals("") && !month_.equals("")) {
            error.setText("");
            Bundle arguments = getIntent().getExtras();
            String name = arguments.get("name").toString();
            String pass = arguments.get("surname").toString();
            Intent intent = new Intent(this, PostApplicationClientActivity.class);
            intent.putExtra("region", region_);
            intent.putExtra("priority", priority_);
            intent.putExtra("average", average_);
            intent.putExtra("per", per_);
            intent.putExtra("source", source_);
            intent.putExtra("sum", sum_);
            intent.putExtra("category", category_);
            intent.putExtra("name", name_);
            intent.putExtra("val", val_);
            intent.putExtra("first", first_);
            intent.putExtra("close", close_);
            intent.putExtra("month", month_);
            intent.putExtra("nameMain", name);
            intent.putExtra("passMain", pass);
            startActivity(intent);
        }
        else {
            error.setText("Введите данные");
        }
    }
}