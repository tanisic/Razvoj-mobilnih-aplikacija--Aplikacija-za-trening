package com.example.rmaprojekt.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rmaprojekt.Adapter.ExerciseListAdapter;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.ExerciseClickInterface;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.ExerciseViewModel;
import com.example.rmaprojekt.ViewModelFactory.ExerciseViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity implements ExerciseClickInterface {
    private RecyclerView recyclerView;
    private FloatingActionButton addExerciseFab;
    private ExerciseViewModel exerciseViewModel;
    private ExerciseListAdapter exerciseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        recyclerView = findViewById(R.id.recyclerview);
        addExerciseFab = findViewById(R.id.fab);
        exerciseListAdapter = new ExerciseListAdapter(Exercise.itemCallback,this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(exerciseListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exerciseViewModel = new ViewModelProvider(this, new ExerciseViewModelFactory(this.getApplication(), "params")).get(ExerciseViewModel.class);
        exerciseViewModel.getAllExercises().observe(this, exercises -> {
            exerciseListAdapter.submitList(exercises);
        });
        new ItemTouchHelper(exerciseTouchHelperCallback).attachToRecyclerView(recyclerView);
        addExerciseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExercisesActivity.this, NewExerciseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Exercise> exerciseList= new ArrayList<>(exerciseListAdapter.getCurrentList());
        exerciseListAdapter.submitList(exerciseList);
    }

    ItemTouchHelper.SimpleCallback exerciseTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

    @Override
    public void onDelete(int position) {
        List<Exercise> exerciseList= new ArrayList<>(exerciseListAdapter.getCurrentList());
        exerciseViewModel.removeExercise(position);
        exerciseListAdapter.notifyItemRemoved(position);
        exerciseListAdapter.submitList(exerciseList);


    }
}