package com.example.my_timetable.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.Model.TimetableDTO;
import com.example.my_timetable.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
        holder.endTime.setText(timetableDTO.getStartTime());
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
        TextView batch;
        TextView scheduledDate;
        TextView startTime;
        TextView endTime;
        TextView classRoomId;
        TextView lecFName;
        TextView lecLName;
        Button cancelButton;



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

//            SharedPreferences prefs = Context.getSharedPreferences("SHARED", Context.MODE_PRIVATE);
        }
    }
}
