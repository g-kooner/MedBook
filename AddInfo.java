package com.example.medbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddInfo extends AppCompatActivity {
    /*
    Receive the request code to keep track of activity switching
    create vars for the edit text input from which data will be sent back
    to the main activity

    Confirm button click listener will get text input
    call constructor to create a medicine object
    use string repr method to send back formatted data to display in list view UI
    switch back to the main activity
     */

    EditText startDate;
    EditText medName;
    EditText doseAmt;
    EditText doseUnit;
    EditText doseFreq;
    private int REQUESTCODEMAIN = 0;
    Button confirmAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        bind all field vars to their UI elems to retrieve their data
        use bundle to get all intent data

         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        startDate = findViewById(R.id.DateEditText);
        medName = findViewById(R.id.nameEditText);
        doseAmt = findViewById(R.id.doseamtEditText);
        doseUnit = findViewById(R.id.doseunitEditText);
        doseFreq = findViewById(R.id.dosefreqEditText);
        confirmAdd = findViewById(R.id.addInfoButton);


        Bundle extra = getIntent().getExtras();
        REQUESTCODEMAIN = extra.getInt("resultCode");
    }

    //onclick Confirm Button
    public void ConfirmMed(View view) {
        /*
        use info from edit text to create med obj
        use str repr to get string
        return string back to main activity

        String value = et.getText().toString();
        int finalValue=Integer.parseInt(value);
         */
        String date = startDate.getText().toString().trim();
        String name = medName.getText().toString().trim();
        String strDoseAmt = doseAmt.getText().toString().trim();
        int doseAmt = Integer.parseInt(strDoseAmt);
        String unit = doseUnit.getText().toString().trim();
        String strDoseFreq = doseFreq.getText().toString().trim();
        int freq = Integer.parseInt(strDoseFreq);

        Medicine addMed = new Medicine(name,date,doseAmt,unit,freq);
        String repraddMed = addMed.strRepr();

        Intent intent = getIntent();
        intent.putExtra("addMedicine", repraddMed);
        REQUESTCODEMAIN = 2;
        setResult(REQUESTCODEMAIN, intent);
        finish();

    }
}