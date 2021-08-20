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

public class ViewModulesAdapter extends RecyclerView.Adapter<ViewModulesAdapter.ViewHolder>{
    List<Module> moduleList;

    public ViewModulesAdapter(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ViewModulesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewModulesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_module,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewModulesAdapter.ViewHolder holder, int position) {
        Module module = moduleList.get(position);
        holder.lecFName.setText(module.getUser().getfName());
        holder.lecLName.setText(module.getUser().getlName());
        holder.learningBatches.setText(module.getBatches().toString());
        holder.moduleName.setText(module.getModuleName());
        holder.moduleId.setText(module.getModuleID());
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView moduleId;
        TextView moduleName;
        TextView learningBatches;
        TextView lecFName;
        TextView lecLName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lecFName=itemView.findViewById(R.id.lecFName);
            lecLName=itemView.findViewById(R.id.lecLName);
            moduleId=itemView.findViewById(R.id.MmoduleId);
            moduleName=itemView.findViewById(R.id.MmoduleName);
            learningBatches=itemView.findViewById(R.id.MlearningBatches);

        }
    }
}
