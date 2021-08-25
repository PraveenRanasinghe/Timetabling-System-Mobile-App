package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.DtoUser;
import com.example.my_timetable.Model.Module;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_module extends AppCompatActivity {


    EditText ModuleId;
    EditText ModuleName;
    EditText lecturer;
    EditText learningBatches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);

        ModuleId=findViewById(R.id.AMmoduleId);
        ModuleName=findViewById(R.id.AMmoduleName);
        lecturer=findViewById(R.id.AMlecName);
        learningBatches=findViewById(R.id.AMbatch);
    }

    public void addNewModule(View view){
        final String moduleId=((EditText)findViewById(R.id.AMmoduleId)).getText().toString().trim();
        final String moduleName=((EditText)findViewById(R.id.AMmoduleName)).getText().toString().trim();
        final String lecName=((EditText)findViewById(R.id.AMlecName)).getText().toString().trim();
        final String batchName=((EditText)findViewById(R.id.AMbatch)).getText().toString().trim();

        if(moduleId.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Module Id", Toast.LENGTH_SHORT).show();
        }
        else if(moduleName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill the Module Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(lecName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill Lecturer Name field.", Toast.LENGTH_SHORT).show();
        }
        else if(batchName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter the Batch Name.", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Module module=new Module();
        RetrofitAPI retrofit = new RetrofitAPI();

        String lec=lecturer.getText().toString();
        DtoUser user = new DtoUser();
        user.setEmail(lec);

        String batches=learningBatches.getText().toString();
        List<Batch> batchList=new ArrayList<>();


        module.setModuleID(ModuleId.getText().toString());
        module.setModuleName(ModuleName.getText().toString());
        module.setUser(user);
//        module.setBatches(learningBatches.getText());


        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<Module> jwtResponseCall = apiCalls.addModule(jwt,module);

        jwtResponseCall.enqueue(new Callback<Module>() {
            @Override
            public void onResponse(Call<Module> call, Response<Module> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "New Module has been added Successfully! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Operation Failed ! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Module> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Operation Failed! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}