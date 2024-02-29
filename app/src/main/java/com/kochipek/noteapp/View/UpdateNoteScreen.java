package com.kochipek.noteapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.Repository.NoteRepository;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.data.Model.Notes;
import com.kochipek.noteapp.databinding.UpdateNotesScreenBinding;

import java.util.Date;

public class UpdateNoteScreen extends AppCompatActivity {
    private NotesViewModel notesViewModel;
    private UpdateNotesScreenBinding binding;
    private String priority;
    private String uTitle, uSubtitle, uPriority, uNotes;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UpdateNotesScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getNoteData();
        setupPriorityButtons();
        setupNoteUpdate();
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
    private void getNoteData() {
        Intent intent = getIntent();
        uid = intent.getIntExtra("id", 0);
        uTitle = intent.getStringExtra("title");
        uSubtitle = intent.getStringExtra("subtitle");
        uPriority = intent.getStringExtra("priority");
        uNotes = intent.getStringExtra("notes");

        binding.updateNoteTitle.setText(uTitle);
        binding.updateNoteSubtitle.setText(uSubtitle);
        binding.updateNote.setText(uNotes);
    }

    private void setupNoteUpdate() {
        binding.updateNoteFab.setOnClickListener(v -> {
            String title = binding.updateNoteTitle.getText().toString();
            String subtitle = binding.updateNoteSubtitle.getText().toString();
            String notes = binding.updateNote.getText().toString();

            if (title.isEmpty() || subtitle.isEmpty() || notes.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (priority == null) {
                priority = uPriority;
            }

            updateNote(title, subtitle, notes);
        });
    }

    private void updateNote(String title, String subtitle, String notes) {
        Notes note = new Notes();
        note.id = uid;
        note.title = title;
        note.subtitle = subtitle;
        note.notes = notes;
        Date date = new Date();
        CharSequence sequence = android.text.format.DateFormat.format("dd-MM HH:mm", date.getTime());
        note.date = sequence.toString();
        note.notePriority = priority;

        if (notesViewModel == null) {
            notesViewModel = new NotesViewModel(getApplication());
        }

        notesViewModel.updateNotes(note);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            showDeleteConfirmationDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteConfirmationDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNoteScreen.this, R.style.BottomSheetStyle);
        View view = LayoutInflater.from(UpdateNoteScreen.this).inflate(R.layout.delete_bottom_sheet, findViewById(R.id.bottomDeleteSheet));
        bottomSheetDialog.setContentView(view);

        view.findViewById(R.id.delete_button_no).setOnClickListener(v -> bottomSheetDialog.dismiss());
        view.findViewById(R.id.delete_button_yes).setOnClickListener(v -> deleteNote());
        bottomSheetDialog.show();
    }

    private void deleteNote() {
        if (notesViewModel == null) {
            notesViewModel = new NotesViewModel(getApplication());
        }
        notesViewModel.deleteNotes(uid);
        finish();
    }
}
