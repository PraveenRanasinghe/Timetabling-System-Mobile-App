package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.JwtResponse;
import com.example.my_timetable.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_student extends AppCompatActivity {

    EditText userType;
    EditText studFName;
    EditText studLName;
    EditText studEmail;
    EditText studContact;
    EditText studBatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        userType= findViewById(R.id.uType);
        studFName= findViewById(R.id.studFName);
        studLName=findViewById(R.id.studLName);
        studEmail=findViewById(R.id.newStudEmail);
        studContact=findViewById(R.id.ScontactNum);
        studBatch=findViewById(R.id.SbatchId);

    }



    public void addStudents(View view){

        User user = new User();
        RetrofitAPI retrofit = new RetrofitAPI();
        user.setfName(studFName.getText().toString());
        user.setlName(studLName.getText().toString());
        user.setPassword("User1234");
        user.setUserRole(userType.getText().toString());
        user.setEmail(studEmail.getText().toString());
//        user.setBatchId((Batch)studBatch.getText());
        user.setContactNumber(studContact.getText().toString());

        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<User> jwtResponseCall = apiCalls.addStudents(user);

        jwtResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "New Student has been Added to the University Successfully! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Operation Successful! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        final String studEmail=((EditText)findViewById(R.id.newStudEmail)).getText().toString().trim();
        final String fName=((EditText)findViewById(R.id.studFName)).getText().toString().trim();
        final String lName=((EditText)findViewById(R.id.studLName)).getText().toString().trim();
//        final String batchId=((EditText)findViewById(R.id.SbatchId)).getText().toString().trim();
        final String contactNum=((EditText)findViewById(R.id.ScontactNum)).getText().toString().trim();

        if(studEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Student Email", Toast.LENGTH_SHORT).show();
        }
        else if(fName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the First Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(lName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Last Name field.", Toast.LENGTH_SHORT).show();
        }
//        else if(batchId.isEmpty()){
//            Toast.makeText(getApplicationContext(), "Please fill the Batch Id Field.", Toast.LENGTH_SHORT).show();
//        }
        else if(contactNum.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Contact Number.", Toast.LENGTH_SHORT).show();
        }

    }
}