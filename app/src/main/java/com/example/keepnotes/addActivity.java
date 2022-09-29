package com.example.keepnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class addActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    EditText bodyText;

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
        bodyText = findViewById(R.id.add_activity_text);
        Button saveBtn = findViewById(R.id.button);
        ImageView speech = findViewById(R.id.speech_add_activity);
        DatabaseHelper database = DatabaseHelper.getDatabase(this);

        speech.setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to text");

            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            } catch (Exception e) {
                Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
            }
        });

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            bodyText.setText(bodyText.getText().toString() + " " + result.get(0).toString());
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}