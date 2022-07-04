package com.example.keepnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

    String[] arr;
    String[] arr2;
    int size;

    public RecyclerViewAdapter(String[] arr, String[] arr2, int s) {
        this.arr = arr;
        this.arr2 = arr2;
        this.size = s;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent,
                false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.titleView.setText(arr[position]);
        holder.textView.setText(arr2[position]);

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView textView;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.text_title_view);
            textView = itemView.findViewById(R.id.text_text_view);

        }
    }
}
