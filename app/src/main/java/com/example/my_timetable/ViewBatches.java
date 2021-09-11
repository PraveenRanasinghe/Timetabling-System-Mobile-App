package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Adapters.ViewBatchesAdapter;
import com.example.my_timetable.Adapters.ViewLecturersAdapter;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBatches extends AppCompatActivity {

    private RecyclerView recyclerView;
    EditText searchingWord;
    Button searchButton;


    @Override
    protected void onStart() {
        super.onStart();

        recyclerView =findViewById(R.id.viewAllBatchesRecyclerView);
        searchingWord=findViewById(R.id.searchText);
        searchButton=findViewById(R.id.searchBtn);

        SharedPreferences prefs = getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        String name = prefs.getString("token", null);
        String jwt = "Bearer " + name;

        Call<List<Batch>> getBatches = RetrofitAPI.getRetrofit().create(ApiCalls.class).getAllBatches(jwt);

        getBatches.enqueue(new Callback<List<Batch>>() {
            @Override
            public void onResponse(Call<List<Batch>> call, Response<List<Batch>> response) {
                if(response.isSuccessful()){
                    List<Batch> batchList = response.body();
                    ViewBatchesAdapter adapter = new ViewBatchesAdapter(batchList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ViewBatches.this));
                }
            }

            @Override
            public void onFailure(Call<List<Batch>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                System.out.println(t);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Batch>> searchBatches = RetrofitAPI.getRetrofit().create(ApiCalls.class).searchBatches(jwt,searchingWord.getText().toString());

                searchBatches.enqueue(new Callback<List<Batch>>() {
                    @Override
                    public void onResponse(Call<List<Batch>> call, Response<List<Batch>> response) {
                        if(response.isSuccessful()){
                            List<Batch> batchList = response.body();
                            ViewBatchesAdapter adapter = new ViewBatchesAdapter(batchList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(ViewBatches.this));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Batch>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Operation Failed ! "+t, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_batches);

        FloatingActionButton fab=findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBatches.this, Admin.class);
                startActivity(intent);
            }
        });


    }
}