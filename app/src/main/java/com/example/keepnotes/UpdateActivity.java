package com.example.keepnotes;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Notes notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Toolbar toolbar_add = findViewById(R.id.toolbar_update_activity);
        setSupportActionBar(toolbar_add);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar_add.setNavigationIcon(R.drawable.back_button);
        toolbar_add.setNavigationOnClickListener(view -> onBackPressed());

        EditText title = findViewById(R.id.update_activity_title);
        EditText text = findViewById(R.id.update_activity_text);
        Button updateBtn = findViewById(R.id.Update_button);
        databaseHelper = DatabaseHelper.getDatabase(UpdateActivity.this);

        String titleText = "";
        String bodyText = "";
        int id = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titleText = extras.getString("title");
            bodyText = extras.getString("text");
            id = extras.getInt("id");
        }
        title.setText(titleText);
        text.setText(bodyText);
        updateBtn.setText(R.string.update);

        notes = new Notes(id, titleText, bodyText);

        updateBtn.setOnClickListener(view -> {
            notes.setTitle(title.getText().toString());
            notes.setText(text.getText().toString());
            databaseHelper.notesDao().updateNotes(notes);
            Log.d(TAG, "onCreate: " + notes.title + notes.text);
            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}
