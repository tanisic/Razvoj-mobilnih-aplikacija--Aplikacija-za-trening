package com.example.rmaprojekt.Entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class RoutineWithExercises {
    @Embedded
    public Routine routine;

    @Relation(
            parentColumn = "routine_id",
            entityColumn = "exercise_id"
    )
    public List<Exercise> exercises;

}
