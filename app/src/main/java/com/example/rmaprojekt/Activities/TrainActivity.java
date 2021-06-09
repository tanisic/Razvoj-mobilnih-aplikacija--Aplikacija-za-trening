package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.Entities.RoutineWithExercises;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.RoutineViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrainActivity extends AppCompatActivity {


    private TextView routineNameTV, currentExercisenameTV, currentExerciseSetsTV,
            currentExerciseRepsTV, currentExerciseCountTV;
    private TextView countDownTV;
    private Button startPauseBTN;
    private Button nextExerciseBTN;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis;
    private int exercisesInRoutine;
    private RoutineWithExercises routineWithExercises;
    private RoutineViewModel routineViewModel;
    private List<Exercise> exerciseList = new ArrayList<>();
    private long routineID;


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
        timeLeftInMillis = 60000;
        routineNameTV.setText(routineWithExercises.routine.getRoutineName());
        exercisesInRoutine = exerciseList.size();
        int currentExerciseIndex = 1;
        for (Exercise exercise : exerciseList) {
            int sets = exercise.getSets();
            for (int i = sets; i >= 0; i++) {
                currentExerciseCountTV.setText(String.format("%d / %d", currentExerciseIndex, exercisesInRoutine));
                currentExercisenameTV.setText(exercise.getName());
                currentExerciseRepsTV.setText(exercise.getReps() + " reps per set");
                currentExerciseSetsTV.setText(sets + " sets to go");

            }
            currentExerciseIndex++;
        }
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
            }
        }.start();
        timerRunning = true;
        startPauseBTN.setText("PAUSE");
    }

    private void resetTimer() {

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
        currentExercisenameTV = findViewById(R.id.currentExerciseNameTV);
        currentExerciseSetsTV = findViewById(R.id.currentExerciseSetsTV);
        currentExerciseRepsTV = findViewById(R.id.currentExerciseRepsTV);
        currentExerciseCountTV = findViewById(R.id.currentExerciseCountTV);
        startPauseBTN = findViewById(R.id.button_start_pause);
        nextExerciseBTN = findViewById(R.id.button_next_exercise);
    }
}