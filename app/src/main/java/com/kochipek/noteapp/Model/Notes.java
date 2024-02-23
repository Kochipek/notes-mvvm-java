package com.kochipek.noteapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "subtitle")
    String subtitle;
    @ColumnInfo(name = "date")
    String Date;
    @ColumnInfo(name = "notes")
    String notes;
    @ColumnInfo(name = "notePriority")
    String notePriority;

}
