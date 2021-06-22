package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.RoutineWithExercises;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.Toolbar.TrainingDoneAlertDialog;
import com.example.rmaprojekt.ViewModels.RoutineViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrainActivity extends AppCompatActivity {


    private TextView routineNameTV, currentExerciseNameTV, currentExerciseSetsTV,
            currentExerciseRepsTV, currentExerciseCountTV;
    private TextView countDownTV;
    private Button startPauseBTN;
    private Button nextExerciseBTN;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private int exerciseIndex = 0;
    private int sets;
    private long timeLeftInMillis;
    private int exercisesInRoutine;
    private RoutineWithExercises routineWithExercises;
    private RoutineViewModel routineViewModel;
    private List<Exercise> exerciseList = new ArrayList<>();
    private long routineID;
    private long lastTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        SetUI();
        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        Intent data = getIntent();
        routineID = data.getLongExtra(AddEditRoutineActivity.EXTRA_ROUTINE_ID, 0);
        routineWithExercises = routineViewModel.getRoutineWithExercises(routineID);
        exerciseList = routineWithExercises.exercises;
        routineNameTV.setText(routineWithExercises.routine.getRoutineName());
        exercisesInRoutine = exerciseList.size();
        setTitle("Train!");
        //rutina nikada ne može biti prazna stoga nije potrebna provjera

        startRoutine();
        nextExerciseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exerciseIndex < exerciseList.size()) {
                    exerciseIndex++;
                    startRoutine();
                } else if (exerciseIndex == exerciseList.size()) {
                    Log.d("TRAINING DONE", "DONE");
                    trainingDone();
                    return;
                }
            }
        });
        startPauseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
    }

    private void startRoutine() {
        if (exerciseIndex == exerciseList.size()) {
            trainingDone();
            return;
        }
        if (exerciseIndex == 0) {
            Exercise exercise1 = exerciseList.get(exerciseIndex);
            sets = exercise1.getSets();
            timeLeftInMillis = exercise1.getExercise_pause_seconds() * 1000;
            updateCountDownText();
            currentExerciseNameTV.setText(exercise1.getName());
            currentExerciseRepsTV.setText(exercise1.getReps() + " reps per set");
            updateSetsToGoText();
            currentExerciseCountTV.setText(String.format("%d / %d", exerciseIndex + 1, exercisesInRoutine));
            startSet(exercise1);
        }
        if (exerciseIndex != exerciseList.size()) {
            Exercise exercise1 = exerciseList.get(exerciseIndex);
            sets = exercise1.getSets();
            timeLeftInMillis = exercise1.getExercise_pause_seconds() * 1000;
            currentExerciseNameTV.setText(exercise1.getName());
            currentExerciseRepsTV.setText(exercise1.getReps() + " reps per set");
            currentExerciseCountTV.setText(String.format("%d / %d", exerciseIndex + 1, exercisesInRoutine));
            startSet(exercise1);
        }

    }

    private void startSet(Exercise exercise) {
        if (sets >= 0) {
            timeLeftInMillis = exercise.getExercise_pause_seconds() * 1000;
            lastTimeLeftInMillis = timeLeftInMillis;
            updateCountDownText();
            updateSetsToGoText();
        } else {
            exerciseIndex++;
        }
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startPauseBTN.setText("START");

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startPauseBTN.setText("START");
                sets--;
                updateSetsToGoText();
                updateCountDownText();
                if (sets <= 0) {
                    if (exerciseIndex < exerciseList.size()) {
                        exerciseIndex++;
                        startRoutine();
                    } else {
                        trainingDone();
                    }
                } else {
                    timeLeftInMillis = lastTimeLeftInMillis;
                }
            }


        }.start();
        timerRunning = true;
        startPauseBTN.setText("PAUSE");
    }

    private void trainingDone() {
        TrainingDoneAlertDialog dialog = new TrainingDoneAlertDialog();
        dialog.show(getSupportFragmentManager(), "Trainng done");
    }

    private void resetTimer() {

    }

    private void updateSetsToGoText() {
        currentExerciseSetsTV.setText(sets + " sets to go");
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDownTV.setText(timeLeftFormatted);
    }

    private void SetUI() {
        routineNameTV = findViewById(R.id.routineNameTrainTV);
        countDownTV = findViewById(R.id.exerciseSecondsTV);
        currentExerciseNameTV = findViewById(R.id.currentExerciseNameTV);
        currentExerciseSetsTV = findViewById(R.id.currentExerciseSetsTV);
        currentExerciseRepsTV = findViewById(R.id.currentExerciseRepsTV);
        currentExerciseCountTV = findViewById(R.id.currentExerciseCountTV);
        startPauseBTN = findViewById(R.id.button_start_pause);
        nextExerciseBTN = findViewById(R.id.button_next_exercise);
    }
}