package com.kochipek.noteapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kochipek.noteapp.Repository.NoteRepository;
import com.kochipek.noteapp.data.Model.Notes;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NoteRepository noteRepository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lotToHigh;
    public LiveData<List<Notes>> filterbydate;

    public LiveData<List<Notes>> searchbyquery;

    public NotesViewModel(Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        getAllNotes = noteRepository.getAllNotes;
        highToLow = noteRepository.highToLow;
        lotToHigh = noteRepository.lowToHigh;
        filterbydate = noteRepository.filterbydate;

    }
    public void insertNotes(Notes notes){
        noteRepository.insertNotes(notes);
        getAllNotes = noteRepository.getAllNotes;
    }
    public void deleteNotes(int id){
        noteRepository.deleteNotes(id);
    }
    public void updateNotes(Notes notes){
        noteRepository.updateNotes(notes);
    }


}
