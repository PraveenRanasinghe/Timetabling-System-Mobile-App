package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add_module extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);
    }

    public void addNewModule(View view){
        final String moduleId=((EditText)findViewById(R.id.moduleId)).getText().toString().trim();
        final String moduleName=((EditText)findViewById(R.id.moduleName)).getText().toString().trim();
        final String lecName=((EditText)findViewById(R.id.lecName)).getText().toString().trim();
        final String batchName=((EditText)findViewById(R.id.batch)).getText().toString().trim();

        if(moduleId.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Module Id", Toast.LENGTH_SHORT).show();
        }
        else if(moduleName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Module Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(lecName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Lecturer Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(batchName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Batch Name.", Toast.LENGTH_SHORT).show();
        }

    }
}