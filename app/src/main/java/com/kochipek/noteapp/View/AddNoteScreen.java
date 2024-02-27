package com.kochipek.noteapp.View;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.data.Model.Notes;
import com.kochipek.noteapp.databinding.AddNoteScreenBinding;

import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

public class AddNoteScreen extends AppCompatActivity {
    private AddNoteScreenBinding binding;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddNoteScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        setupPriorityButtons();
        binding.addNoteFab.setOnClickListener(v -> saveNote());
    }

    private void setupPriorityButtons() {
        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.green_shape_with_arrow);
            binding.yellowPriority.setImageResource(R.drawable.yellow_shape);
            binding.redPriority.setImageResource(R.drawable.red_shape);
            priority = "1";
        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.yellowPriority.setImageResource(R.drawable.yellow_shape_with_arrow);
            binding.greenPriority.setImageResource(R.drawable.green_shape);
            binding.redPriority.setImageResource(R.drawable.red_shape);
            priority = "2";
        });

        binding.redPriority.setOnClickListener(v -> {
            binding.redPriority.setImageResource(R.drawable.red_shape_with_arrow);
            binding.greenPriority.setImageResource(R.drawable.green_shape);
            binding.yellowPriority.setImageResource(R.drawable.yellow_shape);
            priority = "3";
        });
    }

    private void saveNote() {
        // Retrieve values from EditTexts when saving the note
        String title = binding.addNoteTitle.getText().toString();
        String subtitle = binding.addNoteSubtitle.getText().toString();
        String description = binding.addNoteDescription.getText().toString();
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(subtitle) && !TextUtils.isEmpty(description)) {
            createNote(title, subtitle, description);
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNote(String title, String subtitle, String description) {
        Date date = new Date();
        CharSequence sequence = android.text.format.DateFormat.format("dd-MM HH:mm", date.getTime());
        Notes notes = new Notes();
        notes.title = title;
        notes.subtitle = subtitle;
        notes.notes = description;
        notes.date = sequence.toString();
        notes.notePriority = priority;
        notesViewModel.insertNotes(notes);
        finish();
    }
}
