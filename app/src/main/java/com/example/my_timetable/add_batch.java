package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_batch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);
    }


    public void createBatch(View view){
        final String batchId=((EditText)findViewById(R.id.batchId)).getText().toString().trim();
        final String batchName=((EditText)findViewById(R.id.batchName)).getText().toString().trim();
        final String dateOfCommencement=((Button)findViewById(R.id.dateOfCommencement)).getText().toString().trim();
        final String dateOfTermination=((Button)findViewById(R.id.dateOfTermination)).getText().toString().trim();

        if(batchId.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Batch Id Field.", Toast.LENGTH_SHORT).show();
        }
        else if(batchName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Batch Name.", Toast.LENGTH_SHORT).show();
        }
        else if(dateOfCommencement.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Date of Commencement.", Toast.LENGTH_SHORT).show();
        }
        else if(dateOfTermination.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Date of Termination.", Toast.LENGTH_SHORT).show();
        }

    }
}