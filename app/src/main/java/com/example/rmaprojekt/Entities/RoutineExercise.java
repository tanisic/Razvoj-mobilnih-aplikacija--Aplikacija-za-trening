package com.example.rmaprojekt.Entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"routine_id", "exercise_id"})
public class RoutineExercise {
    public long routine_id;
    public long exercise_id;
}
