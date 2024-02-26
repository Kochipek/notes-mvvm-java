package com.kochipek.noteapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kochipek.noteapp.Adapter.NoteAdapter;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    NotesViewModel notesViewModel;
    ActivityMainBinding binding;
    RecyclerView notesRecycler;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.floatingActionButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddNoteScreen.class)));
        notesRecycler = findViewById(binding.recyclerView.getId());

        notesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NotesViewModel.class);
        notesViewModel.getAllNotes.observe(this, notes -> {
            notesRecycler.setLayoutManager(new GridLayoutManager(this, 3));
            notesRecycler.setAdapter(new NoteAdapter(adapter, notes, MainActivity.this));
        });
    }
}