package com.kochipek.noteapp.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.kochipek.noteapp.Dao.NotesDao;
import com.kochipek.noteapp.Database.NotesDb;
import com.kochipek.noteapp.Model.Notes;

import java.util.List;

public class NoteRepository {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;

    // burada overload yaparak birden fazla parametre alabiliriz
    public void NotesRepository(Context context){
        NotesDb notesDb = NotesDb.getDatabaseInstance(context);
        notesDao = notesDb.notesDao();
        getAllNotes = notesDao.getAllNotes();
}
    void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }
    void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
}
