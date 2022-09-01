package com.example.keepnotes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Notes> arrNotes;
    DatabaseHelper databaseHelper;
    private RecyclerView.RecyclerListener listener;

    RecyclerViewAdapter(Context context, ArrayList<Notes> arrNotes, DatabaseHelper databaseHelper) {
        this.context = context;
        this.arrNotes = arrNotes;
        this.databaseHelper = databaseHelper;
    }

    public RecyclerViewAdapter(ArrayList<Notes> arrNotes, RecyclerView.RecyclerListener listener){
        this.arrNotes = arrNotes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(arrNotes.get(position).title);
        holder.body.setText(arrNotes.get(position).text);
        holder.index.setText(String.valueOf(position + 1));

        holder.llView.setOnLongClickListener(view -> {
            showView(position);
            return true;
        });


        holder.llView.setOnClickListener(view -> {
            Intent iNext = new Intent(context, ViewActivity.class);
            iNext.putExtra("title",arrNotes.get(position).title);
            iNext.putExtra("text",arrNotes.get(position).text);
            context.startActivity(iNext);
        });
    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, body, index;
        CardView llView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title_view);
            body = itemView.findViewById(R.id.text_text_view);
            index = itemView.findViewById(R.id.index);
            llView = itemView.findViewById(R.id.card_View);
            databaseHelper = DatabaseHelper.getDatabase(context);
        }
    }

    private void showView(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setTitle("Delete view")
                .setMessage("Are you sure to delete")
                .setIcon(R.drawable.ic_baseline_delete_24)
                .setPositiveButton("yes", (dialogInterface, i) -> databaseHelper.notesDao().deleteById(arrNotes.get(position).id))
                .setNegativeButton("No", (dialogInterface, i) -> {

                });
        alert.show();
    }
}

