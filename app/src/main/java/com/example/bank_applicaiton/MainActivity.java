package com.example.bank_applicaiton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.VerifiedInputEvent;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textViewTEST);
    }


    public void showSing_in(View view)
    {
        Intent intent = new Intent("com.example.myapplication.Sing_Activity");
        startActivity(intent);
    }

    public void test(View view)
    {
        //new JSONTask().execute("https://bankswag2.herokuapp.com/bank/application/client/post");
        Intent intent = new Intent("com.example.myapplication.EmployeeActivitySing");
        startActivity(intent);
    }

    class JSONTask extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                java.util.Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date_ = simpleDateFormat.format(date);
                String json = "{\n" +
                        "  \"application\" : {\n" +
                        "    \"applicationNum\" : \"TEST3\",\n" +
                        "    \"dateCreation\" : \"" + date_ + "\",\n" +
                        "    \"region\" : \"North Island\",\n" +
                        "    \"branch\" : \"60301\",\n" +
                        "    \"stage\" : \"PROGRESS\",\n" +
                        "    \"source\" : \"YES\",\n" +
                        "    \"priority\" : \"vip\"\n" +
                        "  },\n" +
                        "  \"applicationAggr\" : {\n" +
                        "    \"totalLimit\" : 17,\n" +
                        "    \"totalPayment\" : 932806,\n" +
                        "    \"limitProductCategory\" : 1388151,\n" +
                        "    \"limitProductName\" : 43806156,\n" +
                        "    \"lastDateCreated\" : \"2020-10-02\",\n" +
                        "    \"lastDateFunded\" : \"2022-01-27\",\n" +
                        "    \"number\" : 6,\n" +
                        "    \"type\" : \"3\"\n" +
                        "  },\n" +
                        "  \"applicationList\" : {\n" +
                        "    \"primaryKey\" : \"h9C 2X1\",\n" +
                        "    \"stageID\" : \"5\",\n" +
                        "    \"productCategory\" : \"credit\",\n" +
                        "    \"productID\" : \"2\",\n" +
                        "    \"productName\" : \"MIREA\",\n" +
                        "    \"marketingSegment\" : \"manufacturer\",\n" +
                        "    \"participationType\" : \"natural\",\n" +
                        "    \"payment\" : 23214,\n" +
                        "    \"dateCreation\" : \"2021-04-14\",\n" +
                        "    \"dateApproved\" : \"2021-12-01\",\n" +
                        "    \"openDate\" : \"2021-08-18\",\n" +
                        "    \"finishDate\" : \"2026-08-30\",\n" +
                        "    \"limit\" : 5710544,\n" +
                        "    \"status\" : \"inProcess\"\n" +
                        "  },\n" +
                        "  \"client\" : {\n" +
                        "    \"birthDate\" : \"1982-12-04\",\n" +
                        "    \"birthPlace\" : \"Mokpo\",\n" +
                        "    \"age\" : 26,\n" +
                        "    \"gender\" : \"Female\",\n" +
                        "    \"citizenship\" : \"EU\",\n" +
                        "    \"education\" : \"SS\",\n" +
                        "    \"surname\" : \"Cardenas\",\n" +
                        "    \"patronymic\" : \"Roary E. Sears\",\n" +
                        "    \"documentType\" : \"Passport\",\n" +
                        "    \"passportnum\" : \"9952223435\",\n" +
                        "    \"issuedate\" : \"2002-01-07\",\n" +
                        "    \"issuePlace\" : \"Cork\",\n" +
                        "    \"issueAuthority\" : \"537566 2186531855\",\n" +
                        "    \"departmentcode\" : \"58254-322\",\n" +
                        "    \"snils\" : \"61757\",\n" +
                        "    \"name\" : \"Imogene\"\n" +
                        "  },\n" +
                        "  \"credit\" : {\n" +
                        "    \"contractRef\" : \"4687087346\",\n" +
                        "    \"primaryKey\" : \"4\",\n" +
                        "    \"product\" : \"none\",\n" +
                        "    \"issueDate\" : \"2019-10-29\",\n" +
                        "    \"annuity\" : \"50000\",\n" +
                        "    \"comment\" : \"2021-04-14\",\n" +
                        "    \"index\" : \"1-POE\",\n" +
                        "    \"currency\" : \"EU\",\n" +
                        "    \"type\" : \"credit\"\n" +
                        "  },\n" +
                        "  \"declined\" : {\n" +
                        "    \"lastDate\" : \"2021-12-07\"\n" +
                        "  },\n" +
                        "  \"employess\" : {\n" +
                        "    \"name\" : \"Amal\",\n" +
                        "    \"surname\" : \"Key\",\n" +
                        "    \"rang\" : \"consultant\"\n" +
                        "  },\n" +
                        "  \"income\" : {\n" +
                        "    \"paymentManner\" : \"state\",\n" +
                        "    \"average\" : \"75000\",\n" +
                        "    \"confirmation\" : \"people\",\n" +
                        "    \"periodicity\" : \"1/2month\",\n" +
                        "    \"main\" : \"Trade\",\n" +
                        "    \"currency\" : \"EU\",\n" +
                        "    \"type\" : \"Linear\",\n" +
                        "    \"value\" : \"25000\"\n" +
                        "  },\n" +
                        "  \"loan\" : {\n" +
                        "    \"outstanding\" : 17340,\n" +
                        "    \"paidInterest\" : 30523,\n" +
                        "    \"pledgeValue\" : 144138,\n" +
                        "    \"dateFunded\" : \"2021-03-25\",\n" +
                        "    \"dateRealClose\" : \"2023-07-21\",\n" +
                        "    \"initialAmount\" : 1393864,\n" +
                        "    \"annuity\" : 50000,\n" +
                        "    \"type\" : \"normal\",\n" +
                        "    \"status\" : \"inProcess\"\n" +
                        "  },\n" +
                        "  \"productDeclined\" : {\n" +
                        "    \"number\" : 10,\n" +
                        "    \"type\" : \"mortgage\"\n" +
                        "  },\n" +
                        "  \"productParameters\" : {\n" +
                        "    \"downPaymentSource\" : \"bank\",\n" +
                        "    \"purpose\" : \"education\",\n" +
                        "    \"disbursementDate\" : \"2020-11-10\",\n" +
                        "    \"repaymentScheme\" : \"year\",\n" +
                        "    \"firstPaymentDate\" : \"2020-10-26\",\n" +
                        "    \"repaymentDate\" : \"2022-04-12\",\n" +
                        "    \"delayPeriod\" : 5,\n" +
                        "    \"productCategory\" : \"credit\",\n" +
                        "    \"productName\" : \"MIREA\",\n" +
                        "    \"refSum\" : 11505676\n" +
                        "  }\n" +
                        "}";
//                JSONObject jsonParam = new JSONObject();
//                jsonParam.put("timestamp", 1488873360);
//                jsonParam.put("uname", message.getUser());
//                jsonParam.put("message", message.getMessage());
//                jsonParam.put("latitude", 0D);
//                jsonParam.put("longitude", 0D);

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