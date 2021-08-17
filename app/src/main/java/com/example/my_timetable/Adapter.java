package com.example.my_timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Timetable;

import java.sql.Time;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Timetable> timetableList;

    public Adapter(List<Timetable> timetableList) {
        this.timetableList = timetableList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_for_students,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Timetable timetable =timetableList.get(position);
        holder.classRoomId.setText(timetable.getClassRoom().getClassRoomID());
        holder.startTime.setText(timetable.getStartTime().toString());
        holder.endTime.setText(timetable.getEndTime().toString());
        holder.scheduledDate.setText(timetable.getScheduledDate().toString());
    }

    @Override
    public int getItemCount() {
        return timetableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView moduleName;
        TextView scheduledDate;
        TextView startTime;
        TextView endTime;
        TextView classRoomId;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moduleName=itemView.findViewById(R.id.moduleName);
            scheduledDate=itemView.findViewById(R.id.scheduledDate);
            startTime=itemView.findViewById(R.id.endTime);
            endTime=itemView.findViewById(R.id.endTime);
            classRoomId=itemView.findViewById(R.id.classRoomId);
        }
    }
}
