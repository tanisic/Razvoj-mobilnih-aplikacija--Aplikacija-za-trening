package com.example.rmaprojekt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.R;

import java.util.ArrayList;
import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder> {

    private List<Routine> routineList = new ArrayList<>();
    private OnRoutineClickListener listener;

    public void setRoutines(List<Routine> routines) {
        this.routineList = routines;
        notifyDataSetChanged();
    }

    public Routine getRoutineAt(int position) {
        return routineList.get(position);
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_routine, parent, false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        Routine routine = routineList.get(position);
        holder.bind(routine);
    }

    @Override
    public int getItemCount() {
        return routineList.size();
    }

    public class RoutineViewHolder extends RecyclerView.ViewHolder {

        private TextView routineNameTextView;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            routineNameTextView = itemView.findViewById(R.id.routineNameTextView);
        }

        public void bind(Routine routine) {
            routineNameTextView.setText(routine.getRoutineName());
        }
    }
    public interface OnRoutineClickListener{
        void onRoutineClick(Routine routine);
    }
    public void setOnRoutineClickListener(OnRoutineClickListener listener){
        this.listener=listener;
    }
}
