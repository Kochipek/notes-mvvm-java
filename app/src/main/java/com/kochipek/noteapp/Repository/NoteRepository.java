package com.kochipek.noteapp.Repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.kochipek.noteapp.data.local.Dao.NotesDao;
import com.kochipek.noteapp.data.local.Database.NotesDb;
import com.kochipek.noteapp.data.Model.Notes;

import java.util.List;

public class NoteRepository {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;

    // burada overload yaparak birden fazla parametre alabiliriz
    public NoteRepository(Application application) {
        NotesDb notesDb = NotesDb.getDatabaseInstance(application);
        notesDao = notesDb.notesDao();
        getAllNotes = notesDao.getAllNotes();
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
