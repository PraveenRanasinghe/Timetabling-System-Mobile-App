package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Adapters.ViewStudentsAdapter;
import com.example.my_timetable.Model.DtoUser;
import com.example.my_timetable.Model.UDto;
import com.example.my_timetable.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class My_account extends AppCompatActivity {

    EditText userEmail;
    EditText fName;
    EditText lName;
    EditText batchId;
    EditText contactNumber;
    Button updateBtn;
    EditText password;


    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Call<DtoUser> getUser = RetrofitAPI.getRetrofit().create(ApiCalls.class).getMyAccount(jwt);

        getUser.enqueue(new Callback<DtoUser>() {
            @Override
            public void onResponse(Call<DtoUser> call, Response<DtoUser> response) {
                if(response.isSuccessful()){
                    DtoUser user=response.body();
                    userEmail.setText(user.getEmail());
                    fName.setText(user.getfName());
                    lName.setText(user.getlName());
                    contactNumber.setText(user.getContactNumber());
                    batchId.setText(user.getBatch().getBatchID());
                }
            }

            @Override
            public void onFailure(Call<DtoUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });

    }

    public void updateAccount(View view){
        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        DtoUser user = new DtoUser();
        user.setfName(fName.getText().toString());
        user.setlName(lName.getText().toString());
        user.setContactNumber(contactNumber.getText().toString());
        user.setPassword(password.getText().toString());

        Call<DtoUser> getUser = RetrofitAPI.getRetrofit().create(ApiCalls.class).updateStudentAccount(jwt,user);
        getUser.enqueue(new Callback<DtoUser>() {
            @Override
            public void onResponse(Call<DtoUser> call, Response<DtoUser> response) {
                Toast.makeText(getApplicationContext(),"Account Details has been Updated Successfully !", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DtoUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed!.Cannot Update the Account! "+t, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        userEmail=findViewById(R.id.UserEmail);
        fName=findViewById(R.id.uFName);
        lName=findViewById(R.id.uLName);
        batchId=findViewById(R.id.uBatchId);
        contactNumber=findViewById(R.id.uContactNum);
        updateBtn=findViewById(R.id.updateBtnStudent);
        password=findViewById(R.id.newPassword);

    }
}