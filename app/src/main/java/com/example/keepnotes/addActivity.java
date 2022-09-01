package com.example.keepnotes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class addActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar_add = findViewById(R.id.toolbar_add_activity);
        setSupportActionBar(toolbar_add);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar_add.setNavigationIcon(R.drawable.back_button);
        toolbar_add.setNavigationOnClickListener(view -> onBackPressed());
        EditText titleText = findViewById(R.id.add_activity_title);
        EditText bodyText = findViewById(R.id.add_activity_text);
        Button saveBtn = findViewById(R.id.button);
        DatabaseHelper database = DatabaseHelper.getDatabase(this);


        saveBtn.setOnClickListener(view -> {
            String titleBody = titleText.getText().toString();
            String textBody = bodyText.getText().toString();

            if (titleBody.equals("") && textBody.equals("")) {
                Toast.makeText(addActivity.this, "Fields can't be empty",
                        Toast.LENGTH_LONG).show();
            } else {
                database.notesDao().addNotes(new Notes(titleBody, textBody));
                finish();
            }
        });
    }
}