package com.example.keepnotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM notesTable")
    List<Notes> getAllNotes();

    @Query("SELECT * FROM notesTable")
    LiveData<List<Notes>> findAllNotes();

    @Insert
    void addNotes(Notes note);

    @Update
    void updateNotes(Notes note);

    @Delete
    void deleteNotes(Notes note);


}
