package com.example.keepnotes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {


    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private Toolbar toolbar1;
    RecyclerViewAdapter recyclerViewAdapter;
    String []arr = {"Shortfilm Script ","Artificial Intelligence"};
    String []arr2 = {"We bought an old house, my friend and. He's i change the the entire life of" +
            " home. ",
            "In Computer Science, AI, also called machine intelligence demonstrated by machines, " +
                    "in contrast to natural"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up recycler view
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter= new RecyclerViewAdapter(arr, arr2,2);
        recyclerView.setAdapter(recyclerViewAdapter);



        //Setting custom Toolbar
        toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        //Moving from MainActivity to add_Notes Activity
        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, add_activity.class)));
    }
}
