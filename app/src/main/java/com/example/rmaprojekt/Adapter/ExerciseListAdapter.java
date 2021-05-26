package com.example.rmaprojekt.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.ExerciseClickInterface;
import com.example.rmaprojekt.R;

public class ExerciseListAdapter extends ListAdapter<Exercise,ExerciseListAdapter.ExercisesViewHolder>{

    ExerciseClickInterface exerciseClickInterface;
    public ExerciseListAdapter(@NonNull DiffUtil.ItemCallback<Exercise> diffCallback, ExerciseClickInterface exerciseClickInterface) {
        super(diffCallback);
        this.exerciseClickInterface = exerciseClickInterface;
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExercisesViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        Exercise exercise = getItem(position);
        holder.bind(exercise);
    }

    public class ExercisesViewHolder  extends RecyclerView.ViewHolder {

        private final TextView exerciseItemView, exerciseRepsTextView,
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
                    exerciseClickInterface.onDelete(getAdapterPosition());
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
