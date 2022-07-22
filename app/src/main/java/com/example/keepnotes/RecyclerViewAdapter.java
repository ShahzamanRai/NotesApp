package com.example.keepnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<model> arrNotes;

    RecyclerViewAdapter(Context context, List<model> arrNotes) {
        this.context = context;
        this.arrNotes = arrNotes;
    }

    public void setFilteredList(List<model> filteredList) {
        this.arrNotes = filteredList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(arrNotes.get(position).tite_model);
        holder.body.setText(arrNotes.get(position).text_model);

    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, body;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title_view);
            body = itemView.findViewById(R.id.text_text_view);



        }
    }
}