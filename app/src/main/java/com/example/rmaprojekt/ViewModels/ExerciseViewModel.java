package com.example.rmaprojekt.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineExercise;
import com.example.rmaprojekt.Repository.TrainingRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private final TrainingRepository trainingRepository;
    private final LiveData<List<Exercise>> allExercises;


    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        trainingRepository = new TrainingRepository(application);
        allExercises = trainingRepository.getAllExercises();
    }

    public void insert(Exercise exercise) {
        trainingRepository.insertExercise(exercise);
    }

    public void update(Exercise exercise) {
        trainingRepository.updateExercise(exercise);
    }

    public void delete(Exercise exercise) {
        trainingRepository.deleteExercise(exercise);
    }

    public void deleteAllExercises() {
        trainingRepository.deleteAllExercises();
    }

    public void updateRoutineWithExercises(long id, List<Exercise> selectedExercises){
        trainingRepository.deleteExercisesFromRoutine(id);
        for(Exercise exercise : selectedExercises){
            trainingRepository.insertExerciseIntoRoutine(new RoutineExercise(id,exercise.getID()));
        }
    }


    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    public Exercise getExerciseByID(long exerciseID) {
        return trainingRepository.getExerciseByID(exerciseID);
    }
}
