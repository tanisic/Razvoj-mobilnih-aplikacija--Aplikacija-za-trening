package com.example.rmaprojekt.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineExercise;
import com.example.rmaprojekt.Entities.RoutineWithExercises;
import com.example.rmaprojekt.Repository.TrainingRepository;

import java.util.List;

public class RoutineViewModel extends AndroidViewModel {

    private final TrainingRepository trainingRepository;
    private final LiveData<List<Routine>> allRoutines;

    public RoutineViewModel(@NonNull Application application) {
        super(application);
        trainingRepository = new TrainingRepository(application);
        allRoutines = trainingRepository.getAllRoutines();
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

    public long insert(Routine routine) {
        return trainingRepository.insertRoutine(routine);
    }

    public void deleteCrossRef(long routineID) {
        trainingRepository.deleteCrossRef(routineID);
    }

    public void deleteAllCrossRef() {
        trainingRepository.deleteAllCrossRef();
    }

    public void insertRoutineExercise(RoutineExercise routineExercise) {
        trainingRepository.insertExerciseIntoRoutine(routineExercise);
    }

    public RoutineWithExercises getRoutineWithExercises(long routineID) {
        return trainingRepository.getRoutineWithExercises(routineID);
    }

    public void update(Routine routine) {
        trainingRepository.updateRoutine(routine);
    }

    public Routine getRoutine(long ID) {
        return trainingRepository.getRoutine(ID);
    }
}
