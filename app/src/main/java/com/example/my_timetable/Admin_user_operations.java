package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin_user_operations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_operations);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_user_operations.this, Admin.class);
                startActivity(intent);
            }
        });
    }

    public void addNewStudent(View view){
        startActivity(new Intent(Admin_user_operations.this, Add_student.class));
    }

    public void addLecturer(View view){
        startActivity(new Intent(Admin_user_operations.this, Add_lecturer.class));
    }

    public void studentOperations(View view){
        startActivity(new Intent(Admin_user_operations.this, View_all_students.class));
    }

    public void lecturerOperations(View view){
        startActivity(new Intent(Admin_user_operations.this, View_all_lecturers.class));
    }
}