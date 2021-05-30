package com.example.rmaprojekt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

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

        if(intent.hasExtra(EXTRA_ROUTINE_ID)){
            setTitle("Edit Routine");
            routineID = intent.getLongExtra(EXTRA_ROUTINE_ID,1);
            routineWithExercises = routineWithExercisesViewModel.getRoutineWithExercises(routineID);
        }else{
            setTitle("Add Routine");
        }
    }

    private void SetupUI(){
        routineNameEditText = findViewById(R.id.addRoutineName);
        recyclerViewRoutineWithExercises = findViewById(R.id.recyclerview_RoutineWithExercises);

    }
}