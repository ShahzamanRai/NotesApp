package com.example.keepnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

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


    ArrayList<Notes> arrNotes = new ArrayList<>();
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    notesModelView modelView;
    DatabaseHelper database;
    android.widget.SearchView searchView;


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
                adapter.notifyDataSetChanged();
            }
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

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;

            }
        });
    }

    private void filterList(String text) {
        if (!text.trim().isEmpty()) {
            ArrayList<Notes> filteredList = new ArrayList<>();
            for (Notes notes : arrNotes) {
                if (notes.title.toLowerCase().contains(text.toLowerCase()) || notes.text.toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(notes);
                }
                adapter.setFilteredList(filteredList);
            }
        } else {
            adapter.setFilteredList(arrNotes);
        }
    }

}


