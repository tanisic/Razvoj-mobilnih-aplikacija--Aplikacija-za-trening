package com.example.rmaprojekt.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.ViewHolders.ExercisesViewHolder;

public class ExerciseListAdapter extends ListAdapter<Exercise, ExercisesViewHolder>{

    public ExerciseListAdapter(@NonNull DiffUtil.ItemCallback<Exercise> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ExercisesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        Exercise current = getItem(position);
        holder.bind(current.getExerciseName());
    }

    public static class ExerciseDiff extends  DiffUtil.ItemCallback<Exercise>{

        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getExerciseName().equals(newItem.getExerciseName());
        }
    }
}
