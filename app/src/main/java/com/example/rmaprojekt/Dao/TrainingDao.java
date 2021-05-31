package com.example.rmaprojekt.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineExercise;
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
    public LiveData<List<Exercise>> getAllExercises();

    //Routine
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertRoutine(Routine routine);

    @Delete
    public void deleteRoutine(Routine routine);

    @Update
    public void updateRoutine(Routine routine);

    @Query("SELECT * FROM routine WHERE routine_id=:ID")
    public Routine getRoutine(long ID);

    //RoutineExercise
    @Transaction
    @Query("SELECT * FROM routine")
    LiveData<List<RoutineWithExercises>> getRoutinesWithExercises();

    @Transaction
    @Query("SELECT * FROM routine WHERE routine_id = :routineID")
    RoutineWithExercises getRoutineWithExercises(long routineID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertRoutineExercise(RoutineExercise crossRef);

    @Transaction
    @Query("SELECT * FROM routine")
    LiveData<List<Routine>> getAllRoutines();

    @Query("DELETE FROM routine")
    void deleteAllRoutines();
}
