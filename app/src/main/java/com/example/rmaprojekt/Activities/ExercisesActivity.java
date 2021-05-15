package com.example.rmaprojekt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rmaprojekt.Adapter.ExerciseListAdapter;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.ExerciseViewModel;
import com.example.rmaprojekt.ViewModelFactory.ExerciseViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExercisesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    private ExerciseViewModel exerciseViewModel;
    public static final int NEW_EXERCISE_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        recyclerView = findViewById(R.id.recyclerview);
        addExerciseFab=findViewById(R.id.fab);
        final ExerciseListAdapter adapter = new ExerciseListAdapter(new ExerciseListAdapter.ExerciseDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exerciseViewModel = new ViewModelProvider(this,new ExerciseViewModelFactory(this.getApplication(),"params")).get(ExerciseViewModel.class);
        exerciseViewModel.getAllExercises().observe(this, exercises -> {
            adapter.submitList(exercises);
        });
        addExerciseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExercisesActivity.this,NewExerciseActivity.class);
                startActivityForResult(intent, NEW_EXERCISE_ACTIVITY_REQUEST_CODE);

            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_EXERCISE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Exercise exercise = new Exercise(data.getStringExtra(NewExerciseActivity.EXTRA_REPLY));
            exerciseViewModel.insert(exercise);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}