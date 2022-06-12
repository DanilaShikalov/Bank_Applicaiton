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
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetApplicationActivity extends AppCompatActivity {
    private TextView textViewapplicationNum;
    private TextView textViewpriority;
    private TextView textViewregion;
    private TextView textViewsource;
    private TextView textViewdateCreation;
    private TextView textViewstage;
    private TextView textViewname;
    private TextView textViewsurname;
    private TextView textViewpatronymic;
    private TextView textViewproductCategory;
    private TextView textViewproductName;
    private TextView textViewfirstPaymentDate;
    private TextView textViewdisbursementDate;
    private TextView textViewrefSum;
    private TextView textViewdelayPeriod;
    private String url;
    private int id;
    private int num = 0;
    private int limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_application);
        textViewapplicationNum = (TextView) findViewById(R.id.textViewapplicationNum);
        textViewpriority = (TextView) findViewById(R.id.textViewpriority);
        textViewregion = (TextView) findViewById(R.id.textViewregion);
        textViewsource = (TextView) findViewById(R.id.textViewsource);
        textViewdateCreation = (TextView) findViewById(R.id.textViewdateCreation);
        textViewstage = (TextView) findViewById(R.id.textViewstage);
        textViewname = (TextView) findViewById(R.id.textViewname);
        textViewsurname = (TextView) findViewById(R.id.textViewsurname);
        textViewpatronymic = (TextView) findViewById(R.id.textViewpatronymic);
        textViewproductCategory = (TextView) findViewById(R.id.textViewproductCategory);
        textViewproductName = (TextView) findViewById(R.id.textViewproductName);
        textViewfirstPaymentDate = (TextView) findViewById(R.id.textViewfirstPaymentDate);
        textViewdisbursementDate = (TextView) findViewById(R.id.textViewdisbursementDate);
        textViewrefSum = (TextView) findViewById(R.id.textViewrefSum);
        textViewdelayPeriod = (TextView) findViewById(R.id.textViewdelayPeriod);
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        String surname = arguments.get("surname").toString();
        url = "https://bankswag2.herokuapp.com/bank/application/client/" + name + "/" + surname;
        new JSONTask().execute(url);
    }

    public void testBack(View view)
    {
        if (num != 0)
        {
            num--;
        }
        new JSONTask().execute(url);
    }

    public void test(View view)
    {
        if (num <= limit) {
            num++;
        }
        new JSONTask().execute(url);
    }

    public void testDelete(View view)
    {
        new JSONTask1().execute("https://bankswag2.herokuapp.com/bank/application/client/delete");
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();
        String surname = arguments.get("surname").toString();
        Intent intent = new Intent(this, ClientActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        startActivity(intent);
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
                JSONArray parentArray = new JSONArray(result);
                limit = parentArray.length();
                JSONObject jsonObject = parentArray.getJSONObject(num);
                JSONObject jsonObject1 = jsonObject.getJSONObject("application");
                id = jsonObject1.getInt("id");
                textViewapplicationNum.setText("Номер: " + jsonObject1.getString("applicationNum"));
                textViewpriority.setText("Приоритет: " + jsonObject1.getString( "priority"));
                textViewregion.setText("Регион: " + jsonObject1.getString("region"));
                textViewsource.setText("Цель: " + jsonObject1.getString("source"));
                textViewdateCreation.setText("Дата: " + jsonObject1.getString("dateCreation"));
                textViewstage.setText("Стадия: " + jsonObject1.getString("stage"));
                JSONObject jsonObject2 = jsonObject.getJSONObject("productParameters");
                textViewproductCategory.setText("Категория: " + jsonObject2.getString("productCategory"));
                textViewproductName.setText("Название: " + jsonObject2.getString( "productName"));
                textViewfirstPaymentDate.setText("Дата начала: " + jsonObject2.getString("firstPaymentDate"));
                textViewdisbursementDate.setText("Дата окончания: " + jsonObject2.getString("disbursementDate"));
                textViewrefSum.setText("Сумма: " + jsonObject2.getString("refSum"));
                textViewdelayPeriod.setText("Период задержки: " + jsonObject2.getString("delayPeriod"));
                JSONObject jsonObject3 = jsonObject.getJSONObject("client");
                textViewname.setText("Имя: " + jsonObject3.getString("name"));
                textViewsurname.setText("Фамилия: " + jsonObject3.getString("surname"));
                textViewpatronymic.setText("Отчество: " + jsonObject3.getString("patronymic"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class JSONTask1 extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("DELETE");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                String json = "{\n" +
                        "    \"id\": " + id +"\n" +
                        "}";

                //Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json);

                os.flush();
                os.close();

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG" , conn.getResponseMessage());

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}