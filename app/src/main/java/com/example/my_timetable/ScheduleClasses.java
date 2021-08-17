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

public class ScheduleClasses extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_classes);

        Button button1 = (Button)findViewById(R.id.selectDate);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.my_timetable.DatePicker();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentDate = DateFormat.getInstance().format(calendar.getTime());

        TextView textView = (TextView) findViewById(R.id.scheduledDate);
        textView.setText(currentDate);
    }


    public void scheduleClass(View view){


        final String scheduledDate=((TextView)findViewById(R.id.scheduledDate)).getText().toString().trim();
        final String startTime=((TextView)findViewById(R.id.startTime)).getText().toString().trim();
        final String endTime=((TextView)findViewById(R.id.endTime)).getText().toString().trim();
        final String classroom=((EditText)findViewById(R.id.classRoomId)).getText().toString().trim();

        if(scheduledDate.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Select the Date Field.", Toast.LENGTH_SHORT).show();
        }
        else if(startTime.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Start Time Field.", Toast.LENGTH_SHORT).show();
        }
        else if(endTime.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the End Time Field.", Toast.LENGTH_SHORT).show();
        }
        else if(classroom.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please select a Class-Room.", Toast.LENGTH_SHORT).show();
        }

    }
}