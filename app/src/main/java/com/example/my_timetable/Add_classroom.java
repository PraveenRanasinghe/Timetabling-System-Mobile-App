package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_classroom extends AppCompatActivity {

    private Spinner spinner, spinner1;
    EditText classroomId;
    EditText capa;
    Intent nextPath;
    String Ac;
    String Sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_classroom.this, Admin.class);
                startActivity(intent);
            }
        });

        spinner = findViewById(R.id.spinnerAc);
        spinner1= findViewById(R.id.spinnerSMB);
        classroomId=findViewById(R.id.classRoomId);
        capa=findViewById(R.id.capacity);

        String[] items={"Yes","No"};
        String[] items1={"Yes","No"};


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,items);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ac=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,items1);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sb=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addClassRoom(View view) {
        final String classId = ((EditText) findViewById(R.id.classRoomId)).getText().toString().trim();
        final String capacity = ((EditText) findViewById(R.id.capacity)).getText().toString().trim();
        final String ac = (findViewById(R.id.spinnerAc)).toString().trim();
        final String smartBoard = (findViewById(R.id.spinnerSMB).toString().trim());

        if (classId.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill the ClassRoom Id Field.", Toast.LENGTH_SHORT).show();
        } else if (capacity.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter the Capacity of the ClassRoom", Toast.LENGTH_SHORT).show();
        } else if (ac.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter the Air Condition Status of the ClassRoom", Toast.LENGTH_SHORT).show();
        } else if (smartBoard.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter the SmartBoard Status of the ClassRoom", Toast.LENGTH_SHORT).show();
        } else{

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Classroom classroom = new Classroom();
        RetrofitAPI retrofit = new RetrofitAPI();
        classroom.setClassRoomID(classroomId.getText().toString());
        classroom.setAc(Ac);
        classroom.setSmartBoard(Sb);
        classroom.setCapacity(capa.getText().toString());


        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<Classroom> jwtResponseCall = apiCalls.addClassroom(jwt, classroom);

        nextPath = new Intent(Add_classroom.this, Admin_classroom_operations.class);
        jwtResponseCall.enqueue(new Callback<Classroom>() {
            @Override
            public void onResponse(Call<Classroom> call, Response<Classroom> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "New Classroom has been added Successfully! ", Toast.LENGTH_SHORT).show();
                    startActivity(nextPath);
                } else {
                    Toast.makeText(getApplicationContext(), "Operation Failed ! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Classroom> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Operation Failed! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
}