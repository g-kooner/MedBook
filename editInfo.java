package com.example.kooner_medbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editInfo extends AppCompatActivity {

    /*
        View and Edit Medicine
        set the editText field to info already in list
        user can change it by typing in new info
         */
    EditText startDate;
    EditText medName;
    EditText doseAmt;
    EditText doseUnit;
    EditText doseFreq;
    private int REQUESTCODEMAIN = 0;
    Button confirmEdit;
    private String nameEdit;
    private String startDateEdit;
    private int doseEdit;
    private String doseUnitEdit;
    private int dailyFrequencEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        startDate = findViewById(R.id.DateEditText);
        medName = findViewById(R.id.nameEditText);
        doseAmt = findViewById(R.id.doseamtEditText);
        doseUnit = findViewById(R.id.doseunitEditText);
        doseFreq = findViewById(R.id.dosefreqEditText);
        confirmEdit = findViewById(R.id.addInfoButton);

        /*
        get info passed in
        set edit text boxes to info passed in of the selected medicine
         */
        Bundle extra = getIntent().getExtras();
        REQUESTCODEMAIN = extra.getInt("resultCode");
        nameEdit = extra.getString("name");
        startDateEdit = extra.getString("date");
        doseEdit = extra.getInt("doseAmt");
        dailyFrequencEdit = extra.getInt("freq");
        doseUnitEdit = extra.getString("unit");

        //set text for edit txt based on info of the selected med to view all
        startDate.setText(startDateEdit);
        medName.setText(nameEdit);
        doseAmt.setText(String.valueOf(doseEdit));
        doseFreq.setText(String.valueOf(dailyFrequencEdit));
        doseUnit.setText(doseUnitEdit);
    }


    /*
    onclick method for button
     */
    public void confirmEdit(View view) {
        /*
        get info from edit text
         */
        String date = startDate.getText().toString().trim();
        String name = medName.getText().toString().trim();
        String strDoseAmt = doseAmt.getText().toString().trim();
        int doseAmt = Integer.parseInt(strDoseAmt);
        String unit = doseUnit.getText().toString().trim();
        String strDoseFreq = doseFreq.getText().toString().trim();
        int freq = Integer.parseInt(strDoseFreq);

        Intent intent = getIntent();
        //intent.putExtra("addMedicine", repraddMed);
        intent.putExtra("date", date);
        intent.putExtra("name", name);
        intent.putExtra("doseAmt", doseAmt);
        intent.putExtra("unit", unit);
        intent.putExtra("freq", freq);

        REQUESTCODEMAIN = 3;
        setResult(REQUESTCODEMAIN, intent);
        finish();

    }
}