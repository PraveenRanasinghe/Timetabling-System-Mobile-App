package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_classroom_operations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_classroom_operations);
    }

    public void addClassroom(View view){
        startActivity(new Intent(admin_classroom_operations.this,add_classroom.class));
    }

    public void viewClassRooms(View view){
        startActivity(new Intent(admin_classroom_operations.this,ViewClassrooms.class));
    }
}