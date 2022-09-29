package com.example.keepnotes;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    DatabaseHelper databaseHelper;
    Notes notes;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Toolbar toolbar_add = findViewById(R.id.toolbar_update_activity);
        setSupportActionBar(toolbar_add);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar_add.setNavigationIcon(R.drawable.back_button);
        toolbar_add.setNavigationOnClickListener(view -> onBackPressed());

        // Finding Views and and initializing database
        EditText title = findViewById(R.id.update_activity_title);
        text = findViewById(R.id.update_activity_text);
        Button updateBtn = findViewById(R.id.Update_button);
        ImageView speech = findViewById(R.id.speech_update_activity);
        databaseHelper = DatabaseHelper.getDatabase(UpdateActivity.this);

        // Getting text from intent and displaying in editText for Update purpose
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


        //Updating Note by clicking on update button and shifting to MainActivity
        updateBtn.setOnClickListener(view -> {
            notes.setTitle(title.getText().toString());
            notes.setText(text.getText().toString());
            databaseHelper.notesDao().updateNotes(notes);
            Log.d(TAG, "onCreate: " + notes.title + notes.text);
            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(intent);
        });

//      adding speech icon on click listener to add text to EditText by speech input
        speech.setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to text");
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);

        });

    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            text.setText(text.getText().toString() + " " + result.get(0).toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

