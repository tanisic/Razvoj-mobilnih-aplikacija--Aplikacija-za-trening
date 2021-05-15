package com.example.rmaprojekt.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.rmaprojekt.Dao.TrainingDao;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineExercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {Exercise.class, Routine.class, RoutineExercise.class},
        version = 1,
        exportSchema = false
)
public abstract class TrainingRoomDatabase extends RoomDatabase {


    public abstract TrainingDao trainingDao();
    private static volatile TrainingRoomDatabase INSTANCE;
    private static final String DB_NAME = "training_db";
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TrainingRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (TrainingRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TrainingRoomDatabase.class,DB_NAME).
                            addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TrainingDao dao = INSTANCE.trainingDao();
                dao.deleteAllExercises();

                Exercise exercise = new Exercise("Exercise 1");
                dao.insertExercise(exercise);
                exercise = new Exercise("Exercise 2");
                dao.insertExercise(exercise);
            });
        }
    };
}
