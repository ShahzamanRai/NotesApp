package com.example.keepnotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class notesModelView extends AndroidViewModel {

    DatabaseHelper databaseHelper;

    public notesModelView(@NonNull Application application) {
        super(application);
        databaseHelper = DatabaseHelper.getDatabase(application.getApplicationContext());
    }

    public LiveData<List<Notes>> getAllNotes() {
        return databaseHelper.notesDao().findAllNotes();
    }

}
