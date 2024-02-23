package com.kochipek.noteapp.View;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.data.Model.Notes;
import com.kochipek.noteapp.databinding.AddNoteScreenBinding;

import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

public class AddNoteScreen extends AppCompatActivity {
    private AddNoteScreenBinding binding;
    NotesViewModel notesViewModel;
    String title, subtitle, description;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddNoteScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        title = binding.noteTitle.getText().toString();
        subtitle = binding.noteSubtitle.getText().toString();
        description = binding.noteDescription.getText().toString();
        createNote(title, subtitle, description);

        notesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NotesViewModel.class);
        binding.redPriority.setOnClickListener(v -> {
            priority = "3";
            binding.noteTitle.setBackgroundColor(getResources().getColor(R.color.red));
        });
        binding.greenPriority.setOnClickListener(v -> {
            priority = "1";
            binding.noteTitle.setBackgroundColor(getResources().getColor(R.color.green));
        });
        binding.yellowPriority.setOnClickListener(v -> {
            priority = "2";
            binding.noteTitle.setBackgroundColor(getResources().getColor(R.color.yellow));
        });
    }

    private void createNote(String title, String subtitle, String description) {
        Date date = new Date();
        CharSequence sequence = android.text.format.DateFormat.format("dd-MM-yyyy", date.getTime());
        Notes notes = new Notes();
        notes.title = title;
        notes.subtitle = subtitle;
        notes.notes = description;
        notes.date = sequence.toString();
        notes.notePriority = priority;
        binding.addNoteFab.setOnClickListener(v -> {
            notesViewModel.insertNotes(notes);
            finish();
        });
    }
}
