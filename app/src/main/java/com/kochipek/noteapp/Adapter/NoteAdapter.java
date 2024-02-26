package com.kochipek.noteapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kochipek.noteapp.R;
import com.kochipek.noteapp.View.MainActivity;
import com.kochipek.noteapp.data.Model.Notes;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesViewHolder> {
    List<Notes> notes;
    MainActivity mainActivity;
    NoteAdapter adapter;

    public NoteAdapter(NoteAdapter adapter, List<Notes> notes, MainActivity mainActivity) {
        this.adapter = adapter;
        this.notes = notes;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public NoteAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NotesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        public NotesViewHolder(View itemView) {
            super(itemView);

        }
    }
}
