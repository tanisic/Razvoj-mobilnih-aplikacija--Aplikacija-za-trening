package com.example.rmaprojekt.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rmaprojekt.Dao.TrainingDao;
import com.example.rmaprojekt.Database.TrainingRoomDatabase;
import com.example.rmaprojekt.Entities.Exercise;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class TrainingRepository {
    private TrainingDao trainingDao;
    private MutableLiveData<List<Exercise>> allExercises = new MutableLiveData<>();



    public TrainingRepository(@NonNull Application application){
        TrainingRoomDatabase db = TrainingRoomDatabase.getDatabase(application);
        trainingDao = db.trainingDao();
        TrainingRoomDatabase.databaseWriteExecutor.execute(()->{
            allExercises.postValue(trainingDao.getAllExercises());
        });
    }

    public  LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    public void insertExercise(Exercise exercise){
        TrainingRoomDatabase.databaseWriteExecutor.execute(()->{
            trainingDao.insertExercise(exercise);
                });
    }

    public void updateExercise(Exercise exercise){
        TrainingRoomDatabase.databaseWriteExecutor.execute(()->{
            trainingDao.updateExercise(exercise);
        });
    }

    public void deleteExercise(Exercise exercise){
        TrainingRoomDatabase.databaseWriteExecutor.execute(()->{
            trainingDao.deleteExercise(exercise);
        });
    }


}
