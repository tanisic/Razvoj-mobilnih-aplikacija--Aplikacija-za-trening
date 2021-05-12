package com.example.rmaprojekt.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.rmaprojekt.Dao.TrainingDao;
import com.example.rmaprojekt.Database.TrainingRoomDatabase;
import com.example.rmaprojekt.Entities.Exercise;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class TrainingRepository {
    private TrainingDao trainingDao;
    private LiveData<List<Exercise>> allExercises;


    public TrainingRepository(Application application){
        TrainingRoomDatabase db = TrainingRoomDatabase.getDatabase(application);
        trainingDao = db.trainingDao();
        allExercises = (LiveData<List<Exercise>>) trainingDao.getAllExercises();
    }

    public  LiveData<List<Exercise>> getAllExercises(){
        return allExercises;
    }
    void insertExercise(Exercise exercise){
        TrainingRoomDatabase.databaseWriteExecutor.execute(()->{
            trainingDao.insertExercise(exercise);
                });
    }

    void updateExercise(Exercise exercise){
        TrainingRoomDatabase.databaseWriteExecutor.execute(()->{
            trainingDao.updateExercise(exercise);
        });
    }

    void deleteExercise(Exercise exercise){
        TrainingRoomDatabase.databaseWriteExecutor.execute(()->{
            trainingDao.deleteExercise(exercise);
        });
    }


}
