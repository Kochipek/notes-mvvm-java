package com.kochipek.noteapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.kochipek.noteapp.Adapter.NoteAdapter;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.SharedPreferencesHelper;
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
        // Check if the user is enter the app for the first time

        if (!SharedPreferencesHelper.isTutorialCompleted(requireActivity())) {
            startShowcaseWalkthrough();
        }

        NotesViewModel notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        NavController navController = NavHostFragment.findNavController(NotesFeedFragment.this);
        adapter = new NoteAdapter(new ArrayList<>(), (MainActivity) requireActivity()); // Initialize adapter with empty list

        binding.lowButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.highButton.setChecked(false);
                binding.dateButton.setChecked(false);
                notesViewModel.lotToHigh.observe(getViewLifecycleOwner(), notes -> adapter.updateNotes(notes));
            }
        });

        binding.highButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.lowButton.setChecked(false);
                binding.dateButton.setChecked(false);
                notesViewModel.highToLow.observe(getViewLifecycleOwner(), notes -> adapter.updateNotes(notes));
            }
        });

        binding.dateButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.lowButton.setChecked(false);
                binding.highButton.setChecked(false);
                notesViewModel.filterbydate.observe(getViewLifecycleOwner(), notes -> adapter.updateNotes(notes));
            }
        });

        notesViewModel.getAllNotes.observe(getViewLifecycleOwner(), notes -> {
            adapter.updateNotes(notes);
            if (notes.isEmpty()) {
                binding.noNotes.setVisibility(View.VISIBLE);
                binding.imageView2.setVisibility(View.VISIBLE);
                binding.recyclerView.setVisibility(View.GONE);
            } else {
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.noNotes.setVisibility(View.GONE);
                binding.imageView2.setVisibility(View.GONE);
                binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                binding.recyclerView.setAdapter(adapter);
            }
        });

        binding.floatingActionButton.setOnClickListener(v -> navController.navigate(R.id.action_notesFeedFragment_to_addNoteFragment));
    }

    private void startShowcaseWalkthrough() {
        ((MainActivity) requireActivity()).startShowcaseWalkthrough();
        SharedPreferencesHelper.setTutorialCompleted(requireContext(), true); // Marking tutorial as completed
    }
}
