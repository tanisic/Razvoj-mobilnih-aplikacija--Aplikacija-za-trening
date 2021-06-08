package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.rmaprojekt.Adapter.RoutineAdapter;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.Entities.RoutineExercise;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.ExerciseViewModel;
import com.example.rmaprojekt.ViewModels.RoutineViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RoutinesActivity extends AppCompatActivity {

    private static final int ADD_ROUTINE_REQUEST = 1;
    private static final int EDIT_ROUTINE_REQUEST = 2;
    private RecyclerView recyclerViewRoutines;
    private FloatingActionButton floatingActionButton;
    private RoutineViewModel routineViewModel;
    private ExerciseViewModel exerciseViewModel;
    private RoutineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        recyclerViewRoutines = findViewById(R.id.recyclerviewRoutines);
        recyclerViewRoutines.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRoutines.setHasFixedSize(true);
        floatingActionButton = findViewById(R.id.fabRoutines);
        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
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
                Intent intent = new Intent(RoutinesActivity.this, AddEditRoutineActivity.class);
                intent.putExtra(AddEditRoutineActivity.EXTRA_ROUTINE_ID, (long) routine.getID());
                startActivityForResult(intent, EDIT_ROUTINE_REQUEST);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoutinesActivity.this, AddEditRoutineActivity.class);
                startActivityForResult(intent, ADD_ROUTINE_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Routine routine = adapter.getRoutineAt(viewHolder.getAdapterPosition());
                routineViewModel.deleteRoutine(routine);
                routineViewModel.deleteCrossRef(routine.getID());
                Toast.makeText(RoutinesActivity.this, "Routine deleted!", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerViewRoutines);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ROUTINE_REQUEST && resultCode == RESULT_OK) {
            String routineName = data.getStringExtra(AddEditRoutineActivity.EXTRA_ROUTINE_NAME);
            Routine routine = new Routine(routineName);
            long routineID = routineViewModel.insert(routine);
            String[] exerciseIdsString = data.getStringExtra(AddEditRoutineActivity.EXTRA_EXERCISES_IDS)
                    .trim().split("\\s+");
            long[] exerciseIds = new long[exerciseIdsString.length];
            int i = 0;
            Log.d("exercise IDS", exerciseIds.toString());
            for (String string : exerciseIdsString) {
                exerciseIds[i] = Long.parseLong(string);
                RoutineExercise routineExercise = new RoutineExercise(routineID, exerciseIds[i]);
                routineViewModel.insertRoutineExercise(routineExercise);
                i++;
            }


            Toast.makeText(RoutinesActivity.this, "Routine saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_ROUTINE_REQUEST && resultCode == RESULT_OK) {
            long id = data.getLongExtra(AddEditRoutineActivity.EXTRA_ROUTINE_ID, -1);

            if (id == -1) {
                Toast.makeText(RoutinesActivity.this, "Routine can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String routineName = data.getStringExtra(AddEditRoutineActivity.EXTRA_ROUTINE_NAME);
            Routine routine = new Routine(routineName);
            routine.setID(id);
            routineViewModel.update(routine);
            //exerciseViewModel.updateRoutineWithExercises(id,exerciseList);
            Toast.makeText(RoutinesActivity.this, "Routine updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(RoutinesActivity.this, "Routine not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.routines_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_routines:
                routineViewModel.deleteAllRoutines();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}