package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
                    DtoUser dto = new DtoUser();
                    dto.setEmail(userEmail.getText().toString());
//                    dto.setBatch(batchId.getText());
                    dto.setContactNumber(contactNumber.getText().toString());
                    dto.setfName(fName.getText().toString());
                    dto.setlName(lName.getText().toString());
                }
            }

            @Override
            public void onFailure(Call<DtoUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
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

    }
}