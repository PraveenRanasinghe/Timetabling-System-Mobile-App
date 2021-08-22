package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.DtoUser;
import com.example.my_timetable.Model.UDto;
import com.example.my_timetable.Model.User;
import com.example.my_timetable.R;

import java.util.List;

public class ViewStudentsAdapter extends RecyclerView.Adapter<ViewStudentsAdapter.ViewHolder>{

    List<UDto> userList;

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
        holder.batchId.setText(user.getBatchId().toString());
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            studentfName=itemView.findViewById(R.id.studFname);
            studentlName=itemView.findViewById(R.id.studLname);
            studentEmail=itemView.findViewById(R.id.studEmail);
            batchId=itemView.findViewById(R.id.learningBatch);
            contactNum=itemView.findViewById(R.id.studContactNum);

        }
    }
}
