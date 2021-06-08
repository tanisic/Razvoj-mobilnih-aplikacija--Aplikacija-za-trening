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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExercise(Exercise exercise);

    @Update
    void updateExercise(Exercise exercise);

    @Delete
    void deleteExercise(Exercise exercise);

    @Query("DELETE FROM exercise")
    void deleteAllExercises();

    @Query("SELECT * FROM exercise WHERE exercise_id = :ID")
    Exercise getExercise(int ID);

    @Query("SELECT * FROM exercise ORDER BY exercise_id DESC")
    LiveData<List<Exercise>> getAllExercises();

    //Routine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRoutine(Routine routine);

    @Delete
    void deleteRoutine(Routine routine);

    @Update
    void updateRoutine(Routine routine);

    @Query("SELECT * FROM routine WHERE routine_id=:ID")
    Routine getRoutine(long ID);

    //RoutineExercise
    @Transaction
    @Query("SELECT * FROM routine")
    LiveData<List<RoutineWithExercises>> getRoutinesWithExercises();

    @Transaction
    @Query("SELECT * FROM routine WHERE routine_id = :routineID")
    RoutineWithExercises getRoutineWithExercises(long routineID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoutineExercise(RoutineExercise crossRef);

    @Transaction
    @Query("DELETE FROM RoutineExercise WHERE routine_id = :routineID")
    void deleteCrossRef(long routineID);

    @Transaction
    @Query("SELECT * FROM routine")
    LiveData<List<Routine>> getAllRoutines();

    @Query("DELETE FROM routine")
    void deleteAllRoutines();

    @Transaction
    @Query("DELETE FROM RoutineExercise WHERE routine_id = :ID")
    void deleteExercisesFromRoutine(long ID);


}
