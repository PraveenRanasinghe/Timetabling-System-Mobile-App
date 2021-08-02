package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add_student extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        drawerLayout=findViewById(R.id.drawerLayout);
    }

    public void clickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }



    public void ClickHome(View view){
         redirectToActivity(add_student.this,Admin.class);
    }

    public static void redirectToActivity(Activity activity, Class aClass){
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void addStudents(View view){
        final String studEmail=((EditText)findViewById(R.id.studEmail)).getText().toString().trim();
        final String fName=((EditText)findViewById(R.id.fName)).getText().toString().trim();
        final String lName=((EditText)findViewById(R.id.lName)).getText().toString().trim();
        final String batchId=((EditText)findViewById(R.id.batchId)).getText().toString().trim();
        final String contactNum=((EditText)findViewById(R.id.contactNum)).getText().toString().trim();

        if(studEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Student Email", Toast.LENGTH_SHORT).show();
        }
        else if(fName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the First Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(lName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Last Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(batchId.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Batch Id Field.", Toast.LENGTH_SHORT).show();
        }
        else if(contactNum.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Contact Number.", Toast.LENGTH_SHORT).show();
        }

    }
}