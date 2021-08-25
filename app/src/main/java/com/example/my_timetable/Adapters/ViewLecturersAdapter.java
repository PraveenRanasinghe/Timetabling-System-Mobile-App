package com.example.my_timetable.Adapters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Module;
import com.example.my_timetable.Model.User;
import com.example.my_timetable.R;

import java.util.List;

public class ViewLecturersAdapter extends RecyclerView.Adapter<ViewLecturersAdapter.ViewHolder>{

    List<User> userList;

    private static final int REQUEST_CALL=1;

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

//        holder.callBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                makeCall(user.getContactNumber());
//                if(holder.lecContactNum.getText().length()>0){
//                    if(ContextCompat.checkSelfPermission(v.getContext(),Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
//                        ActivityCompat.requestPermissions(v.getContext(),new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
//                    }
//
//                }
//            }
//        });
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
        ImageView callBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lecFName=itemView.findViewById(R.id.lecFName);
            lecLName=itemView.findViewById(R.id.lecLName);
            lecEmail=itemView.findViewById(R.id.lecEmail);
            callBtn=itemView.findViewById(R.id.callBtn);
            lecContactNum=itemView.findViewById(R.id.lecContactNum);

        }
    }

}
