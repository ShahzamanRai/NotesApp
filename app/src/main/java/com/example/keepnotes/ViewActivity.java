package com.example.keepnotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Toolbar toolbar_add = findViewById(R.id.toolbar_view_activity);
        setSupportActionBar(toolbar_add);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar_add.setNavigationIcon(R.drawable.back_button);
        toolbar_add.setNavigationOnClickListener(view -> onBackPressed());

        TextView title = findViewById(R.id.view_activity_title);
        TextView text = findViewById(R.id.view_activity_text);
        ImageView btnUpdate = findViewById(R.id.view_update_button);

        String titleText = "";
        String bodyText = "";
        int id = 1;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titleText = extras.getString("title");
            bodyText = extras.getString("text");
            id = extras.getInt("id");

        }
        title.setText(titleText);
        text.setText(bodyText);

        title.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("title", title.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        });

        text.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text", text.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        });

        int finalId = id;
        btnUpdate.setOnClickListener(view -> {
            Intent iNext = new Intent(ViewActivity.this, UpdateActivity.class);
            iNext.putExtra("title", title.getText().toString());
            iNext.putExtra("text", text.getText().toString());
            iNext.putExtra("id", finalId);
            startActivity(iNext);
        });

    }
}