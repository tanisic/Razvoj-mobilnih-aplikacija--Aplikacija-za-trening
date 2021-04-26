package com.example.rmaprojekt.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.rmaprojekt.Entities.RoutineWithExercises;

import java.util.List;

@Dao
public interface RoutineExerciseDao {
    @Query("SELECT * FROM routine")
    List<RoutineWithExercises> getRoutinesWithExercises();

    
}
