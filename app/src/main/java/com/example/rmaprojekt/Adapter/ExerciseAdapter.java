package com.example.rmaprojekt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExercisesViewHolder>{

    private List<Exercise> exerciseList = new ArrayList<>();

    public void setExercises(List<Exercise> exercises){
        this.exerciseList = exercises;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
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

    public class ExercisesViewHolder  extends RecyclerView.ViewHolder {

        private TextView exerciseItemView,
                exerciseRepsTextView,
                exerciseSetsTextView;
        private Button deleteExercise;

        private ExercisesViewHolder(View itemView) {
            super(itemView);
            exerciseItemView = itemView.findViewById(R.id.exerciseTextView);
            exerciseRepsTextView = itemView.findViewById(R.id.exerciseRepsTextView);
            exerciseSetsTextView = itemView.findViewById(R.id.exerciseSetsTextView);
            deleteExercise = itemView.findViewById(R.id.deleteExercise);
            deleteExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        public void bind(Exercise exercise) {
            exerciseItemView.setText(exercise.getExerciseName());
            exerciseRepsTextView.setText("Reps: "+Integer.toString(exercise.getReps()));
            exerciseSetsTextView.setText("Sets: "+Integer.toString(exercise.getSets()));
        }

    }


}
