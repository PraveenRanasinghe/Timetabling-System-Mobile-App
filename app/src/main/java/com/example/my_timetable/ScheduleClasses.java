package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.ClassDTO;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.Timetable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleClasses extends AppCompatActivity{

    Button dateButton, startTime,endTimeBtn;
    TextView scheduleDate, startTimeTV,endTimeTV;
    Spinner clzId;

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        RetrofitAPI retrofit = new RetrofitAPI();
        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);

        Call <List<ClassDTO>> jwtResponseBatchList=apiCalls.getAllClassesToList(jwt);


        jwtResponseBatchList.enqueue(new Callback<List<ClassDTO>>() {
            @Override
            public void onResponse(Call<List<ClassDTO>> call, Response<List<ClassDTO>> response) {
                if(response.isSuccessful()){
                    List<ClassDTO> classDTOS= response.body();
                    Spinner spinner = (Spinner) findViewById(R.id.classRoomIdSpinner);

                    ArrayAdapter<ClassDTO> adapter = new ArrayAdapter<ClassDTO>(ScheduleClasses.this,
                            android.R.layout.simple_spinner_item,classDTOS);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ClassDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Operation Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_classes);

        dateButton=findViewById(R.id.selectDate);
        startTime=findViewById(R.id.time);
        endTimeBtn=findViewById(R.id.selectDateButton);

        scheduleDate=findViewById(R.id.scheduledDate);
        startTimeTV=findViewById(R.id.startT);
        endTimeTV=findViewById(R.id.endTimeT);
        clzId=findViewById(R.id.classRoomIdSpinner);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDate();
            }
        });
        
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTime();
            }
        });

        endTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleEndTime();
            }
        });
    }

    private void handleTime() {
        Calendar calendar = Calendar.getInstance();
        int Hour = calendar.get(Calendar.HOUR);
        int Min=calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time=hourOfDay+":"+minute;
                startTimeTV.setText(time);

            }
        },Hour,Min,true);
        timePickerDialog.show();
    }

    private void handleEndTime() {
        Calendar calendar = Calendar.getInstance();
        int Hour = calendar.get(Calendar.HOUR);
        int Min=calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time=hourOfDay+":"+minute;
                endTimeTV.setText(time);
            }
        },Hour,Min,true);
        timePickerDialog.show();

    }

    private void handleDate() {
        Calendar calendar = Calendar.getInstance();
        int Y=calendar.get(Calendar.YEAR);
        int M=calendar.get(Calendar.MONTH);
        int D=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date=year+"-"+month+"-"+dayOfMonth;
                scheduleDate.setText(date);
            }
        }, Y,M,D);
        datePickerDialog.show();
     }


    public void scheduleClass(View view) throws ParseException {

        final String scheduledDate=((TextView)findViewById(R.id.scheduledDate)).getText().toString().trim();
        final String startTime=((TextView)findViewById(R.id.startTime)).getText().toString().trim();
        final String endTime=((TextView)findViewById(R.id.endTime)).getText().toString().trim();
        final String classroom=((Spinner)findViewById(R.id.classRoomIdSpinner)).toString().trim();

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


        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Timetable timetable = new Timetable();
        RetrofitAPI retrofit = new RetrofitAPI();

        String sDate=scheduleDate.getText().toString();
        Date date = new SimpleDateFormat().parse(sDate);

        timetable.setStartTime(startTimeTV.getText().toString());
        timetable.setEndTime(endTimeTV.getText().toString());
        timetable.setScheduledDate(date);


        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<Timetable> jwtResponseCall = apiCalls.scheduleClasses(jwt,timetable);

        jwtResponseCall.enqueue(new Callback<Timetable>() {
            @Override
            public void onResponse(Call<Timetable> call, Response<Timetable> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Timetable has been Scheduled Successfully! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Operation Failed ! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Timetable> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Operation Failed! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}