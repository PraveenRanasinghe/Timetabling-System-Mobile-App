package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    public void addClassRoom(View view){
        final String classRoomId=((EditText)findViewById(R.id.classRoomId)).getText().toString().trim();
        final String capacity=((EditText)findViewById(R.id.capacity)).getText().toString().trim();
        final String  ac=((Spinner)findViewById(R.id.spinner)).toString().trim();
        final String smartBoard=((Spinner)findViewById(R.id.spinner1)).toString().trim();

        if(classRoomId.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the ClassRoom Id Field.", Toast.LENGTH_SHORT).show();
        }
        else if(capacity.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Capacity of the ClassRoom", Toast.LENGTH_SHORT).show();
        }
        else if(ac.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Air Condition Status of the ClassRoom", Toast.LENGTH_SHORT).show();
        }
        else if(smartBoard.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the SmartBoard Status of the ClassRoom", Toast.LENGTH_SHORT).show();
        }

    }
}