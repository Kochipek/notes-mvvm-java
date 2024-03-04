package com.kochipek.noteapp.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.kochipek.noteapp.Adapter.NoteAdapter;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.databinding.FragmentNotesFeedBinding;

import java.util.ArrayList;

public class NotesFeedFragment extends Fragment {
    private FragmentNotesFeedBinding binding;
    private NoteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesFeedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NotesViewModel notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        NavController navController = NavHostFragment.findNavController(NotesFeedFragment.this);
        adapter = new NoteAdapter(new ArrayList<>(), (MainActivity) requireActivity()); // Initialize adapter with empty list

        notesViewModel.getAllNotes.observe(getViewLifecycleOwner(), notes -> {
            adapter.updateNotes(notes); // Update adapter data when new notes are received
            binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
            binding.recyclerView.setAdapter(adapter);
        });

        binding.floatingActionButton.setOnClickListener(v -> navController.navigate(R.id.action_notesFeedFragment_to_addNoteFragment));
      }
}