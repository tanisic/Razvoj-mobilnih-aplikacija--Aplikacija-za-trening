package com.example.rmaprojekt.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rmaprojekt.Entities.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertExercise(Exercise exercise);

    @Delete
    public void deleteExercise(Exercise exercise);

    @Query("SELECT * FROM exercise WHERE exercise_id = :ID")
    public Exercise getExercise(int ID);

    @Query("SELECT * FROM exercise ORDER BY exercise_id DESC")
    public List<Exercise> getAllExercises();
}
