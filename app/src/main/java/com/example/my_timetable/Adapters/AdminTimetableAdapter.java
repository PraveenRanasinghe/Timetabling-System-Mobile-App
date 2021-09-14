package com.example.my_timetable.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.Model.TimetableDTO;
import com.example.my_timetable.R;
import com.example.my_timetable.ReScheduleClasses;
import com.example.my_timetable.ScheduleClasses;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTimetableAdapter extends RecyclerView.Adapter<AdminTimetableAdapter.ViewHolder>{

    List<TimetableDTO> timetableList;

    public AdminTimetableAdapter(List<TimetableDTO> timetableList) {
        this.timetableList = timetableList;
    }

    @NonNull
    @Override
    public AdminTimetableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminTimetableAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_for_admin,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTimetableAdapter.ViewHolder holder, int position) {
        TimetableDTO timetableDTO= timetableList.get(position);

        holder.classRoomId.setText(timetableDTO.getClassRoom().getClassRoomID());
        holder.startTime.setText(timetableDTO.getStartTime());
        holder.endTime.setText(timetableDTO.getEndTime());
        holder.scheduledDate.setText(timetableDTO.getScheduledDate());
        holder.moduleName.setText(timetableDTO.getModule().getModuleName());
        holder.batch.setText(timetableDTO.getBatches().toString());
        holder.lecFName.setText(timetableDTO.getModule().getUser().getfName());
        holder.lecLName.setText(timetableDTO.getModule().getUser().getlName());

        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(v.getContext()).setTitle("Cancel Lecture").setMessage("Are you sure to Cancel this lecture?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences prefs = v.getContext().getSharedPreferences("SHARED", Context.MODE_PRIVATE);
                        String name = prefs.getString("token", null);
                        String jwt = "Bearer " + name;

                        RetrofitAPI retrofit = new RetrofitAPI();
                        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);

                        Timetable timetable = new Timetable();

                        timetable.setTimetableId(timetableDTO.getTimetableId());
                        timetable.setStartTime(timetableDTO.getStartTime());
                        timetable.setEndTime(timetableDTO.getEndTime());
                        timetable.setClassRoom(timetableDTO.getClassRoom());
                        timetable.setModules(timetableDTO.getModule());
                        timetable.setBatches(timetableDTO.getBatches());
                        timetable.setScheduledDate(Date.valueOf(timetableDTO.getScheduledDate()));

                        Call<Timetable> jwtResponseCall = apiCalls.cancelTimetable(jwt,timetable);

                        jwtResponseCall.enqueue(new Callback<Timetable>() {
                            @Override
                            public void onResponse(Call<Timetable> call, Response<Timetable> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(v.getContext().getApplicationContext(), "Scheduled Timetable has been Canceled Successfully!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Timetable> call, Throwable t) {
                                Toast.makeText(v.getContext().getApplicationContext(), "Scheduled Timetable has been Canceled Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        holder.rescheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReScheduleClasses.class);
                TimetableDTO timetable= new TimetableDTO();
                timetable.setTimetableId(timetableDTO.getTimetableId());
                timetable.setStartTime(timetableDTO.getStartTime());
                timetable.setEndTime(timetableDTO.getEndTime());
                timetable.setScheduledDate(timetableDTO.getScheduledDate());
                timetable.setClassRoom(timetableDTO.getClassRoom());
                timetable.setModule(timetableDTO.getModule());
                timetable.setBatches(timetableDTO.getBatches());
                intent.putExtra("timetableData",timetable);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return timetableList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView moduleName;
        TextView batch;
        TextView scheduledDate;
        TextView startTime;
        TextView endTime;
        TextView classRoomId;
        TextView lecFName;
        TextView lecLName;
        Button cancelButton;
        Button rescheduleButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleName=itemView.findViewById(R.id.moduleIdA);
            scheduledDate=itemView.findViewById(R.id.dateA);
            startTime=itemView.findViewById(R.id.startTimeA);
            endTime=itemView.findViewById(R.id.endTimeA);
            classRoomId=itemView.findViewById(R.id.classRoomIdA);
            batch=itemView.findViewById(R.id.batchIdA);
            lecFName=itemView.findViewById(R.id.fNameLec);
            lecLName=itemView.findViewById(R.id.lNameLec);
            cancelButton=itemView.findViewById(R.id.cancelBtnAdmin);
            rescheduleButton=itemView.findViewById(R.id.rescheduleBtnAdmin);

        }
    }
}
