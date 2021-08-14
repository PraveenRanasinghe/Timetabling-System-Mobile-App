package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.Model.Timetable;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class lecturer_timetable extends AppCompatActivity {

    private CardView cardView;
    private TextView batchId;
    private TextView scheduledDate;
    private TextView startTime;
    private TextView endTime;
    private TextView classRoomId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_timetable);

        cardView = findViewById(R.id.lecCardView);
        batchId = findViewById(R.id.batchId);
        scheduledDate = findViewById(R.id.date);
        startTime=findViewById(R.id.startTime);
        endTime=findViewById(R.id.endTime);
        classRoomId=findViewById(R.id.classRoomId);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        Call<List<Timetable>> listCall = apiCalls.getTodayTimetableToLecturer();
        listCall.enqueue(new Callback<List<Timetable>>() {
            @Override
            public void onResponse(Call<List<Timetable>> call, Response<List<Timetable>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Operation Failed !", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Timetable> timetables = response.body();

                for(Timetable timetable:timetables){
                    batchId.setText(timetable.getBatches().toString());
                    scheduledDate.setText(timetable.getScheduledDate().toString());
                    startTime.setText(timetable.getStartTime().toString());
                    endTime.setText(timetable.getEndTime().toString());
                    classRoomId.setText(timetable.getClassRoom().getClassRoomID());
                }
            }

            @Override
            public void onFailure(Call<List<Timetable>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed! "+t, Toast.LENGTH_SHORT).show();
            }
        });


    }
}