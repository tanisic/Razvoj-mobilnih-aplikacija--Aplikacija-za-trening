package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rmaprojekt.R;

public class MenuActivity extends AppCompatActivity {

    private Button btnRoutines, btnExercises, btnTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        setTitle("Shapeit Workout Planner");
        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseActivity();
            }
        });
        btnRoutines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routineActivity();
            }
        });
        btnTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainActivity();
            }
        });
    }

    public void setUI() {
        btnRoutines = findViewById(R.id.buttonRoutines);
        btnExercises = findViewById(R.id.buttonExercises);
        btnTrain = findViewById(R.id.buttonTrain);
    }

    private void exerciseActivity() {
        Intent intent = new Intent(this, ExercisesActivity.class);
        startActivity(intent);
    }

    private void routineActivity() {
        Intent intent = new Intent(this, RoutinesActivity.class);
        startActivity(intent);
    }

    private void trainActivity() {
        Intent intent = new Intent(this, ChooseTrainingActivity.class);
        startActivity(intent);
    }
}
