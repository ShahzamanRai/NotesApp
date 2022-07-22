package com.example.keepnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class add_activity extends AppCompatActivity {

    MainActivity activity;
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

        EditText titleText = findViewById(R.id.add_activity_title);
        EditText bodyText = findViewById(R.id.add_activity_text);
        Button saveBtn = findViewById(R.id.button);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleBody = titleText.getText().toString();
                String textBody = bodyText.getText().toString();

                if (titleBody.equals("") && textBody.equals("")) {
                    Toast.makeText(add_activity.this, "Fields can't be empty",
                            Toast.LENGTH_LONG).show();
                } else {
                    MainActivity.addNotes(titleBody, textBody);
                    finish();
                }

            }
        });


    }

}