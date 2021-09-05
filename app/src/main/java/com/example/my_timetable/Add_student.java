package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.JwtResponse;
import com.example.my_timetable.Model.UDto;
import com.example.my_timetable.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_student extends AppCompatActivity {

    EditText userType;
    EditText studFName;
    EditText studLName;
    EditText studEmail;
    EditText studContact;
    Spinner studBatch;
    String batch_Id;
    Intent nextPath;

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        RetrofitAPI retrofit = new RetrofitAPI();
        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);

        Call <List<Batch>> jwtResponseBatchList=apiCalls.getBatchesToList(jwt);


        jwtResponseBatchList.enqueue(new Callback<List<Batch>>() {
            @Override
            public void onResponse(Call<List<Batch>> call, Response<List<Batch>> response) {
                if(response.isSuccessful()){
                    List<Batch> batches= response.body();
                    Spinner spinner = (Spinner) findViewById(R.id.SpinnerBatchId);

                    ArrayAdapter<Batch> adapter = new ArrayAdapter<Batch>(Add_student.this,
                            android.R.layout.simple_spinner_item,batches);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            batch_Id=parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Batch>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Operation Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_student.this, Admin.class);
                startActivity(intent);
            }
        });

        userType= findViewById(R.id.uType);
        studFName= findViewById(R.id.studFName);
        studLName=findViewById(R.id.studLName);
        studEmail=findViewById(R.id.newStudEmail);
        studContact=findViewById(R.id.ScontactNum);
        studBatch=findViewById(R.id.SpinnerBatchId);

    }



    public void addStudents(View view){

        final String studentEmail=((EditText)findViewById(R.id.newStudEmail)).getText().toString().trim();
        final String fName=((EditText)findViewById(R.id.studFName)).getText().toString().trim();
        final String lName=((EditText)findViewById(R.id.studLName)).getText().toString().trim();
        final String batchId=((Spinner)findViewById(R.id.SpinnerBatchId)).toString().trim();
        final String contactNum=((EditText)findViewById(R.id.ScontactNum)).getText().toString().trim();

        if(studentEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Student Email", Toast.LENGTH_SHORT).show();
        }
        else if(fName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the First Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(lName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Last Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(batchId.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Batch Id Field.", Toast.LENGTH_SHORT).show();
        }
        else if(contactNum.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Contact Number.", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        User user = new User();
        RetrofitAPI retrofit = new RetrofitAPI();
        user.setfName(studFName.getText().toString());
        user.setlName(studLName.getText().toString());
        user.setPassword("User1234");
        user.setUserRole(userType.getText().toString());
        user.setEmail(studEmail.getText().toString());
        user.setBatchId(batch_Id);
        user.setContactNumber(studContact.getText().toString());

        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<User> jwtResponseCall = apiCalls.addStudents(jwt,user);
        nextPath=new Intent(Add_student.this,Admin_user_operations.class);
        jwtResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "New Student has been Added to the University Successfully! ", Toast.LENGTH_SHORT).show();
                    startActivity(nextPath);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Operation Failed Response! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Operation Failed! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}