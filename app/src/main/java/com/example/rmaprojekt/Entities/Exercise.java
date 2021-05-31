package com.example.rmaprojekt.Entities;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "exercise")
public class Exercise {

    public static DiffUtil.ItemCallback<Exercise> itemCallback = new DiffUtil.ItemCallback<Exercise>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getID() == newItem.getID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.equals(newItem);
        }
    };
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "exercise_id")
    public long exerciseID;
    @ColumnInfo(name = "exercise_name")
    public String exerciseName;
    @ColumnInfo(name = "exercise_reps")
    public int reps;
    @ColumnInfo(name = "exercise_sets")
    public int sets;

    public Exercise(String exerciseName, int reps, int sets) {
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
    }

    public long getID() {
        return exerciseID;
    }

    public void setID(long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getName() {
        return this.exerciseName;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return exerciseID == exercise.exerciseID &&
                reps == exercise.reps &&
                sets == exercise.sets &&
                Objects.equals(exerciseName, exercise.exerciseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseID, exerciseName, reps, sets);
    }
}
