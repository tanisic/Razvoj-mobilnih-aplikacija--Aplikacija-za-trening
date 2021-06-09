package com.example.rmaprojekt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.rmaprojekt.Adapter.RoutineAdapter;
import com.example.rmaprojekt.Entities.Routine;
import com.example.rmaprojekt.R;
import com.example.rmaprojekt.ViewModels.RoutineViewModel;

import java.util.List;

public class ChooseTrainingActivity extends AppCompatActivity {

    private RoutineAdapter adapter;
    private RoutineViewModel routineViewModel;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_training);
        SetUI();
        routineViewModel = new ViewModelProvider(this).get(RoutineViewModel.class);
        adapter = new RoutineAdapter();
        recyclerView.setAdapter(adapter);
        routineViewModel.getAllRoutines().observe(this, new Observer<List<Routine>>() {
            @Override
            public void onChanged(List<Routine> routines) {
                adapter.setRoutines(routines);
            }
        });
        adapter.setOnRoutineClickListener(new RoutineAdapter.OnRoutineClickListener() {
            @Override
            public void onRoutineClick(Routine routine) {
                Intent intent = new Intent(ChooseTrainingActivity.this,TrainActivity.class);
                intent.putExtra(AddEditRoutineActivity.EXTRA_ROUTINE_ID,routine.getID());
                intent.putExtra(AddEditRoutineActivity.EXTRA_ROUTINE_NAME,routine.getRoutineName());
                startActivity(intent);
            }
        });
    }

    private void SetUI(){
        recyclerView = findViewById(R.id.recyclerviewTrainRoutine);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
}