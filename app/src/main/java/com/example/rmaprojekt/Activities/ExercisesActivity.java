package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Adapter.ExerciseAdapter;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.ExerciseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExercisesActivity extends AppCompatActivity {
    public static final int ADD_EXERCISE_REQUEST = 1;
    public static final int EDIT_EXERCISE_REQUEST = 2;
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    private ExerciseViewModel exerciseViewModel;
    private ExerciseAdapter exerciseAdapter;

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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                exerciseViewModel.delete(exerciseAdapter.getExerciseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ExercisesActivity.this, "Exercise deleted!", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        exerciseAdapter.setOnExerciseClickListener(new ExerciseAdapter.OnExerciseClickListener() {
            @Override
            public void onExerciseClick(Exercise exercise) {
                Intent intent = new Intent(ExercisesActivity.this, AddEditExerciseActivity.class);
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_NAME, exercise.getName());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_REPS, exercise.getReps());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_SETS, exercise.getSets());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ID, exercise.getID());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE_SECONDS, exercise.getExercise_pause_seconds());
                startActivityForResult(intent, EDIT_EXERCISE_REQUEST);
            }
        });

        addExerciseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExercisesActivity.this, AddEditExerciseActivity.class);
                startActivityForResult(intent, ADD_EXERCISE_REQUEST);
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

        if (requestCode == ADD_EXERCISE_REQUEST && resultCode == RESULT_OK) {
            String exerciseName = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_NAME);
            int exerciseReps = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_REPS, 1);
            int exerciseSets = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_SETS, 1);
            int exercisePauseSeconds = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_SECONDS,10);
            Exercise exercise = new Exercise(exerciseName, exerciseReps, exerciseSets,exercisePauseSeconds);
            exerciseViewModel.insert(exercise);
            Toast.makeText(ExercisesActivity.this, "Exercise saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_EXERCISE_REQUEST && resultCode == RESULT_OK) {
            long id = data.getLongExtra(AddEditExerciseActivity.EXTRA_EXERCISE_ID, -1);

            if (id == -1) {
                Toast.makeText(ExercisesActivity.this, "Exercise can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String exerciseName = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE_NAME);
            int exerciseReps = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_REPS, 1);
            int exerciseSets = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_SETS, 1);
            int exercisePauseSeconds = data.getIntExtra(AddEditExerciseActivity.EXTRA_EXERCISE_SECONDS,10);
            Exercise exercise = new Exercise(exerciseName, Integer.valueOf(exerciseReps), Integer.valueOf(exerciseSets),exercisePauseSeconds);
            exercise.setID(id);
            exerciseViewModel.update(exercise);
            Toast.makeText(ExercisesActivity.this, "Exercise updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(ExercisesActivity.this, "Exercise not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.exercises_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_exercises:
                exerciseViewModel.deleteAllExercises();
                Toast.makeText(this, "All exercises deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}