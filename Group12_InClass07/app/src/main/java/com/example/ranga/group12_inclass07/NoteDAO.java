package com.example.ranga.group12_inclass07;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranga on 2/27/2017.
 */

public class NoteDAO{

    private SQLiteDatabase db1;

    public NoteDAO(SQLiteDatabase db)
    {
        this.db1 = db;
    }
    public long save(Notes note)
    {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_NOTETEXT,note.getNoteText());
        values.put(NotesTable.COLUMN_Priority,note.getPriority());
        values.put(NotesTable.COLUMN_Time,note.getUpdateTime());
        values.put(NotesTable.COLUMN_Status,note.getStatus());
        long i = db1.insert(NotesTable.TABLENAME,null,values);
        Log.d("demo","i in notedao"+i);
        return i;

    }
    public boolean update(Notes note)
    {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_NOTETEXT,note.getNoteText());
        values.put(NotesTable.COLUMN_Priority,note.getPriority());
        values.put(NotesTable.COLUMN_Time,note.getUpdateTime());
        values.put(NotesTable.COLUMN_Status,note.getStatus());

        return db1.update(NotesTable.TABLENAME,values,NotesTable.COLUMN_ID+"=?",new String[]{note.get_id()+""})>0;

    }
    public boolean delete(Notes note)
    {
        return db1.delete(NotesTable.TABLENAME,NotesTable.COLUMN_ID+"=?",new String[]{note.get_id()+""})>0;
    }
    public Notes getNote(long id){
        Notes note = null;
        Cursor c =db1.query(true,NotesTable.TABLENAME,new String[]{NotesTable.COLUMN_ID,NotesTable.COLUMN_NOTETEXT,NotesTable.COLUMN_Priority,NotesTable.COLUMN_Time,NotesTable.COLUMN_Status},NotesTable.COLUMN_ID+"=?",new String[]{id+""},null,null,null,null);
        if(c!=null && c.moveToFirst())
        {
            note = buildNoteFromCursor(c);
            if(!c.isClosed())
            {
                c.close();
            }
        }
        return note;
    }
    public ArrayList<Notes> getAll()
    {
        ArrayList<Notes> notes=new ArrayList<Notes>();

        Cursor c=db1.query(true,NotesTable.TABLENAME,new String[]{NotesTable.COLUMN_ID,NotesTable.COLUMN_NOTETEXT,NotesTable.COLUMN_Priority,NotesTable.COLUMN_Time,NotesTable.COLUMN_Status},null,null,null,null,null,null);
        if(c!=null && c.moveToFirst())
        {
            do {
                Notes note = buildNoteFromCursor(c);
                if(note!=null)
                {
                    notes.add(note);
                }

            }while(c.moveToNext());
            if(!c.isClosed())
            {
                c.close();
            }

        }
        return notes;
    }

    public Notes buildNoteFromCursor(Cursor c)
    {
        Notes note=null;
        if(c!=null)
        {
            note = new Notes();
            note.set_id(c.getInt(0));
            note.setNoteText(c.getString(1));
            note.setPriority(c.getString(2));
            note.setUpdateTime(c.getString(3));
            note.setStatus(c.getString(4));

        }
        return  note;
    }


}
