package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.R;

import java.util.List;

public class ViewClassRoomsAdapter extends RecyclerView.Adapter<ViewClassRoomsAdapter.ViewHolder>{

    List<Classroom> classroomList;

    public ViewClassRoomsAdapter(List<Classroom> classroomList) {
        this.classroomList = classroomList;
    }

    @NonNull
    @Override
    public ViewClassRoomsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewClassRoomsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_class,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewClassRoomsAdapter.ViewHolder holder, int position) {
        Classroom classroom = classroomList.get(position);

        holder.clzId.setText(classroom.getClassRoomID());
        holder.capacity.setText(classroom.getCapacity());
        holder.ac.setText(classroom.getAc());
        holder.smartBoard.setText(classroom.getSmartBoard());

    }

    @Override
    public int getItemCount() {
        return classroomList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView clzId;
        TextView capacity;
        TextView ac;
        TextView smartBoard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clzId=itemView.findViewById(R.id.clzId);
            capacity=itemView.findViewById(R.id.Acapacity);
            ac=itemView.findViewById(R.id.Ac);
            smartBoard=itemView.findViewById(R.id.AsmartBoard);

        }
    }
}
