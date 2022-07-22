package com.example.keepnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    static List<model> arrNotes = new ArrayList<>();
    static RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    android.widget.SearchView search;

    public static void addNotes(String title, String text) {
        arrNotes.add(new model(title, text));
        adapter.notifyItemInserted(arrNotes.size() - 1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.searchView);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);


        adapter = new RecyclerViewAdapter(this, arrNotes);
        recyclerView.setAdapter(adapter);


        //setting up recycler view
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //Setting custom Toolbar
        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);


        //Moving from MainActivity to add_Notes Activity
        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, add_activity.class);
            startActivity(intent);
        });


    }

    private void filterList(String text) {

        ArrayList<model> filteredList = new ArrayList<>();
        for (model item : arrNotes) {
            if (item.text_model.toLowerCase().contains(text.toLowerCase()) || item.tite_model.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (!filteredList.isEmpty())
                adapter.setFilteredList(filteredList);

        }

    }

}
