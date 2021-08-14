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
         private final CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.studentCardView);
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

//    @Override
//    public void onBindViewHolder(@NonNull Adapter holder, int position) {
//
//    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
