package com.example.rmaprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rmaprojekt.Adapter.ExerciseListAdapter;
import com.example.rmaprojekt.Entities.Exercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExercisesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        recyclerView = findViewById(R.id.recyclerview);
        addExerciseFab=findViewById(R.id.fab);
        final ExerciseListAdapter adapter = new ExerciseListAdapter(
                new ExerciseListAdapter.ExerciseDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addExerciseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewExerciseActivity.class);
                startActivity(intent);

            }
        });
    }
}