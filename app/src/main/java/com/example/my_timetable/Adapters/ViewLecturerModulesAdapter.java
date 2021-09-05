package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Module;
import com.example.my_timetable.Model.User;
import com.example.my_timetable.R;

import java.util.List;

public class ViewLecturerModulesAdapter extends RecyclerView.Adapter<ViewLecturerModulesAdapter.ViewHolder>{

    List<Module> moduleList;

    public ViewLecturerModulesAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ViewLecturerModulesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewLecturerModulesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lecturer_modules,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewLecturerModulesAdapter.ViewHolder holder, int position) {
        Module module = moduleList.get(position);

        holder.moduleId.setText(module.getModuleID());
        holder.moduleName.setText(module.getModuleName());
        holder.batchList.setText(module.getBatches().toString());
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView moduleId;
        TextView moduleName;
        TextView batchList;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moduleId=itemView.findViewById(R.id.MyModuleId);
            moduleName=itemView.findViewById(R.id.MyModuleName);
            batchList=itemView.findViewById(R.id.myModuleBatches);
        }
    }
}
