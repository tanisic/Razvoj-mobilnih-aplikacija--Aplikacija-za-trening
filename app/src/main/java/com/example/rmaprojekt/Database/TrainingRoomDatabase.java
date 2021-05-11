package com.example.rmaprojekt.Database;

import androidx.room.RoomDatabase;

import com.example.rmaprojekt.Dao.ExerciseDao;

public abstract class TrainingRoomDatabase extends RoomDatabase {

    public abstract com.example.rmaprojekt.Dao.ExerciseDao exerciseDao();
}
