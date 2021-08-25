package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_batch extends AppCompatActivity{

    EditText batchID;
    EditText BatchName;
    TextView commencementDate;
    TextView terminationDate;
    Button commencement;
    Button termination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);

        batchID=findViewById(R.id.batchId);
        BatchName=findViewById(R.id.batchName);
        commencementDate=findViewById(R.id.commencementDate);
        terminationDate=findViewById(R.id.terminationDate);

        commencement=findViewById(R.id.selectDate);
        termination=findViewById(R.id.selectDate2);

        commencement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCommencementDate();
            }
        });

        termination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTerminationDate();
            }
        });

    }

    private void handleCommencementDate() {
        Calendar calendar = Calendar.getInstance();
        int Y=calendar.get(Calendar.YEAR);
        int M=calendar.get(Calendar.MONTH);
        int D=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date=dayOfMonth+"/"+month+"/"+year;
                commencementDate.setText(date);
            }
        }, Y,M,D);
        datePickerDialog.show();
    }

    private void handleTerminationDate() {
        Calendar calendar = Calendar.getInstance();
        int Y=calendar.get(Calendar.YEAR);
        int M=calendar.get(Calendar.MONTH);
        int D=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date=dayOfMonth+"/"+month+"/"+year;
                terminationDate.setText(date);
            }
        }, Y,M,D);
        datePickerDialog.show();
    }

    public void createBatch(View view) throws ParseException {
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

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Batch batch = new Batch();
        RetrofitAPI retrofit = new RetrofitAPI();


        String cDate=commencementDate.getText().toString();
        String tDate=terminationDate.getText().toString();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(cDate);
        Date ter = new SimpleDateFormat("dd/MM/yyyy").parse(tDate);

        batch.setBatchID(batchID.getText().toString());
        batch.setBatchName(BatchName.getText().toString());
        batch.setStartDate(date);
        batch.setEndDate(ter);

        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<Batch> jwtResponseCall = apiCalls.addBatch(jwt,batch);

        jwtResponseCall.enqueue(new Callback<Batch>() {
            @Override
            public void onResponse(Call<Batch> call, Response<Batch> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "New Batch has been Added to the University Successfully! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Operation Failed Response! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Batch> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Operation Failed! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}