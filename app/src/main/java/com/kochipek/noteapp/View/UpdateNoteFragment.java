package com.kochipek.noteapp.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.ViewModel.NotesViewModel;
import com.kochipek.noteapp.data.Model.Notes;
import com.kochipek.noteapp.databinding.FragmentUpdateNoteBinding;

import java.util.Date;

public class UpdateNoteFragment extends Fragment {
    private FragmentUpdateNoteBinding binding;
    private String priority;
    private String uTitle, uSubtitle, uPriority, uNotes;
    private int uid;
    private NotesViewModel notesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
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
        if (getArguments() != null) {
            UpdateNoteFragmentArgs args = UpdateNoteFragmentArgs.fromBundle(getArguments());
            uid = args.getId();
            uTitle = args.getTitle();
            uSubtitle = args.getSubtitle();
            uPriority = args.getPriority();
            uNotes = args.getNotes();

            binding.updateNoteTitle.setText(uTitle);
            binding.updateNoteSubtitle.setText(uSubtitle);
            binding.updateNote.setText(uNotes);
        }
    }

    private void setupNoteUpdate() {
        binding.updateNoteFab.setOnClickListener(v -> {
            String title = binding.updateNoteTitle.getText().toString();
            String subtitle = binding.updateNoteSubtitle.getText().toString();
            String notes = binding.updateNote.getText().toString();

            if (title.isEmpty() || subtitle.isEmpty() || notes.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (priority == null) {
                priority = uPriority;
            }

            updateNote(title, subtitle, notes);
            requireActivity().getSupportFragmentManager().popBackStack();
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
            notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        }
        notesViewModel.updateNotes(note);
    }

  // bottom dialogu bagliyoruz
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setHasOptionsMenu(true);
  }

    @Override
    public void onCreateOptionsMenu(@NonNull android.view.Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
            bottomSheetDialog.setContentView(R.layout.delete_bottom_sheet);
            bottomSheetDialog.findViewById(R.id.delete_button_yes).setOnClickListener(v -> {
                notesViewModel.deleteNotes(uid);
                requireActivity().getSupportFragmentManager().popBackStack();
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.findViewById(R.id.delete_button_no).setOnClickListener(v -> bottomSheetDialog.dismiss());
            bottomSheetDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}