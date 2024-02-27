package com.kochipek.noteapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kochipek.noteapp.R;
import com.kochipek.noteapp.View.MainActivity;
import com.kochipek.noteapp.View.UpdateNoteScreen;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.data.Model.Notes;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesViewHolder> {
    List<Notes> notes;
    MainActivity mainActivity;
    NoteAdapter adapter;

    public NoteAdapter(List<Notes> notes, MainActivity mainActivity) {
        this.adapter = adapter;
        this.notes = notes;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public NoteAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NotesViewHolder holder, int position) {

        Notes note = notes.get(position);

        holder.date.setText(note.date);
        holder.title.setText(note.title);
        holder.subtitle.setText(note.subtitle);

        switch (note.notePriority) {
            case "1":
                holder.priorityIndicator.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.priorityIndicator.setBackgroundResource(R.drawable.yellow_shape);                break;
            case "3":
                holder.priorityIndicator.setBackgroundResource(R.drawable.red_shape);                break;
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNoteScreen.class);
            intent.putExtra("id", note.id);
            intent.putExtra("title", note.title);
            intent.putExtra("subtitle", note.subtitle);
            intent.putExtra("priority", note.notePriority);
            intent.putExtra("notes", note.notes);
            mainActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, date;
        View priorityIndicator;
        public NotesViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.noteTitle);
            subtitle = itemView.findViewById(R.id.noteSubtitle);
            date = itemView.findViewById(R.id.noteDate);
            priorityIndicator = itemView.findViewById(R.id.notePriorityIndicator);
        }
    }
}
