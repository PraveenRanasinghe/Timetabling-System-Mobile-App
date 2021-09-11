package com.example.my_timetable.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.API.ApiCalls;
import com.example.my_timetable.API.RetrofitAPI;
import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.Classroom;
import com.example.my_timetable.Model.Module;
import com.example.my_timetable.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBatchesAdapter extends RecyclerView.Adapter<ViewBatchesAdapter.ViewHolder>{

    List<Batch> batchList;

    public ViewBatchesAdapter(List<Batch> batchList) {
        this.batchList = batchList;
    }

    @NonNull
    @Override
    public ViewBatchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewBatchesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_batch,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewBatchesAdapter.ViewHolder holder, int position) {

        Batch batch = batchList.get(position);
        holder.batchName.setText(batch.getBatchName());
        holder.batchId.setText(batch.getBatchID());
        holder.dateOfTermination.setText(batch.getStartDate().toString());
        holder.dateOfCommencement.setText(batch.getEndDate().toString());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(v.getContext()).setTitle("Remove Batch").setMessage("Are you sure to Remove this Batch?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = v.getContext().getSharedPreferences("SHARED", Context.MODE_PRIVATE);
                        String name = prefs.getString("token", null);
                        String jwt = "Bearer " + name;

                        RetrofitAPI retrofit = new RetrofitAPI();
                        ApiCalls apiCalls = retrofit.getRetrofit().create(ApiCalls.class);
                        Call<Batch> jwtResponseCall = apiCalls.removeBatch(jwt,batch);

                        jwtResponseCall.enqueue(new Callback<Batch>() {
                            @Override
                            public void onResponse(Call<Batch> call, Response<Batch> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(v.getContext().getApplicationContext(), "Batch has been Removed Successfully!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Batch> call, Throwable t) {
                                Toast.makeText(v.getContext().getApplicationContext(), "Batch has been Removed Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return batchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView batchId;
        TextView batchName;
        TextView dateOfCommencement;
        TextView dateOfTermination;
        ImageView deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            batchId=itemView.findViewById(R.id.AbatchId);
            batchName=itemView.findViewById(R.id.AbatchName);
            dateOfCommencement=itemView.findViewById(R.id.AcommencementDate);
            dateOfTermination=itemView.findViewById(R.id.AterminationDate);
            deleteBtn=itemView.findViewById(R.id.deleteBatch);

        }
    }
}
