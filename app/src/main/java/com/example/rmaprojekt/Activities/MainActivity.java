package com.example.rmaprojekt.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rmaprojekt.R;

public class MainActivity extends AppCompatActivity {

    private Button btnRoutines,btnExercises,btnLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseActivity();
            }
        });
    }
    public void setUI(){
        btnRoutines = findViewById(R.id.buttonRoutines);
        btnExercises = findViewById(R.id.buttonExercises);
        btnLogs = findViewById(R.id.buttonLogs);
    }
    private void exerciseActivity(){
        Intent intent = new Intent(this, ExercisesActivity.class);
        startActivity(intent);
    }




    }
