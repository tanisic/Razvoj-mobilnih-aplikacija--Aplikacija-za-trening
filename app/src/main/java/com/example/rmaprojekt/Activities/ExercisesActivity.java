package com.example.rmaprojekt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rmaprojekt.Adapter.ExerciseAdapter;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.ExerciseClickInterface;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.ExerciseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExercisesActivity extends AppCompatActivity implements ExerciseClickInterface {
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    private ExerciseViewModel exerciseViewModel;
    private ExerciseAdapter exerciseAdapter;
    public static final int ADD_NOTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        setUI();
        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        exerciseAdapter = new ExerciseAdapter();
        recyclerView.setAdapter(exerciseAdapter);
        exerciseViewModel.getAllExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                exerciseAdapter.setExercises(exercises);
            }
        });
        addExerciseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExercisesActivity.this, AddExerciseActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });



    }

    private void setUI() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        addExerciseFab = findViewById(R.id.fab);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String exerciseName = data.getStringExtra(AddExerciseActivity.EXTRA_EXERCISE_NAME);
            String exerciseReps = data.getStringExtra(AddExerciseActivity.EXTRA_EXERCISE_REPS);
            String exerciseSets = data.getStringExtra(AddExerciseActivity.EXTRA_EXERCISE_SETS);
            Exercise exercise = new Exercise(exerciseName,
                    Integer.parseInt(exerciseReps),
                    Integer.parseInt(exerciseSets));
            exerciseViewModel.insert(exercise);
            Toast.makeText(ExercisesActivity.this, "Exercise saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(ExercisesActivity.this, "Exercise not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        exerciseAdapter.notifyDataSetChanged();
    }



    @Override
    public void onDelete(int position) {


    }
}