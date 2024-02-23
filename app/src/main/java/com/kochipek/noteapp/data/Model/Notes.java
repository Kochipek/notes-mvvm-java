package com.kochipek.noteapp.data.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "subtitle")
    public String subtitle;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "notes")
    public String notes;
    @ColumnInfo(name = "notePriority")
    public String notePriority;

}
