package com.example.rmaprojekt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rmaprojekt.Adapter.RoutineAdapter;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.RoutineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RoutinesActivity extends AppCompatActivity {

    private static final int ADD_ROUTINE_REQUEST = 1;
    private static final int EDIT_ROUTINE_REQUEST = 2;
    private RecyclerView recyclerViewRoutines;
    private FloatingActionButton floatingActionButton;
    private RoutineViewModel routineViewModel;
    private RoutineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        SetupUI();
        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        adapter = new RoutineAdapter();
        recyclerViewRoutines.setAdapter(adapter);
        routineViewModel.getAllRoutines().observe(this, new Observer<List<Routine>>() {
            @Override
            public void onChanged(List<Routine> routines) {
                adapter.setRoutines(routines);
            }
        });
        adapter.setOnRoutineClickListener(new RoutineAdapter.OnRoutineClickListener() {
            @Override
            public void onRoutineClick(Routine routine) {
                Intent intent = new Intent(RoutinesActivity.this,AddEditRoutineActivity.class);
                intent.putExtra()
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoutinesActivity.this, AddEditRoutineActivity.class);
                startActivityForResult(intent, ADD_ROUTINE_REQUEST);
            }
        });


    }

    private void SetupUI() {
        recyclerViewRoutines = findViewById(R.id.recyclerviewRoutines);
        floatingActionButton = findViewById(R.id.fabRoutines);
    }
}