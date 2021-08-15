package com.example.my_timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter <Adapter.ViewHolder> {


    private CardView[] cardViews;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView moduleName;
        private TextView scheduledDate;
        private TextView startTime;
        private TextView endTime;
        private TextView classRoomId;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.studentCardView);
            moduleName = view.findViewById(R.id.moduleName);
            scheduledDate= view.findViewById(R.id.scheduledDate);
            startTime = view.findViewById(R.id.startTime);
            endTime = view.findViewById(R.id.endTime);
            classRoomId = view.findViewById(R.id.classRoomId);
        }

        public CardView getTextView() {
            return cardView;
        }
    }

    public Adapter(CardView[] cardView) {
        this.cardViews = cardView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_student_timetable,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
