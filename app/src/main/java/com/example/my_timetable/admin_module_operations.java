package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_module_operations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_module_operations);
    }

    public void addModule(View view){
        startActivity(new Intent(admin_module_operations.this,add_module.class));
    }

    public void viewModules(View view){
        startActivity(new Intent(admin_module_operations.this,view_all_modules.class));
    }
}