package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Batch;
import com.example.my_timetable.Model.Module;
import com.example.my_timetable.R;

import java.util.List;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            batchId=itemView.findViewById(R.id.AbatchId);
            batchName=itemView.findViewById(R.id.AbatchName);
            dateOfCommencement=itemView.findViewById(R.id.AcommencementDate);
            dateOfTermination=itemView.findViewById(R.id.AterminationDate);


        }
    }
}
