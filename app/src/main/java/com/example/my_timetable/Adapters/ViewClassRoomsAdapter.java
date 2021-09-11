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
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.Timetable;
import com.example.my_timetable.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.removeClz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(v.getContext()).setTitle("Remove Classroom").setMessage("Are you sure to Remove this Classroom?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = v.getContext().getSharedPreferences("SHARED", Context.MODE_PRIVATE);
                        String name = prefs.getString("token", null);
                        String jwt = "Bearer " + name;

                        RetrofitAPI retrofit = new RetrofitAPI();
                        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
                        Call<Classroom> jwtResponseCall = apiCalls.removeClassroom(jwt,classroom);

                        jwtResponseCall.enqueue(new Callback<Classroom>() {
                            @Override
                            public void onResponse(Call<Classroom> call, Response<Classroom> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(v.getContext().getApplicationContext(), "Classroom has been Removed Successfully!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Classroom> call, Throwable t) {
                                Toast.makeText(v.getContext().getApplicationContext(), "Classroom has been Removed Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

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
        Button removeClz;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clzId=itemView.findViewById(R.id.clzId);
            capacity=itemView.findViewById(R.id.Acapacity);
            ac=itemView.findViewById(R.id.Ac);
            smartBoard=itemView.findViewById(R.id.AsmartBoard);
            removeClz=itemView.findViewById(R.id.clzRemoveBtn);

        }
    }
}
