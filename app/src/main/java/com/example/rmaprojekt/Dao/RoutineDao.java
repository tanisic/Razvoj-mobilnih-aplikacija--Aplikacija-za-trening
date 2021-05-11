package com.example.rmaprojekt.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rmaprojekt.Entities.Routine;

@Dao
public interface RoutineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertRoutine(Routine routine);

    @Delete
    public void deleteRoutine(Routine routine);

    @Update
    public void updateRoutine(Routine routine);

    @Query("SELECT * FROM routine WHERE routine_id=:ID")
    public void getRoutine(long ID);
}
