package com.example.rmaprojekt.ViewHolders;

import android.service.controls.templates.TemperatureControlTemplate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaprojekt.R;

public class ExercisesViewHolder  extends RecyclerView.ViewHolder {

    private final TextView exerciseItemView;

    private ExercisesViewHolder(View itemView) {
        super(itemView);
        exerciseItemView = itemView.findViewById(R.id.exerciseTextView);
    }
    public void bind(String text) {
        exerciseItemView.setText(text);
    }
    public static ExercisesViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ExercisesViewHolder(view);
    }
}
