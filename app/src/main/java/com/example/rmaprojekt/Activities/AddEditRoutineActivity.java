package com.example.rmaprojekt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.Entities.RoutineWithExercises;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.RoutineWithExercisesViewModel;

public class AddEditRoutineActivity extends AppCompatActivity {

    public static final String EXTRA_ROUTINE_NAME =
            "com.example.rmaprojekt.Activities.ADD_ROUTINE_NAME";
    public static final String EXTRA_ROUTINE_ID =
            "com.example.rmaprojekt.Activities.ADD_ROUTINE_ID";

    private EditText routineNameEditText;
    private String routineName;
    private RecyclerView recyclerViewRoutineWithExercises;
    private long routineID;
    private RoutineWithExercisesViewModel routineWithExercisesViewModel;
    private RoutineWithExercises routineWithExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);
        SetupUI();
        routineWithExercisesViewModel = new ViewModelProvider(this).get(RoutineWithExercisesViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ROUTINE_ID)) {
            setTitle("Edit Routine");
            routineID = intent.getLongExtra(EXTRA_ROUTINE_ID, 1);
            routineWithExercises = routineWithExercisesViewModel.getRoutineWithExercises(routineID);
        } else {
            setTitle("Add Routine");
        }
    }

    private void SetupUI() {
        routineNameEditText = findViewById(R.id.addRoutineName);
        recyclerViewRoutineWithExercises = findViewById(R.id.recyclerview_RoutineWithExercises);

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
            // dodati još "checkabilni recyclerview"

            long id = getIntent().getLongExtra(EXTRA_ROUTINE_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ROUTINE_ID, id);
            }
            setResult(RESULT_OK, data);
            finish();
        }
    }
}