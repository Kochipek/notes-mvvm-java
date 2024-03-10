package com.kochipek.noteapp.View;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.kochipek.noteapp.R;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.data.Model.Notes;
import com.kochipek.noteapp.databinding.FragmentAddNoteBinding;

import java.util.Date;

public class AddNoteFragment extends Fragment {
    private FragmentAddNoteBinding binding;
    private NotesViewModel notesViewModel;
    private String priority = "1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
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

        String title = binding.addNoteTitle.getText().toString();
        String subtitle = binding.addNoteSubtitle.getText().toString();
        String description = binding.addNoteDescription.getText().toString();
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(subtitle) && !TextUtils.isEmpty(description)) {
            createNote(title, subtitle, description);
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
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
        Navigation.findNavController(requireView()).navigate(R.id.action_addNoteFragment_to_notesFeedFragment);
    }
}