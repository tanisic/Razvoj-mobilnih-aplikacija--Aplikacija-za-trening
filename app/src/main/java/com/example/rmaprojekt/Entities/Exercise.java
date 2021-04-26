package com.example.rmaprojekt.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="exercise")
public class Exercise{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="exercise_id")
    public long exerciseID;

    @ColumnInfo(name = "exercise_name")
    public String exerciseName;

    @ColumnInfo(name="exercise_reps")
    public int reps;

    public Exercise(@NonNull String exerciseName){
        this.exerciseName = exerciseName;
    }

    public String getExerciseName(){
        return this.exerciseName;
    }

}
