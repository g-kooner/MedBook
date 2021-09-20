package com.example.medbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
    EditText confirmMed;
    String selectedMed;
    private final int REQUEST_CODE1 = 2;
    private final int REQUEST_CODE2 = 3;
    private final int REQUEST_CODE3 = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medList = findViewById(R.id.med_list);
        //populate array with inital medicine
        Medicine Tylenol = new Medicine("Tylenol","2021-08-18", 1, "mg", 8 );
        String strTylenol = Tylenol.strRepr();
        String[] stringMeds = {strTylenol};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(stringMeds));

        medAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        medList.setAdapter(medAdapter);

        //click handler for listview that will allow to handle deletion
        medList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get the clicked item in the list view
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
        Intent intent = new Intent(MainActivity.this, AddInfo.class);
        intent.putExtra("resultCode", REQUEST_CODE1);
        startActivityForResult(intent, REQUEST_CODE1);
    }

    public void delMed(View view) {
        /*
        select med from list view and then click delete button to remove it
         */
        medAdapter.remove(selectedMed);
        medAdapter.notifyDataSetChanged();
    }

    public void viewMed(View view) {
        /*
        switch to view activity with all the data shown
        finish view button to switch back
         */

    }

    public void editMed(View view) {
        /*
        switch to edit activity with all old data pre-written into editText
        user can change input in editText
        confirm to update at that position array[position] = updated str repr data
        switch back to list view
         */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //handle data back from addinfo activity
        if(requestCode == REQUEST_CODE1){
            //add str repr to list view
            String addInfo = data.getStringExtra("addMedicine");
            dataList.add(addInfo);
            medAdapter.notifyDataSetChanged();
        }

        //handle data back from view
    }
}