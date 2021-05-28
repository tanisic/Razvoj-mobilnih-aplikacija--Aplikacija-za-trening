package com.example.rmaprojekt.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.Repository.TrainingRepository;

public class AddExerciseActivity extends AppCompatActivity {

    public static final String EXTRA_EXERCISE_NAME =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_NAME";
    public static final String EXTRA_EXERCISE_SETS =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_SETS";
    public static final String EXTRA_EXERCISE_REPS =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_REPS";
    private EditText exerciseEditText, exerciseRepsEditText, exerciseSetsEditText;
    private Button saveExerciseButton;
    private String exerciseName, exerciseReps, exerciseSets;
    private TrainingRepository trainingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        trainingRepository = new TrainingRepository(getApplication());
        SetUI();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Exercise");
        }

    private void saveExercise(){
        exerciseName = exerciseEditText.getText().toString();
        exerciseReps = exerciseRepsEditText.getText().toString();
        exerciseSets = exerciseSetsEditText.getText().toString();
        if (exerciseName.trim().isEmpty() || exerciseReps.trim().isEmpty() || exerciseSets.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "You did not entered exercise info!", Toast.LENGTH_LONG).show();
            return;
        } else {
            Intent data = new Intent();
            data.putExtra(EXTRA_EXERCISE_NAME,exerciseName);
            data.putExtra(EXTRA_EXERCISE_REPS,exerciseReps);
            data.putExtra(EXTRA_EXERCISE_SETS,exerciseSets);
            setResult(RESULT_OK,data);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_exercise_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_exercise:
                saveExercise();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    void SetUI() {
        exerciseEditText = findViewById(R.id.addExerciseName);
        saveExerciseButton = findViewById(R.id.button_save);
        exerciseRepsEditText = findViewById(R.id.addExerciseReps);
        exerciseSetsEditText = findViewById(R.id.addExerciseSets);
    }
}