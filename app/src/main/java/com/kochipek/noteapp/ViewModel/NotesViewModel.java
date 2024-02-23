package com.kochipek.noteapp.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kochipek.noteapp.Repository.NoteRepository;
import com.kochipek.noteapp.data.Model.Notes;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NoteRepository noteRepository;
    public LiveData<List<Notes>> getAllNotes;
    public NotesViewModel(Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        getAllNotes = noteRepository.getAllNotes;
    }
    public void insertNotes(Notes notes){
        noteRepository.insertNotes(notes);
    }
    public void deleteNotes(int id){
        noteRepository.deleteNotes(id);
    }
    public void updateNotes(Notes notes){
        noteRepository.updateNotes(notes);
    }
}
