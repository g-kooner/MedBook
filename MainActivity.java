package com.example.kooner_medbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
        android:text="TYLENOL\n dose: 23 unit: 100mg daily frequency: 2"
        field variables
        global in scope once declared here
        list view variable that corresponds to the UI
        array adapter variable for binding arraylist to list view
        editText variables for user entry
        String vars for selected elements of list view
        result codes are for multiple intents to switch between activities

        inside oncreate initialize corresponding list variables

         */
    ListView medList;
    ArrayAdapter<String> medAdapter;
    ArrayList<String> dataList;
    ArrayList<Medicine> medObjList = new ArrayList<>();
    EditText confirmMed;
    String selectedMed;
    int position;
    private final int REQUEST_CODE1 = 2;
    private final int REQUEST_CODE2 = 3;
    private int totalDose = 0;
    TextView doseTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medList = findViewById(R.id.med_list);
        doseTotal = findViewById(R.id.total_doses);
        //populate array with inital medicine
        //Medicine Tylenol = new Medicine("Tylenol","2021-08-18", 1, "mg", 8 );
        //String strTylenol = Tylenol.strRepr();
        //String[] stringMeds = {strTylenol};

        dataList = new ArrayList<>();
        //dataList.addAll(Arrays.asList(stringMeds));

        medAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        medList.setAdapter(medAdapter);

        //click handler for listview that will allow to handle deletion
        medList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get the clicked item in the list view
                position = i;
                selectedMed = (String) adapterView.getItemAtPosition(i);
            }
        });


    }

    /*
    on click methods for button menu to add , delete, view , edit the medicine list
     */
    public void addMed(View view) {
        /*
        Switch to another activity that will have edit text fields for info input of medicine
        Button to transfer back the data and display it on mainactivity list view UI
         */
        Intent intent = new Intent(MainActivity.this, addInfo.class);
        intent.putExtra("resultCode", REQUEST_CODE1);
        startActivityForResult(intent, REQUEST_CODE1);
    }

    public void delMed(View view) {
        /*
        select med from list view and then click delete button to remove it
         */
        medAdapter.remove(selectedMed);
        medAdapter.notifyDataSetChanged();
        Medicine deletedMed = medObjList.get(position);
        medObjList.remove(position);
        int doseDeleted =  deletedMed.getDose();
        calcDoseAmt();
    }

    public void editMed(View view) {
        /*
        switch to edit activity with all old data pre-written into editText
        user can change input in editText
        confirm to update at that position array[position] = updated str repr data
        switch back to list view
         */
        Medicine selectedMedObj = medObjList.get(position);
        String selectedDate = selectedMedObj.getStartDate();
        String selectedName = selectedMedObj.getName();
        int selectedDoseAmt = selectedMedObj.getDose();
        String selectedUnit = selectedMedObj.getDoseUnit();
        int selectedFreq = selectedMedObj.getDailyFrequency();

        Intent intent = new Intent(MainActivity.this, editInfo.class);
        intent.putExtra("resultCode2", REQUEST_CODE2);
        intent.putExtra("date", selectedDate);
        intent.putExtra("name", selectedName);
        intent.putExtra("doseAmt", selectedDoseAmt);
        intent.putExtra("unit", selectedUnit);
        intent.putExtra("freq", selectedFreq);
        startActivityForResult(intent, REQUEST_CODE2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //handle data back from addinfo activity
        if(requestCode == REQUEST_CODE1){
            Log.d("RESULT", "onActivityResult: back to main rcv result from add info");

            String date = data.getStringExtra("date");
            String name = data.getStringExtra("name");
            int doseAmt = data.getIntExtra("doseAmt", 1);
            String unit = data.getStringExtra("unit");
            int freq = data.getIntExtra("freq", 1);

            Log.d("RESULT", "onActivityResult: after rcv data");
            Log.d("RESULT", date + name + String.valueOf(doseAmt) + unit + String.valueOf(freq));

            Medicine addMed = new Medicine(name,date,doseAmt,unit,freq);
            Log.d("RESULT", "onActivityResult: after object creation");
            String repraddMed = addMed.strRepr();
            medObjList.add(addMed);

            dataList.add(repraddMed);
            medAdapter.notifyDataSetChanged();
            calcDoseAmt();

            Log.d("RESULT", "onActivityResult: after changing list");
        }

        //handle data back from view / edit
        if(requestCode == REQUEST_CODE2){
            String date = data.getStringExtra("date");
            String name = data.getStringExtra("name");
            int doseAmt = data.getIntExtra("doseAmt", 1);
            String unit = data.getStringExtra("unit");
            int freq = data.getIntExtra("freq", 1);

            Medicine editMed = new Medicine(name,date,doseAmt,unit,freq);
            String repreditMed = editMed.strRepr();

            //replace old obj and list data with new edit changes
            medObjList.set(position, editMed);
            dataList.set(position, repreditMed);
            medAdapter.notifyDataSetChanged();
            calcDoseAmt();
        }
    }

    private void calcDoseAmt() {
        int totalDose = 0;
        for (int counter = 0; counter < medObjList.size(); counter++) {
            totalDose = totalDose + medObjList.get(counter).getDailyFrequency();
        }
        doseTotal.setText("Total Doses: " + String.valueOf(totalDose));
    }
}