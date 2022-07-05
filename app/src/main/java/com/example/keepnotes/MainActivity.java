package com.example.keepnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    RecyclerView.LayoutManager layoutManager;
    ArrayList<model> arrNotes = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText title = findViewById(R.id.add_activity_title);
        EditText body = findViewById(R.id.add_activity_text);
        Button button1 = findViewById(R.id.button);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);


        arrNotes.add(new model("Shahzaman", "Rai"));
        arrNotes.add(new model("Artificial intelligence", "Artificial intelligence (AI) is the ability of a computer or a robot controlled by a computer to do tasks that are usually done by humans because they require human intelligence and discernment."));
        arrNotes.add(new model("Shahzaman", "Rai"));
        arrNotes.add(new model("Artificial intelligence", "Artificial intelligence (AI) is the " +
                "ability of a computer or a robot controlled by a computer to do tasks that are usually done by humans because they require human intelligence and discernment."));
        arrNotes.add(new model("Shahzaman", "Rai"));
        arrNotes.add(new model("Shahzaman", "Rai"));
        arrNotes.add(new model("Artificial intelligence", "Artificial intelligence (AI) is the " +
                "ability of a computer or a robot controlled by a computer to do tasks that are usually done by humans because they require human intelligence and discernment."));
        arrNotes.add(new model("Shahzaman", "Rai"));
        arrNotes.add(new model("Shahzaman", "Rai"));
        arrNotes.add(new model("Artificial intelligence", "Artificial intelligence (AI) is the " +
                "ability of a computer or a robot controlled by a computer to do tasks that are usually done by humans because they require human intelligence and discernment."));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrNotes);
        recyclerView.setAdapter(adapter);


        //setting up recycler view
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //Setting custom Toolbar
        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        //Moving from MainActivity to add_Notes Activity
        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, add_activity.class)));
    }

}
