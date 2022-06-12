package com.example.bank_applicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class PatchEmployeeActivity extends AppCompatActivity {
    private String[] tables = { "Application", "ApplicationAggr", "ApplicationList", "Client", "Credit",
            "Declined", "Employees", "Income", "Loan", "ProductDeclined",
            "ProductParameters"};
    private String[] columns;
    private Context context;
    private Spinner spinner;
    private Spinner spinner1;
    private EditText editText;
    private EditText str_;
    private TextView resutttt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch_employee);
        editText = (EditText) findViewById(R.id.editTextId);
        resutttt = (TextView)  findViewById(R.id.textViewPatch);
        str_ = (EditText) findViewById(R.id.editTextPatchEmp);
        context = this;
        spinner = findViewById(R.id.spinnerTables);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tables);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Выберите таблицу");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinner.getSelectedItem().toString()) {
                    case "Application":
                        columns = new String[]{"ApplicationNum",
                                "Stage",
                                "Source",
                                "Region",
                                "Branch",
                                "DateCreation",
                                "Priority"};
                        break;
                    case "ApplicationAggr":
                        columns = new String[]{"Type",
                                "Number",
                                "TotalLimit",
                                "TotalPayment",
                                "LimitProductCategory",
                                "LimitProductName",
                                "LastDateCreated",
                                "LastDateFunded"};
                        break;
                    case "ApplicationList":
                        columns = new String[]{"PrimaryKey",
                                "StageID",
                                "ProductCategory",
                                "ProductID",
                                "ProductName",
                                "MarketingSegment",
                                "ParticipationType",
                                "Limit",
                                "Payment",
                                "DateCreation",
                                "DateApproved",
                                "Status",
                                "OpenDate",
                                "FinishDate"};
                        break;
                    case "Client":
                        columns = new String[]{"BirthDate",
                                "BirthPlace",
                                "Age",
                                "Gender",
                                "Citizenship",
                                "Education",
                                "Surname",
                                "Name",
                                "Patronymic",
                                "DocumentType",
                                "Passportnum",
                                "Issuedate",
                                "IssuePlace",
                                "IssueAuthority",
                                "Departmentcode",
                                "SNILS"};
                        break;
                    case "Credit":
                        columns = new String[]{"Index",
                                "Type",
                                "PrimaryKey",
                                "ContractRef",
                                "Currency",
                                "IssueDate",
                                "Annuity",
                                "Product",
                                "Comment"};
                        break;

                    case "Declined":
                        columns = new String[]{"LastDate"};
                        break;
                    case "Employees":
                        columns = new String[]{"NAME",
                                "SURNAME",
                                "RANG"};
                        break;
                    case "Income":
                        columns = new String[]{"Type",
                                "Main",
                                "Confirmation",
                                "Periodicity",
                                "PaymentManner",
                                "Currency",
                                "Value",
                                "Average"};
                        break;
                    case "Loan":
                        columns = new String[]{"Type",
                                "Status",
                                "Annuity",
                                "InitialAmount",
                                "Outstanding",
                                "PaidInterest",
                                "PledgeValue",
                                "DateFunded",
                                "DateRealClose"};
                        break;
                    case "ProductDeclined":
                        columns = new String[]{"Type",
                                "Number"};
                        break;
                    case "ProductParameters":
                        columns = new String[]{"ProductCategory",
                                "ProductName",
                                "Purpose",
                                "DisbursementDate",
                                "RepaymentScheme",
                                "FirstPaymentDate",
                                "RepaymentDate",
                                "DownPaymentSource",
                                "DelayPeriod",
                                "RefSum"};
                        break;
                }
                spinner1 = findViewById(R.id.spinnerColumns);
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, columns);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner1.setPrompt("Выберите столбец");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void patchApplication(View view)
    {
        new JSONTask(Integer.parseInt(editText.getText().toString()), spinner.getSelectedItem().toString(), spinner1.getSelectedItem().toString(), str_.getText().toString())
        .execute("https://bankswag2.herokuapp.com/bank/application/update");
    }


    class JSONTask extends AsyncTask<String, String, String>
    {
        private int id;
        private String table;
        private String column;
        private String str;

        public JSONTask(int id, String table, String column, String str) {
            this.id = id;
            this.table = table;
            this.column = column;
            this.str = str;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PATCH");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                String json = "{\n" +
                        "    \"id\": " + id + ",\n" +
                        "    \"str\": \"" + str + "\",\n" +
                        "    \"column\": \"" + column + "\",\n" +
                        "    \"table\": \"" + table + "\"\n" +
                        "}";
                Log.i("JSON", json);
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