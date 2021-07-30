package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class add_classroom extends AppCompatActivity {

    private Spinner spinner, spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);

        spinner = findViewById(R.id.spinner);
        spinner1= findViewById(R.id.spinner1);

        String[] items={"Yes","No"};
        String[] items1={"Yes","No"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,items);
        spinner.setAdapter(arrayAdapter);

        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,items1);
        spinner1.setAdapter(arrayAdapter1);
    }
}