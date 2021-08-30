package com.example.my_timetable.Adapters;

import android.content.Context;
import android.content.DialogInterface;
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
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerTimetableAdapter extends RecyclerView.Adapter<LecturerTimetableAdapter.ViewHolder>{

    List<Timetable> timetableList;

    public LecturerTimetableAdapter(List<Timetable> timetableList) {
        this.timetableList = timetableList;
    }


    @NonNull
    @Override
    public LecturerTimetableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LecturerTimetableAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_for_lecturer,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LecturerTimetableAdapter.ViewHolder holder, int position) {

        Timetable timetables =timetableList.get(position);

        holder.classRoomId.setText(timetables.getClassRoom().getClassRoomID());
        holder.startTime.setText(timetables.getStartTime());
        holder.endTime.setText(timetables.getEndTime());
        holder.scheduledDate.setText(timetables.getScheduledDate().toString());
        holder.batchId.setText(timetables.getBatches().toString());

        holder.cancelLec.setOnClickListener(new View.OnClickListener() {
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

                        timetable.setTimetableId(timetables.getTimetableId());
                        timetable.setStartTime(timetables.getStartTime());
                        timetable.setEndTime(timetables.getEndTime());
                        timetable.setClassRoom(timetables.getClassRoom());
                        timetable.setModules(timetables.getModules());
                        timetable.setBatches(timetables.getBatches());
                        timetable.setScheduledDate(timetables.getScheduledDate());

                        Call<Timetable> jwtResponseCall = apiCalls.lecturerCancelTimetable(jwt,timetable);

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
    }

    @Override
    public int getItemCount() {
        return timetableList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView moduleName;
        TextView batchId;
        TextView scheduledDate;
        TextView startTime;
        TextView endTime;
        TextView classRoomId;
        Button cancelLec;
        Button reScheduleBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moduleName=itemView.findViewById(R.id.moduleId);
            batchId=itemView.findViewById(R.id.batchId);
            scheduledDate=itemView.findViewById(R.id.date);
            startTime=itemView.findViewById(R.id.startTime);
            endTime=itemView.findViewById(R.id.endTime);
            classRoomId=itemView.findViewById(R.id.classRoomId);
            cancelLec=itemView.findViewById(R.id.cancelBtnLec);
            reScheduleBtn=itemView.findViewById(R.id.rescheduleBtnLec);
        }
    }
}
