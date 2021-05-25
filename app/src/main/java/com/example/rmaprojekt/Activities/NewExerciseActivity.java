package com.example.rmaprojekt.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rmaprojekt.Adapter.ExerciseListAdapter;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.Repository.TrainingRepository;

public class NewExerciseActivity extends AppCompatActivity {


    private EditText exerciseEditText,exerciseRepsEditText, exerciseSetsEditText;
    private Button saveExerciseButton;
    private String exerciseName, exerciseReps, exerciseSets;
    private TrainingRepository trainingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        trainingRepository = new TrainingRepository(getApplication());
        SetUI();
        saveExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseName = exerciseEditText.getText().toString();
                exerciseReps = exerciseRepsEditText.getText().toString();
                exerciseSets = exerciseSetsEditText.getText().toString();
                if(exerciseName.matches("") || exerciseReps.matches("") ||
                        exerciseSets.matches("")){
                    Toast.makeText(getApplicationContext(),"You did not entered exercise info!",Toast.LENGTH_LONG).show();
                }else{
                    trainingRepository.insertExercise(new Exercise(exerciseName,Integer.parseInt(exerciseReps),Integer.parseInt(exerciseSets)));
                    finish();
                }
            }
        });

    }

    void SetUI(){
        exerciseEditText = findViewById(R.id.addExerciseName);
        saveExerciseButton = findViewById(R.id.button_save);
        exerciseRepsEditText = findViewById(R.id.addExerciseReps);
        exerciseSetsEditText = findViewById(R.id.addExerciseSets);
    }
}