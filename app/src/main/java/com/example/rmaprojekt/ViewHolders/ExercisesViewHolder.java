package com.example.rmaprojekt.ViewHolders;

import android.service.controls.templates.TemperatureControlTemplate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.R;

public class ExercisesViewHolder  extends RecyclerView.ViewHolder {

    private final TextView exerciseItemView, exerciseRepsTextView,
    exerciseSetsTextView;

    private ExercisesViewHolder(View itemView) {
        super(itemView);
        exerciseItemView = itemView.findViewById(R.id.exerciseTextView);
        exerciseRepsTextView = itemView.findViewById(R.id.exerciseRepsTextView);
        exerciseSetsTextView = itemView.findViewById(R.id.exerciseSetsTextView);
    }
    public void bind(Exercise exerise) {
        exerciseItemView.setText(exerise.getExerciseName());
        exerciseRepsTextView.setText("Reps: "+Integer.toString(exerise.getReps()));
        exerciseSetsTextView.setText("Sets: "+Integer.toString(exerise.getSets()));
    }
    public static ExercisesViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ExercisesViewHolder(view);
    }
}
