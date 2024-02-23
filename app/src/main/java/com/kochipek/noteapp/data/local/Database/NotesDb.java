package com.kochipek.noteapp.data.local.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kochipek.noteapp.data.Model.Notes;
import com.kochipek.noteapp.data.local.Dao.NotesDao;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesDb extends RoomDatabase {
    public abstract NotesDao notesDao();
    public static NotesDb INSTANCE;
    public static NotesDb getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NotesDb.class, "notes_db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
