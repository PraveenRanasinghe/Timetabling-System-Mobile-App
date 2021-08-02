package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_lecturer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecturer);
    }

    public void addLecturers(View view){
        final String lecEmail=((EditText)findViewById(R.id.lecEmail)).getText().toString().trim();
        final String fName=((EditText)findViewById(R.id.fName)).getText().toString().trim();
        final String lName=((EditText)findViewById(R.id.lName)).getText().toString().trim();
        final String contactNum=((EditText)findViewById(R.id.contactNum)).getText().toString().trim();

        if(lecEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Lecturer Email", Toast.LENGTH_SHORT).show();
        }
        else if(fName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the First Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(lName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Last Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(contactNum.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Contact Number.", Toast.LENGTH_SHORT).show();
        }

    }
}