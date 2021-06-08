package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Adapter.ExercisesInRoutineAdapter;
import com.example.rmaprojekt.Entities.Exercise;
import com.example.rmaprojekt.ExercisesInRoutineListener;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.ExerciseViewModel;
import com.example.rmaprojekt.ViewModels.RoutineViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddEditRoutineActivity extends AppCompatActivity implements ExercisesInRoutineListener {

    public static final String EXTRA_ROUTINE_NAME =
            "com.example.rmaprojekt.Activities.ADD_ROUTINE_NAME";
    public static final String EXTRA_ROUTINE_ID =
            "com.example.rmaprojekt.Activities.ADD_ROUTINE_ID";
    public static final String EXTRA_EXERCISES_IDS =
            "com.example.rmaprojekt.Activities.ADD_EXERCISES_IDS";


    private Button updateExercisesBTN;
    private EditText routineNameEditText;
    private String routineName;
    private RecyclerView recyclerView;
    private long routineID;
    List<Exercise> selectedExercises;
    private ExerciseViewModel exerciseViewModel;
    private RoutineViewModel routineViewModel;
    private ExercisesInRoutineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);
        SetupUI();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        adapter = new ExercisesInRoutineAdapter(this);
        recyclerView.setAdapter(adapter);
        exerciseViewModel.getAllExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                adapter.setExercises(exercises);
            }
        });
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        updateExercisesBTN.setVisibility(View.VISIBLE);
        updateExercisesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedExercises = adapter.getSelectedExercises();
            }
        });
        if (intent.hasExtra(EXTRA_ROUTINE_ID)) {
            setTitle("Edit Routine");
            routineID = intent.getLongExtra(EXTRA_ROUTINE_ID, 1);
            routineName = intent.getStringExtra(EXTRA_ROUTINE_NAME);
            routineNameEditText.setText(routineName);
            String[] exerciseIdsString = intent.getStringExtra(AddEditRoutineActivity.EXTRA_EXERCISES_IDS)
                    .trim().split("\\s+");
            if(exerciseIdsString.length > 0) {
            long[] exerciseIDS = new long[exerciseIdsString.length];
            int i = 0;
            selectedExercises = new ArrayList<Exercise>();
                for (String string : exerciseIdsString) {
                    try {
                        exerciseIDS[i] = Long.valueOf(string);
                    }catch (NumberFormatException e){ e.printStackTrace();}

                    selectedExercises.add(exerciseViewModel.getExerciseByID(exerciseIDS[i]));
                    i++;
                }
            }
            else{
                return;
            }
        } else {
            setTitle("Add Routine");
        }
    }


    private void SetupUI() {
        routineNameEditText = findViewById(R.id.addRoutineName);
        updateExercisesBTN = findViewById(R.id.updateExercises);
        recyclerView = findViewById(R.id.recyclerview_RoutineWithExercises);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_routine_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_routine:
                saveRoutine();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveRoutine() {
        routineName = routineNameEditText.getText().toString();
        if (routineName.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "You did not entered routine name!", Toast.LENGTH_LONG).show();
            return;
        } else {
            Intent data = new Intent();
            data.putExtra(EXTRA_ROUTINE_NAME, routineName);
            selectedExercises = adapter.getSelectedExercises();
            long id = getIntent().getLongExtra(EXTRA_ROUTINE_ID, -1);
            StringBuilder exerciseIds = new StringBuilder();
            for (Exercise exercise : selectedExercises) {
                exerciseIds.append(exercise.getID() + " ");
            }
            Log.d("IDS",exerciseIds.toString());
            data.putExtra(EXTRA_EXERCISES_IDS, exerciseIds.toString());
            if (id != -1) {
                data.putExtra(EXTRA_ROUTINE_ID, id);
            }
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void onExerciseInRoutineAction(Boolean isSelected) {
        if (isSelected) {
            updateExercisesBTN.setVisibility(View.VISIBLE);
        } else {
            updateExercisesBTN.setVisibility(View.GONE);
        }
    }
}