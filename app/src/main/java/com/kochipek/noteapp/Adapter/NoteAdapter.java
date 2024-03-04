package com.kochipek.noteapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.View.MainActivity;
import com.kochipek.noteapp.View.NotesFeedFragment;
import com.kochipek.noteapp.View.NotesFeedFragmentDirections;
import com.kochipek.noteapp.data.Model.Notes;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesViewHolder> {
    private List<Notes> notes;
    private MainActivity mainActivity;
    public NoteAdapter(List<Notes> notes, MainActivity mainActivity) {
        this.notes = notes;
        this.mainActivity = mainActivity;
    }
    // Method to update adapter data
    public void updateNotes(List<Notes> updatedNotes) {
        notes.clear();
        notes.addAll(updatedNotes);
        notifyDataSetChanged();
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

        holder.itemView.setOnClickListener(v -> {
            NotesFeedFragmentDirections.ActionNotesFeedFragmentToUpdateNoteFragment action =
                    NotesFeedFragmentDirections.actionNotesFeedFragmentToUpdateNoteFragment()
                            .setId(note.id)
                            .setTitle(note.title)
                            .setSubtitle(note.subtitle)
                            .setPriority(note.notePriority)
                            .setNotes(note.notes);
            Navigation.findNavController(v).navigate(action);
        });


        switch (note.notePriority) {
            case "1":
                holder.priorityIndicator.setBackgroundColor(Color.parseColor("#A8D38D"));
                break;
            case "2":
                holder.priorityIndicator.setBackgroundColor(Color.parseColor("#FDFD96"));
                break;
            case "3":
                holder.priorityIndicator.setBackgroundColor(Color.parseColor("#F1583C"));
                break;
        }
    }

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
