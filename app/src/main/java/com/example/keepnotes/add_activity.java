package com.example.keepnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class add_activity extends AppCompatActivity {

    public String[] arr = {"Shortfilm Script", "Artificial Intelligence"};
    public String[] arr2 = {"We bought an old house, my friend and. He's i change the the entire" +
            " life " +
            "of" +
            " home. ",
            "In Computer Science, AI, also called machine intelligence demonstrated by machines, " +
                    "in contrast to natural"};
    private Toolbar toolbar_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        toolbar_add = findViewById(R.id.toolbar_add_activity);
        setSupportActionBar(toolbar_add);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        toolbar_add.setNavigationIcon(R.drawable.back_button);
        toolbar_add.setNavigationOnClickListener(view -> onBackPressed());


    }

}