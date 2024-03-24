package com.kochipek.noteapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kochipek.noteapp.data.Model.Notes;
import com.kochipek.noteapp.data.local.Dao.NotesDao;
import com.kochipek.noteapp.data.local.Database.NotesDb;

import java.util.List;

public class NoteRepository {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;
    public LiveData<List<Notes>> filterbydate;

    public LiveData<List<Notes>> searchbyquery;

    public NoteRepository(Application application) {
        NotesDb notesDb = NotesDb.getDatabaseInstance(application);
        notesDao = notesDb.notesDao();
        getAllNotes = notesDao.getAllNotes();
        highToLow = notesDao.highToLow();
        lowToHigh = notesDao.lowToHigh();
        filterbydate = notesDao.filterbydate();

    }
    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }
    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }

}
