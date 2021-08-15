package com.example.my_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.Retrofit;
import com.example.my_timetable.Model.JwtResponse;
import com.example.my_timetable.Model.User;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        textView=findViewById(R.id.error);

    }

    public void adminHome(View view){
        startActivity(new Intent(MainActivity.this,Admin.class));
    }

    public void studentHome(View view){startActivity(new Intent(MainActivity.this, student.class));}

    public void Login(View view){
        User user = new User();
        Retrofit retrofit = new Retrofit();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
        Call<JwtResponse> jwtResponseCall = apiCalls.authenticateUser(user);
        jwtResponseCall.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if(response.isSuccessful()){
                    JwtResponse jwtResponse =response.body();

                    Toast.makeText(getApplicationContext(),"Operation Successful! "+jwtResponse.getEmail(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Operation Failed! "+t, Toast.LENGTH_LONG).show();
            }
        });



    }
}