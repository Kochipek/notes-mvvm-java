package com.kochipek.noteapp.data.local.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kochipek.noteapp.data.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void insertNotes(Notes... notes);

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    LiveData<List<Notes>> getAllNotes();

    @Query("DELETE FROM notes_table WHERE id = :id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes note);
}
