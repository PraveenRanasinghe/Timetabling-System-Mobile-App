package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void adminHome(View view){
        startActivity(new Intent(MainActivity.this,Admin.class));
    }

    public void studentHome(View view){startActivity(new Intent(MainActivity.this, student.class));}
}