package com.example.rmaprojekt.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.ExercisesInRoutineListener;
import com.example.rmaprojekt.R;

import java.util.ArrayList;
import java.util.List;

public class ExercisesInRoutineAdapter extends RecyclerView.Adapter<ExercisesInRoutineAdapter.ExercisesInRoutineViewHolder> {

    private List<Exercise> exerciseList = new ArrayList<>();
    private ExercisesInRoutineListener listener;
    private Context context;

    public ExercisesInRoutineAdapter(ExercisesInRoutineListener listener) {
        this.listener = listener;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exerciseList = exercises;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExercisesInRoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExercisesInRoutineViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_exercise_clickable, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesInRoutineViewHolder holder, int position) {
        holder.bind(exerciseList.get(position));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public List<Exercise> getSelectedExercises() {
        List<Exercise> selectedExercises = new ArrayList<>();
        for (Exercise exercise : exerciseList) {
            if (exercise.isSelected) {
                selectedExercises.add(exercise);
            }
        }
        return selectedExercises;
    }

    public void setOnClickListener(ExercisesInRoutineListener listener) {
        this.listener = listener;
    }

    class ExercisesInRoutineViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout;
        TextView exerciseNameTV, exerciseRepsTV, exerciseSetsTV;
        ImageView imageSelected;


        public ExercisesInRoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layoutExercise);

            exerciseNameTV = itemView.findViewById(R.id.exerciseTV);
            exerciseSetsTV = itemView.findViewById(R.id.exerciseSetsTV);
            exerciseRepsTV = itemView.findViewById(R.id.exerciseRepsTV);
            imageSelected = itemView.findViewById(R.id.imageSelected);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    Log.d("layout click", "layout clicked " + position);

                    Exercise exercise = exerciseList.get(position);
                    exercise.isSelected = !exercise.isSelected;
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        Log.d("layout click", "listener != null " + position);
                        if (exercise.isSelected) {
                            Log.d("layout click", "exercise is not selected " + position);
                            imageSelected.setVisibility(View.GONE);
                            exercise.isSelected = false;
                            if (getSelectedExercises().size() == 0) {
                                listener.onExerciseInRoutineAction(false);
                            } else {
                                Log.d("layout click", "exercise is selected " + position);
                                imageSelected.setVisibility(View.VISIBLE);
                                exercise.isSelected = true;
                                listener.onExerciseInRoutineAction(true);
                            }
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }

        void bind(final Exercise exercise) {
            exerciseNameTV.setText(exercise.getName());
            exerciseRepsTV.setText("Reps: " + exercise.getReps());
            exerciseSetsTV.setText("Sets: " + exercise.getSets());
            imageSelected.setVisibility(exercise.isSelected() ? View.VISIBLE : View.GONE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exercise.setSelected(!exercise.isSelected());
                    imageSelected.setVisibility(exercise.isSelected() ? View.VISIBLE : View.GONE);
                }
            });

        }
    }
}
