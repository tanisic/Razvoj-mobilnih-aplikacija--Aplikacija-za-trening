package com.example.rmaprojekt.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Repository.TrainingRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private TrainingRepository trainingRepository;
    private LiveData<List<Exercise>> allExercises;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        this.trainingRepository = new TrainingRepository(application);
        this.allExercises = trainingRepository.getAllExercises();
    }





    public LiveData<List<Exercise>> getAllExercises(){ return allExercises;}

    public void insert(Exercise exercise){ trainingRepository.insertExercise(exercise);}
}
