package com.example.my_timetable.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.DtoUser;
import com.example.my_timetable.Model.UDto;
import com.example.my_timetable.Model.User;
import com.example.my_timetable.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStudentsAdapter extends RecyclerView.Adapter<ViewStudentsAdapter.ViewHolder>{

    List<UDto> userList;
    private static final int REQUEST_CALL=1;
    public ViewStudentsAdapter(List<UDto> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewStudentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewStudentsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_students,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewStudentsAdapter.ViewHolder holder, int position) {
        UDto user = userList.get(position);
        holder.studentEmail.setText(user.getEmail());
        holder.studentfName.setText(user.getfName());
        holder.studentlName.setText(user.getlName());
        holder.contactNum.setText(user.getContactNumber());
        holder.batchId.setText(user.getBatchId().getBatchName());

        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber= user.getContactNumber();
                String call="tel:"+mobileNumber.trim();
                Intent intent= new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(call));
                v.getContext().startActivity(intent);
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(v.getContext()).setTitle("Remove Student").setMessage("Are you sure to Remove this Student from the Institute?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = v.getContext().getSharedPreferences("SHARED", Context.MODE_PRIVATE);
                        String name = prefs.getString("token", null);
                        String jwt = "Bearer " + name;

                        RetrofitAPI retrofit = new RetrofitAPI();
                        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);


                        DtoUser dtoUser = new DtoUser();
                        dtoUser.setfName(user.getfName());
                        dtoUser.setlName(user.getlName());
                        dtoUser.setEmail(user.getEmail());
                        dtoUser.setContactNumber(user.getContactNumber());
                        dtoUser.setUserRole(user.getUserRole());
                        dtoUser.setPassword(user.getPassword());
                        dtoUser.setBatch(user.getBatchId());

                        Call<DtoUser> jwtResponseCall = apiCalls.removeLecturer(jwt,dtoUser);
                        jwtResponseCall.enqueue(new Callback<DtoUser>() {
                            @Override
                            public void onResponse(Call<DtoUser> call, Response<DtoUser> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(v.getContext().getApplicationContext(), "Student has been removed successfully!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DtoUser> call, Throwable t) {
                                Toast.makeText(v.getContext().getApplicationContext(), "Student has been removed successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView studentfName;
        TextView studentlName;
        TextView studentEmail;
        TextView batchId;
        TextView contactNum;
        ImageView callBtn;
        ImageView removeBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            studentfName=itemView.findViewById(R.id.studFname);
            studentlName=itemView.findViewById(R.id.studLname);
            studentEmail=itemView.findViewById(R.id.studEmail);
            batchId=itemView.findViewById(R.id.learningBatch);
            contactNum=itemView.findViewById(R.id.studContactNum);
            callBtn=itemView.findViewById(R.id.callStudent);
            removeBtn=itemView.findViewById(R.id.removeStud);

        }
    }
}
