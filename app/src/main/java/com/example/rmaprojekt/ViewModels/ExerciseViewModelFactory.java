package com.example.rmaprojekt.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ExerciseViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;

    public ExerciseViewModelFactory(Application application, String param) {
        this.mApplication = application;
        this.mParam = param;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ExerciseViewModel(mApplication,mParam);
    }
}
