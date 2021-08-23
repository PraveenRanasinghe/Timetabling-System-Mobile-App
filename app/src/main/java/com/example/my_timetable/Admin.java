package com.example.my_timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        drawerLayout = findViewById(R.id.adminDrawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.o, R.string.c
        );

        toggle.syncState();

        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(Admin.this, Student.class);
                        startActivity(intent);
                        return true;

                    case R.id.myAccount:
                        Intent myAccountIntent = new Intent(Admin.this, My_account.class);
                        startActivity(myAccountIntent);
                        return true;

                    case R.id.myModules:
                        Intent myModules = new Intent(Admin.this, MyModulesStud.class);
                        startActivity(myModules);
                        return true;


                    case R.id.WeeklyTimetable:
                        Intent weeklyTimetable = new Intent(Admin.this,weekly_timetable_for_student.class);
                        startActivity(weeklyTimetable);
                        return true;

                }
                return false;
            }
        });
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