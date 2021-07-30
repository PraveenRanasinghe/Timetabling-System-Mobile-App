package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void adminBatchOperations(View view){
        startActivity(new Intent(Admin.this,admin_batch_operations.class));
    }

    public void adminModuleOperations(View view){
        startActivity(new Intent(Admin.this,admin_module_operations.class));
    }

    public void adminClassroomOperations(View view){
        startActivity(new Intent(Admin.this,admin_classroom_operations.class));
    }

    public void adminUserOperations(View view){
        startActivity(new Intent(Admin.this,admin_user_operations.class));
    }
}