package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.R;

import java.util.List;

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
        Timetable timetable =timetableList.get(position);
        holder.classRoomId.setText(timetable.getClassRoom().getClassRoomID());
        holder.startTime.setText(timetable.getStartTime());
        holder.endTime.setText(timetable.getEndTime());
        holder.scheduledDate.setText(timetable.getScheduledDate().toString());
        holder.batchId.setText(timetable.getBatches().toString());
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
