package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.User;
import com.example.my_timetable.R;

import java.util.List;

public class ViewLecturersAdapter extends RecyclerView.Adapter<ViewLecturersAdapter.ViewHolder>{

    List<User> userList;

    public ViewLecturersAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewLecturersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewLecturersAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lecturers,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewLecturersAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.lecEmail.setText(user.getEmail());
        holder.lecFName.setText(user.getfName());
        holder.lecLName.setText(user.getlName());
        holder.lecContactNum.setText(user.getContactNumber());
//        holder.teachingBatches.setText((CharSequence) user.getBatchId());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView lecFName;
        TextView lecLName;
        TextView lecEmail;
        TextView teachingBatches;
        TextView lecContactNum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lecFName=itemView.findViewById(R.id.lecFName);
            lecLName=itemView.findViewById(R.id.lecLName);
            lecEmail=itemView.findViewById(R.id.lecEmail);
            teachingBatches=itemView.findViewById(R.id.teachingModules);
            lecContactNum=itemView.findViewById(R.id.lecContactNum);

        }
    }

}
