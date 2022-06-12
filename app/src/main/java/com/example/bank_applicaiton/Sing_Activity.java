package com.example.bank_applicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Sing_Activity extends AppCompatActivity {
    private boolean login;
    private TextView name;
    private String name_;
    private TextView surname;
    private String surname_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);
        login = false;
    }


    public void showLogin_in(View view) throws InterruptedException, ExecutionException {
        login = false;
        name = (TextView) findViewById(R.id.editTextTextPersonName);
        name_ = name.getText().toString();
        surname = (TextView) findViewById(R.id.editTextTextPersonName2);
        surname_ = surname.getText().toString();
        JSONTask jsonTask = new JSONTask();
        if (!name_.equals("") && !surname_.equals("")) {
            jsonTask.execute("https://bankswag2.herokuapp.com/bank/application/client/enter/" + name_ + "/" + surname_).get();
            Log.d("My login", name_ + surname_);
        }
        else {
            TextView error = (TextView) findViewById(R.id.textViewError);
            error.setText("Введите данные");
        }
    }

    public void nextPage()
    {
        TextView error = (TextView) findViewById(R.id.textViewError);
        if (login)
        {
            error.setText("");
            Intent intent = new Intent(this, ClientActivity.class);
            intent.putExtra("name", name_);
            intent.putExtra("surname", surname_);
            startActivity(intent);
        }
        else {
            error.setText("Неверные данные");
        }
    }

    class JSONTask extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer= new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return String.valueOf(buffer);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                {
                    connection.disconnect();
                }
                try {
                    if (reader != null)
                    {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                Log.d("My App", jsonObject.toString());
                if (jsonObject.getString("login").equals("true"))
                {
                    login = true;
                }
                else {
                    login = false;
                }
                nextPage();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}