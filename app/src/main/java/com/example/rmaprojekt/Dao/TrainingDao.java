package com.example.rmaprojekt.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineWithExercises;

import java.util.List;

@Dao
public interface TrainingDao {

    //Exercise
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertExercise(Exercise exercise);

    @Update
    public void updateExercise(Exercise exercise);

    @Delete
    public void deleteExercise(Exercise exercise);

    @Query("DELETE FROM exercise")
    public void deleteAllExercises();

    @Query("SELECT * FROM exercise WHERE exercise_id = :ID")
    public Exercise getExercise(int ID);

    @Query("SELECT * FROM exercise ORDER BY exercise_id DESC")
    public List<Exercise> getAllExercises();

    //Routine
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertRoutine(Routine routine);

    @Delete
    public void deleteRoutine(Routine routine);

    @Update
    public void updateRoutine(Routine routine);

    @Query("SELECT * FROM routine WHERE routine_id=:ID")
    public void getRoutine(long ID);

    //RoutineExercise
    @Query("SELECT * FROM routine")
    List<RoutineWithExercises> getRoutinesWithExercises();

}
