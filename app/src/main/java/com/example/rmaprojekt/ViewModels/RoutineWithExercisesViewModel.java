package com.example.rmaprojekt.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.rmaprojekt.Entities.RoutineWithExercises;
import com.example.rmaprojekt.Repository.TrainingRepository;

public class RoutineWithExercisesViewModel extends AndroidViewModel {

    private final TrainingRepository trainingRepository;
    private RoutineWithExercises routineWithExercises;

    public RoutineWithExercisesViewModel(@NonNull Application application) {
        super(application);
        trainingRepository = new TrainingRepository(application);
    }

    public RoutineWithExercises getRoutineWithExercises(long routineID) {
        routineWithExercises = trainingRepository.getRoutineWithExercises(routineID);
        return routineWithExercises;
    }
}
