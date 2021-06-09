package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rmaprojekt.R;

public class AddEditExerciseActivity extends AppCompatActivity {

    public static final String EXTRA_EXERCISE_NAME =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_NAME";
    public static final String EXTRA_EXERCISE_SETS =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_SETS";
    public static final String EXTRA_EXERCISE_REPS =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_REPS";
    public static final String EXTRA_EXERCISE_ID =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_ID";
    public static final String EXTRA_EXERCISE_SECONDS =
            "com.example.rmaprojekt.Activities.ADD_EXERCISE_SECONDS";
    private EditText exerciseNameEditText;
    private NumberPicker setsNumberPicker, repsNumberPicker;
    private String exerciseName;
    private int exerciseReps, exerciseSets;
    private TextView secondsTV;
    private SeekBar seekBar;
    private int exercisePauseSeconds;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        SetUI();
        repsNumberPicker.setMinValue(1);
        repsNumberPicker.setMaxValue(25);
        setsNumberPicker.setMinValue(1);
        setsNumberPicker.setMaxValue(15);
        seekBar.setMin(10);
        seekBar.setMax(500);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                secondsTV.setText(progress + " sec");
                exercisePauseSeconds = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_EXERCISE_ID)) {
            setTitle("Edit Exercise");
            exerciseNameEditText.setText(intent.getStringExtra(EXTRA_EXERCISE_NAME));
            repsNumberPicker.setValue(intent.getIntExtra(EXTRA_EXERCISE_REPS, 1));
            setsNumberPicker.setValue(intent.getIntExtra(EXTRA_EXERCISE_SETS, 1));
            seekBar.setProgress(intent.getIntExtra(EXTRA_EXERCISE_SECONDS, 10));
        } else {
            setTitle("Add Exercise");
        }
    }

    private void saveExercise() {
        exerciseName = exerciseNameEditText.getText().toString();
        exerciseReps = repsNumberPicker.getValue();
        exerciseSets = setsNumberPicker.getValue();
        exercisePauseSeconds = seekBar.getProgress();
        if (exerciseName.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "You did not entered exercise name!", Toast.LENGTH_LONG).show();
            return;
        } else {
            Intent data = new Intent();
            data.putExtra(EXTRA_EXERCISE_NAME, exerciseName);
            data.putExtra(EXTRA_EXERCISE_REPS, exerciseReps);
            data.putExtra(EXTRA_EXERCISE_SETS, exerciseSets);
            data.putExtra(EXTRA_EXERCISE_SECONDS, exercisePauseSeconds);

            long id = getIntent().getLongExtra(EXTRA_EXERCISE_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_EXERCISE_ID, id);
            }
            setResult(RESULT_OK, data);
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
        exerciseNameEditText = findViewById(R.id.addRoutineName);
        repsNumberPicker = findViewById(R.id.number_picker_reps);
        setsNumberPicker = findViewById(R.id.number_picker_sets);
        seekBar = findViewById(R.id.seekBar);
        secondsTV = findViewById(R.id.secondsTV);
    }
}