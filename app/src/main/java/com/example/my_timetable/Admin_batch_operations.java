package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin_batch_operations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_batch_operations);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_batch_operations.this, Admin.class);
                startActivity(intent);
            }
        });
    }

    public void addBatch(View view){
        startActivity(new Intent(Admin_batch_operations.this, Add_batch.class));
    }

    public void viewBatches(View view){
        startActivity(new Intent(Admin_batch_operations.this,ViewBatches.class));
    }
}