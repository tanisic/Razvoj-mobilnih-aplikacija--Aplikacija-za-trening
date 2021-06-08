package com.example.rmaprojekt.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.rmaprojekt.Dao.TrainingDao;
import com.example.rmaprojekt.Database.TrainingRoomDatabase;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineExercise;
import com.example.rmaprojekt.Entities.RoutineWithExercises;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TrainingRepository {

    private final TrainingDao trainingDao;
    private final LiveData<List<Exercise>> allExercises;
    private final LiveData<List<Routine>> allRoutines;


    public TrainingRepository(@NonNull Application application) {
        TrainingRoomDatabase db = TrainingRoomDatabase.getDatabase(application);
        trainingDao = db.trainingDao();
        allExercises = trainingDao.getAllExercises();
        allRoutines = trainingDao.getAllRoutines();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    public void insertExercise(Exercise exercise) {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.insertExercise(exercise);
        });
    }

    public void updateExercise(Exercise exercise) {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.updateExercise(exercise);
        });
    }

    public void deleteExercise(Exercise exercise) {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.deleteExercise(exercise);
        });
    }

    public void deleteAllExercises() {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.deleteAllExercises();
        });
    }


    public long insertRoutine(Routine routine) {


            Callable<Long> value = new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    return trainingDao.insertRoutine(routine);
                }
            };
            Future future = TrainingRoomDatabase.databaseWriteExecutor.submit(value);
            Long result = new Long(1);
            try {
              result = Long.valueOf((Long)future.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return result;
    }
    public void deleteCrossRef(long routineID) {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.deleteCrossRef(routineID);
        });
    }

    public void updateRoutine(Routine routine) {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.updateRoutine(routine);
        });
    }

    public void deleteRoutine(Routine routine) {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.deleteRoutine(routine);
        });
    }

    public void deleteExercisesFromRoutine(long ID){
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.deleteExercisesFromRoutine(ID);
        });

    }
    public LiveData<List<Routine>> getAllRoutines() {
        return allRoutines;
    }

    public RoutineWithExercises getRoutineWithExercises(long routineID) {
        return trainingDao.getRoutineWithExercises(routineID);
    }
    public void insertExerciseIntoRoutine(RoutineExercise crossref){
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.insertRoutineExercise(crossref);
        });
    }
    public void deleteAllRoutines() {
        TrainingRoomDatabase.databaseWriteExecutor.execute(() -> {
            trainingDao.deleteAllRoutines();
        });
    }

    public Routine getRoutine(long id) {
        return trainingDao.getRoutine(id);
    }
}
