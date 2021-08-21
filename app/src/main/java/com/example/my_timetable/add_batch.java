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
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_batch extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText batchId;
    EditText batchName;
    TextView commencementDate;
    TextView terminationDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);

        batchId=findViewById(R.id.batchId);
        batchName=findViewById(R.id.batchName);
        commencementDate=findViewById(R.id.commencementDate);
        terminationDate=findViewById(R.id.terminationDate);

        Button button1 = (Button)findViewById(R.id.selectDate);
        Button button2 = (Button)findViewById(R.id.selectDate2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.my_timetable.DatePicker();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePicker2();
                datePicker.show(getSupportFragmentManager(),"Date Picker 2");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR,year);
        calendar2.set(Calendar.MONTH,month);
        calendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentDate = DateFormat.getInstance().format(calendar.getTime());
        String currentDate2 = DateFormat.getInstance().format(calendar2.getTime());

        TextView textView = (TextView) findViewById(R.id.commencementDate);
        textView.setText(currentDate);

        TextView textView1 = (TextView) findViewById(R.id.terminationDate);
        textView1.setText(currentDate2);

    }


    public void createBatch(View view){
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