package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin_module_operations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_module_operations);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_module_operations.this, Admin.class);
                startActivity(intent);
            }
        });
    }

    public void addModule(View view){
        startActivity(new Intent(Admin_module_operations.this, Add_module.class));
    }

    public void viewAllModules(View view){
        startActivity(new Intent(Admin_module_operations.this, View_all_modules.class));
    }

    public void viewAllScheduledClasses(View view){
        startActivity(new Intent(Admin_module_operations.this, ViewAllScheduledClasses.class));
    }
}