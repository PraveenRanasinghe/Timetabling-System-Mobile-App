package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class add_batch extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);

        Button button1 = (Button)findViewById(R.id.selectDate);
        Button button2 = (Button)findViewById(R.id.selectDate2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.my_timetable.DatePicker();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePicker2();
                datePicker.show(getSupportFragmentManager(),"Date Picker 2");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR,year);
        calendar2.set(Calendar.MONTH,month);
        calendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentDate = DateFormat.getInstance().format(calendar.getTime());
        String currentDate2 = DateFormat.getInstance().format(calendar2.getTime());

        TextView textView = (TextView) findViewById(R.id.commencementDate);
        textView.setText(currentDate);

        TextView textView1 = (TextView) findViewById(R.id.terminationDate);
        textView1.setText(currentDate2);

    }


    public void createBatch(View view){
        final String batchId=((EditText)findViewById(R.id.batchId)).getText().toString().trim();
        final String batchName=((EditText)findViewById(R.id.batchName)).getText().toString().trim();
        final String dateOfCommencement=((TextView)findViewById(R.id.commencementDate)).getText().toString().trim();
        final String dateOfTermination=((TextView)findViewById(R.id.terminationDate)).getText().toString().trim();

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