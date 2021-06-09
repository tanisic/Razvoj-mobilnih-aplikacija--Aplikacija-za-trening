package com.example.rmaprojekt.Entities;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    @ColumnInfo(name = "exercise_pause")
    public int exercise_pause_seconds;
    @Ignore
    public boolean isSelected = false;
    @Ignore
    public Exercise() {

    }

    public Exercise(String exerciseName, int reps, int sets, int exercise_pause_seconds) {
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
        this.exercise_pause_seconds = exercise_pause_seconds;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public int getExercise_pause_seconds() {
        return exercise_pause_seconds;
    }

    public void setExercise_pause_seconds(int exercise_pause_seconds) {
        this.exercise_pause_seconds = exercise_pause_seconds;
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
