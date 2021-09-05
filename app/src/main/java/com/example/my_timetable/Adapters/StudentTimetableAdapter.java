package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.R;

import java.util.List;

public class StudentTimetableAdapter extends RecyclerView.Adapter<StudentTimetableAdapter.ViewHolder> {

    List<Timetable> timetableList;

    public StudentTimetableAdapter(List<Timetable> timetableList) {
        this.timetableList = timetableList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_for_students,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Timetable timetable =timetableList.get(position);
        holder.classRoomId.setText(timetable.getClassRoom().getClassRoomID());
        holder.startTime.setText(timetable.getStartTime());
        holder.endTime.setText(timetable.getEndTime());
        holder.scheduledDate.setText(timetable.getScheduledDate().toString());
    }

    @Override
    public int getItemCount() {
        return timetableList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView moduleName;
        TextView scheduledDate;
        TextView startTime;
        TextView endTime;
        TextView classRoomId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moduleName=itemView.findViewById(R.id.TmoduleName);
            scheduledDate=itemView.findViewById(R.id.TscheduledDate);
            startTime=itemView.findViewById(R.id.TstartTime);
            endTime=itemView.findViewById(R.id.TendTime);
            classRoomId=itemView.findViewById(R.id.TclassRoomId);
        }
    }
}
