package com.example.rmaprojekt.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "routine")
public class Routine {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "routine_id")
    public long routineID;

    @ColumnInfo(name = "routine_name")
    public String routineName;

    public Routine(@NonNull String routineName) {
        this.routineName = routineName;
    }

    public String getRoutineName() {
        return routineName;
    }

    public long getID() {
        return routineID;
    }

    public void setID(long id) {
        this.routineID = id;
    }
}
