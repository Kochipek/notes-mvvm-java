package com.kochipek.noteapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kochipek.noteapp.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    // ... -> varargs in kotlin
    void insertNotes(Notes... notes);
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    LiveData<List<Notes>> getAllNotes();

    @Query("DELETE FROM notes_table WHERE id = :id")
    @Delete
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes note);
}
