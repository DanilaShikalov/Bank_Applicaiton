package com.example.bank_applicaiton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostApplicationClientActivity extends AppCompatActivity {
    private String region_;
    private String priority_;
    private String average_;
    private String per_;
    private String source_;
    private String sum_; //int
    private String category_;
    private String name_;
    private String val_;
    private String first_; //int
    private String close_;
    private String month_; //int
    private String surnameClient_;
    private String patronicClient_;
    private String DateBirthClient_;
    private String placeBirthClient_;
    private String documentClient_;
    private String whoGiveClient_;
    private String dateGiveClient_;
    private String placeGiveClient_;
    private String ageClient_; //int
    private String codeClient_;
    private String genderClient_;
    private String educationClient_;
    private String nationalClient_;
    private String snilsClient_;
    private String nameMain;
    private String passMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_application_client);
        Bundle arguments = getIntent().getExtras();
        region_ = arguments.get("region").toString();
        priority_ = arguments.get("priority").toString();
        average_ = arguments.get("average").toString();
        per_ = arguments.get("per").toString();
        source_ = arguments.get("source").toString();
        sum_ = arguments.get("sum").toString();
        category_ = arguments.get("category").toString();
        name_ = arguments.get("name").toString();
        val_ = arguments.get("val").toString();
        first_ = arguments.get("first").toString();
        close_ = arguments.get("close").toString();
        month_ = arguments.get("month").toString();
        nameMain = arguments.get("nameMain").toString();
        passMain = arguments.get("passMain").toString();
        TextView nameClient = (TextView) findViewById(R.id.editTextClientName);
        nameClient.setText(nameMain);
        TextView numberClient = (TextView) findViewById(R.id.editTextNumberofDoc);
        numberClient.setText(passMain);
    }

    public void postReadyApplicationClient(View view)
    {
        TextView error = (TextView) findViewById(R.id.textViewErrorClient);
        TextView ageClient = null;
        try {
            TextView surnameClient = (TextView) findViewById(R.id.editTextClientSurname);
            surnameClient_ = surnameClient.getText().toString();
            TextView patronicClient = (TextView) findViewById(R.id.editTextClientPatronic);
            patronicClient_ = patronicClient.getText().toString();
            TextView DateBirthClient = (TextView) findViewById(R.id.editTextDate);
            DateBirthClient_ = DateBirthClient.getText().toString();
            TextView placeBirthClient = (TextView) findViewById(R.id.editTextPlaceBirth);
            placeBirthClient_ = placeBirthClient.getText().toString();
            TextView documentClient = (TextView) findViewById(R.id.editTextDocument);
            documentClient_ = documentClient.getText().toString();
            TextView whoGiveClient = (TextView) findViewById(R.id.editTextWhoGive);
            whoGiveClient_ = whoGiveClient.getText().toString();
            TextView dateGiveClient = (TextView) findViewById(R.id.editTextDateGive);
            dateGiveClient_ = dateGiveClient.getText().toString();
            TextView placeGiveClient = (TextView) findViewById(R.id.editTextPlaceGive);
            placeGiveClient_ = placeGiveClient.getText().toString();
            ageClient = (TextView) findViewById(R.id.editTextAge);
            ageClient_ = ageClient.getText().toString();
            TextView codeClient = (TextView) findViewById(R.id.editTextCode);
            codeClient_ = codeClient.getText().toString();
            TextView genderClient = (TextView) findViewById(R.id.editTextGender);
            genderClient_ = genderClient.getText().toString();
            TextView educationClient = (TextView) findViewById(R.id.editTextEducation);
            educationClient_ = educationClient.getText().toString();
            TextView nationalClient = (TextView) findViewById(R.id.editTextNational);
            nationalClient_ = nationalClient.getText().toString();
            TextView snilsClient = (TextView) findViewById(R.id.editTextSnils);
            snilsClient_ = snilsClient.getText().toString();
        } catch (Exception e) {
            error.setText("Введите корректные данные");
        }
        if (!surnameClient_.equals("") && !patronicClient_.equals("") &&
                !DateBirthClient_.equals("") && !placeBirthClient_.equals("") && !documentClient_.equals("") &&
                !whoGiveClient_.equals("") && !dateGiveClient_.equals("") &&
                !placeGiveClient_.equals("") && !ageClient_.equals("") && !codeClient_.equals("") &&
                !genderClient_.equals("") && !educationClient_.equals("") && !nationalClient_.equals("") &&
                !snilsClient_.equals(""))
        {
            error.setText("");
            new JSONTask().execute("https://bankswag2.herokuapp.com/bank/application/client/post");
//            Intent intent = new Intent(this, ClientActivity.class);
//            intent.putExtra("name", nameMain);
//            intent.putExtra("surname", passMain);
//            startActivity(intent);
        }
        else {
            error.setText("Введите данные");
        }
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
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date_ = simpleDateFormat.format(date);
                String json = "{\n" +
                        "  \"application\" : {\n" +
                        "    \"applicationNum\" : \"none\",\n" +
                        "    \"dateCreation\" : \"" + date_ + "\",\n" +
                        "    \"region\" : \"" + region_ + "\",\n" +
                        "    \"branch\" : \"none\",\n" +
                        "    \"stage\" : \"none\",\n" +
                        "    \"source\" : \"none\",\n" +
                        "    \"priority\" : \"" + priority_ + "\"\n" +
                        "  },\n" +
                        "  \"applicationAggr\" : {\n" +
                        "    \"totalLimit\" : 0,\n" +
                        "    \"totalPayment\" : 0,\n" +
                        "    \"limitProductCategory\" : 0,\n" +
                        "    \"limitProductName\" : 0,\n" +
                        "    \"lastDateCreated\" : \"2020-01-01\",\n" +
                        "    \"lastDateFunded\" : \"2020-01-01\",\n" +
                        "    \"number\" : 0,\n" +
                        "    \"type\" : \"none\"\n" +
                        "  },\n" +
                        "  \"applicationList\" : {\n" +
                        "    \"primaryKey\" : \"none\",\n" +
                        "    \"stageID\" : \"none\",\n" +
                        "    \"productCategory\" : \"none\",\n" +
                        "    \"productID\" : \"none\",\n" +
                        "    \"productName\" : \"none\",\n" +
                        "    \"marketingSegment\" : \"none\",\n" +
                        "    \"participationType\" : \"none\",\n" +
                        "    \"payment\" : 0,\n" +
                        "    \"dateCreation\" : \"2020-01-01\",\n" +
                        "    \"dateApproved\" : \"2020-01-01\",\n" +
                        "    \"openDate\" : \"2020-01-01\",\n" +
                        "    \"finishDate\" : \"2020-01-01\",\n" +
                        "    \"limit\" : 0,\n" +
                        "    \"status\" : \"none\"\n" +
                        "  },\n" +
                        "  \"client\" : {\n" +
                        "    \"birthDate\" : \"" + DateBirthClient_ + "\",\n" +
                        "    \"birthPlace\" : \"" + placeBirthClient_ + "\",\n" +
                        "    \"age\" : " + ageClient_ + ",\n" +
                        "    \"gender\" : \"" + genderClient_ + "\",\n" +
                        "    \"citizenship\" : \"" + nationalClient_ + "\",\n" +
                        "    \"education\" : \"" + educationClient_ + "\",\n" +
                        "    \"surname\" : \"" + surnameClient_ + "\",\n" +
                        "    \"patronymic\" : \"" + patronicClient_ + "\",\n" +
                        "    \"documentType\" : \"" + documentClient_ + "\",\n" +
                        "    \"passportnum\" : \"" + passMain + "\",\n" +
                        "    \"issuedate\" : \"" + dateGiveClient_ + "\",\n" +
                        "    \"issuePlace\" : \"" + placeGiveClient_ + "\",\n" +
                        "    \"issueAuthority\" : \"" + whoGiveClient_ + "\",\n" +
                        "    \"departmentcode\" : \"" + codeClient_ + "\",\n" +
                        "    \"snils\" : \"" + snilsClient_ + "\",\n" +
                        "    \"name\" : \"" + nameMain + "\"\n" +
                        "  },\n" +
                        "  \"credit\" : {\n" +
                        "    \"contractRef\" : \"none\",\n" +
                        "    \"primaryKey\" : \"none\",\n" +
                        "    \"product\" : \"none\",\n" +
                        "    \"issueDate\" : \"2020-01-01\",\n" +
                        "    \"annuity\" : \"none\",\n" +
                        "    \"comment\" : \"2020-01-01\",\n" +
                        "    \"index\" : \"none\",\n" +
                        "    \"currency\" : \"none\",\n" +
                        "    \"type\" : \"none\"\n" +
                        "  },\n" +
                        "  \"declined\" : {\n" +
                        "    \"lastDate\" : \"2020-01-01\"\n" +
                        "  },\n" +
                        "  \"employess\" : {\n" +
                        "    \"name\" : \"none\",\n" +
                        "    \"surname\" : \"none\",\n" +
                        "    \"rang\" : \"none\"\n" +
                        "  },\n" +
                        "  \"income\" : {\n" +
                        "    \"paymentManner\" : \"none\",\n" +
                        "    \"average\" : \"" + average_ + "\",\n" +
                        "    \"confirmation\" : \"none\",\n" +
                        "    \"periodicity\" : \"" + per_ + "\",\n" +
                        "    \"main\" : \"none\",\n" +
                        "    \"currency\" : \"" + val_ + "\",\n" +
                        "    \"type\" : \"none\",\n" +
                        "    \"value\" : \"none\"\n" +
                        "  },\n" +
                        "  \"loan\" : {\n" +
                        "    \"outstanding\" : 0,\n" +
                        "    \"paidInterest\" : 0,\n" +
                        "    \"pledgeValue\" : " + month_ + ",\n" +
                        "    \"dateFunded\" : \"2020-01-01\",\n" +
                        "    \"dateRealClose\" : \"2020-01-01\",\n" +
                        "    \"initialAmount\" : 0,\n" +
                        "    \"annuity\" : 0,\n" +
                        "    \"type\" : \"" + close_ +"\",\n" +
                        "    \"status\" : \"none\"\n" +
                        "  },\n" +
                        "  \"productDeclined\" : {\n" +
                        "    \"number\" : 0,\n" +
                        "    \"type\" : \"none\"\n" +
                        "  },\n" +
                        "  \"productParameters\" : {\n" +
                        "    \"downPaymentSource\" : \"none\",\n" +
                        "    \"purpose\" : \"" + source_ + "\",\n" +
                        "    \"disbursementDate\" : \"2020-01-01\",\n" +
                        "    \"repaymentScheme\" : \"none\",\n" +
                        "    \"firstPaymentDate\" : \"" + first_ + "\",\n" +
                        "    \"repaymentDate\" : \"2020-01-01\",\n" +
                        "    \"delayPeriod\" : 0,\n" +
                        "    \"productCategory\" : \"" + category_ + "\",\n" +
                        "    \"productName\" : \"" + name_ + "\",\n" +
                        "    \"refSum\" : " + sum_ + "\n" +
                        "  }\n" +
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