package com.example.rmaprojekt.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineWithExercises;
import com.example.rmaprojekt.Repository.TrainingRepository;

import java.util.List;

public class RoutineViewModel extends AndroidViewModel {

    private TrainingRepository trainingRepository;
    private LiveData<List<Routine>> allRoutines;

    public RoutineViewModel(@NonNull Application application) {
        super(application);
        trainingRepository = new TrainingRepository(application);
        allRoutines = trainingRepository.getAllRoutines();
    }

    public void insertRoutine(Routine routine) {
        trainingRepository.insertRoutine(routine);
    }

    public void updateRoutine(Routine routine) {
        trainingRepository.updateRoutine(routine);
    }

    public void deleteRoutine(Routine routine) {
        trainingRepository.deleteRoutine(routine);
    }

    public LiveData<List<Routine>> getAllRoutines() {
        return allRoutines;
    }

    public void deleteAllRoutines() {
        trainingRepository.deleteAllRoutines();
    }

    public void insert(Routine routine) {
        trainingRepository.insertRoutine(routine);
    }

    public void update(Routine routine) {
        trainingRepository.updateRoutine(routine);
    }
}
