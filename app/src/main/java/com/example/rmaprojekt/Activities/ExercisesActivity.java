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
    final ExerciseListAdapter adapter = new ExerciseListAdapter(new ExerciseListAdapter.ExerciseDiff());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        recyclerView = findViewById(R.id.recyclerview);
        addExerciseFab=findViewById(R.id.fab);

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
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}