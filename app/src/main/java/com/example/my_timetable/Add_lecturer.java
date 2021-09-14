package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_lecturer extends AppCompatActivity {

    EditText userType;
    EditText lecFName;
    EditText lecLName;
    EditText lecturerEmail;
    EditText lecContact;
    Intent nextPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecturer);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_lecturer.this, Admin.class);
                startActivity(intent);
            }
        });

        userType=findViewById(R.id.userType);
        lecFName=findViewById(R.id.fName);
        lecLName=findViewById(R.id.lName);
        lecturerEmail=findViewById(R.id.lecturerEmail);
        lecContact=findViewById(R.id.lecContactNum);
    }

    public void addLecturers(View view){
        final String lecEmail=((EditText)findViewById(R.id.lecturerEmail)).getText().toString().trim();
        final String fName=((EditText)findViewById(R.id.fName)).getText().toString().trim();
        final String lName=((EditText)findViewById(R.id.lName)).getText().toString().trim();
        final String contactNum=((EditText)findViewById(R.id.lecContactNum)).getText().toString().trim();

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
        else {

            SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
            String name = prefs.getString("token", null);
            String jwt = "Bearer " + name;

            User user = new User();
            RetrofitAPI retrofit = new RetrofitAPI();
            user.setfName(lecFName.getText().toString());
            user.setlName(lecLName.getText().toString());
            user.setPassword("Lecturer1234");
            user.setUserRole(userType.getText().toString());
            user.setEmail(lecturerEmail.getText().toString());
            user.setContactNumber(lecContact.getText().toString());

            ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
            Call<User> jwtResponseCall = apiCalls.addLecturers(jwt, user);
            nextPath = new Intent(Add_lecturer.this, Admin_user_operations.class);

            jwtResponseCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "New Lecturer has been Added to the University Successfully! ", Toast.LENGTH_SHORT).show();
                        startActivity(nextPath);
                    } else {
                        Toast.makeText(getApplicationContext(), "Operation Failed. Cannot Add the Lecturer. Please Check the given details!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Operation Failed! ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}