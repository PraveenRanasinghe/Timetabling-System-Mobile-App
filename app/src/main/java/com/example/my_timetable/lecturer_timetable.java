package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

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



    }
}