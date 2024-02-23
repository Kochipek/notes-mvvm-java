package com.kochipek.noteapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.floatingActionButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddNoteScreen.class)));
    }
}