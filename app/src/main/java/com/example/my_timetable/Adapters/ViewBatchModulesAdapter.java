package com.example.my_timetable.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_timetable.Model.Module;
import com.example.my_timetable.R;

import java.util.List;

public class ViewBatchModulesAdapter extends RecyclerView.Adapter<ViewBatchModulesAdapter.ViewHolder>{

    List<Module> moduleList;

    public ViewBatchModulesAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ViewBatchModulesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewBatchModulesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.batch_modules,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBatchModulesAdapter.ViewHolder holder, int position) {
        Module module = moduleList.get(position);

        holder.lecFName.setText(module.getUser().getfName());
        holder.lecLName.setText(module.getUser().getlName());
        holder.moduleName.setText(module.getModuleName());
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView moduleName;
        TextView lecFName;
        TextView lecLName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lecFName=itemView.findViewById(R.id.lecfName);
            lecLName=itemView.findViewById(R.id.leclname);
            moduleName=itemView.findViewById(R.id.MmoduleName);
        }
    }
}
