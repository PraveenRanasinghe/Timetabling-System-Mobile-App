package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_user_operations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_operations);
    }

    public void addStudent(View view){
        startActivity(new Intent(admin_user_operations.this,add_student.class));
    }

    public void addLecturer(View view){
        startActivity(new Intent(admin_user_operations.this,add_lecturer.class));
    }
}