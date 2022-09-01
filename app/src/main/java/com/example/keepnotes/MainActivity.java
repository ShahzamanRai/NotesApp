package com.example.keepnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    static ArrayList<Notes> arrNotes = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    notesModelView modelView;
    DatabaseHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this, arrNotes, database);
        recyclerView.setAdapter(adapter);

        //setting up recycler view
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        modelView = new ViewModelProvider(this).get(notesModelView.class);
        modelView.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                arrNotes.clear();
                arrNotes.addAll(notes);
                adapter.notifyItemInserted(arrNotes.size()-1);            }
        });



        //Setting custom Toolbar
        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        //Moving from MainActivity to add_Notes Activity
        Button button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, addActivity.class);
            startActivity(intent);

        });

    }
}
