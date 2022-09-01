package com.example.keepnotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;

@Database(entities = Notes.class,exportSchema = false,version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static final String DB_NAME = "NotesDB";
    private  static DatabaseHelper instance;


    public static synchronized DatabaseHelper getDatabase(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context,DatabaseHelper.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();


}
