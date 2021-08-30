package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.ClassDTO;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.Module;
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.Model.TimetableDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReScheduleClasses extends AppCompatActivity {


    Button dateButton, startTime,endTimeBtn;
    TextView scheduleDate, startTimeTV,endTimeTV;
    Spinner clzId;
    Intent nextPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_schedule_classes);

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
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                scheduleDate.setText(date);
            }
        }, Y,M,D);
        datePickerDialog.show();
    }


    public void reScheduleClass(View view) throws ParseException {

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Timetable timetable = new Timetable();
        RetrofitAPI retrofit = new RetrofitAPI();

        String sDate=scheduleDate.getText().toString();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

        Intent intent = getIntent();

        TimetableDTO timetableDTO = intent.getParcelableExtra("timetableData");

        timetable.setStartTime(startTimeTV.getText().toString());
        timetable.setEndTime(endTimeTV.getText().toString());
        timetable.setScheduledDate(date);
        timetable.setClassRoom(timetableDTO.getClassRoom());

        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<Timetable> jwtResponseCall = apiCalls.rescheduleClasses(jwt,timetable);

        nextPath=new Intent(ReScheduleClasses.this, ViewAllScheduledClasses.class);

        jwtResponseCall.enqueue(new Callback<Timetable>() {
            @Override
            public void onResponse(Call<Timetable> call, Response<Timetable> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Timetable has been Re-Scheduled Successfully! ", Toast.LENGTH_SHORT).show();
                    startActivity(nextPath);
                }
            }

            @Override
            public void onFailure(Call<Timetable> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Process Failed!.Please Try Again! ", Toast.LENGTH_SHORT).show();
                startActivity(nextPath);
            }
        });
    }
}