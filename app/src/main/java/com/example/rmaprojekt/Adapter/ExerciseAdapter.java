package com.example.rmaprojekt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExercisesViewHolder> {

    private List<Exercise> exerciseList = new ArrayList<>();
    private OnExerciseClickListener listener;

    public void setExercises(List<Exercise> exercises) {
        this.exerciseList = exercises;
        notifyDataSetChanged();
    }

    public Exercise getExerciseAt(int position) {
        return exerciseList.get(position);
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_exercise, parent, false);
        return new ExercisesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ExercisesViewHolder extends RecyclerView.ViewHolder {

        private TextView exerciseItemView,
                exerciseRepsTextView,
                exerciseSetsTextView;


        private ExercisesViewHolder(View itemView) {
            super(itemView);
            exerciseItemView = itemView.findViewById(R.id.exerciseTextView);
            exerciseRepsTextView = itemView.findViewById(R.id.exerciseRepsTextView);
            exerciseSetsTextView = itemView.findViewById(R.id.exerciseSetsTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onExerciseClick(exerciseList.get(position));
                    }
                }
            });

        }

        public void bind(Exercise exercise) {
            exerciseItemView.setText(exercise.getName());
            exerciseRepsTextView.setText("Reps: " + Integer.toString(exercise.getReps()));
            exerciseSetsTextView.setText("Sets: " + Integer.toString(exercise.getSets()));
        }

    }

    public interface OnExerciseClickListener {
        void onExerciseClick(Exercise exercise);
    }

    public void setOnExerciseClickListener(OnExerciseClickListener listener) {
        this.listener = listener;
    }

}
