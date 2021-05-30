package com.example.rmaprojekt.ViewModels;

import android.app.Application;
import android.view.SurfaceControl;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rmaprojekt.Entities.RoutineWithExercises;
import com.example.rmaprojekt.Repository.TrainingRepository;

import java.util.List;

public class RoutineWithExercisesViewModel extends AndroidViewModel {

    private TrainingRepository trainingRepository;
    private RoutineWithExercises routineWithExercises;

    public RoutineWithExercisesViewModel(@NonNull Application application) {
        super(application);
        trainingRepository = new TrainingRepository(application);
    }

    public RoutineWithExercises getRoutineWithExercises(long routineID){
        routineWithExercises = trainingRepository.getRoutineWithExercises(routineID);
        return routineWithExercises;
    }
}
