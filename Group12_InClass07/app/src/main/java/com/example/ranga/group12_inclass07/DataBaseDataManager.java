package com.example.ranga.group12_inclass07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranga on 2/27/2017.
 */

public class DataBaseDataManager {
    private Context mContext1;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO notesDAO;

    public DataBaseDataManager(Context mCntext)
    {
        this.mContext1 = mCntext;
        dbOpenHelper = new DatabaseOpenHelper(mCntext);
        db = dbOpenHelper.getWritableDatabase();
        notesDAO = new NoteDAO(db);
    }

    public void close()
    {
        if(db!=null)
        {
            db.close();
        }
    }

    public  NoteDAO getNotesDAO()
    {
        return this.notesDAO;
    }

    public long saveNote(Notes note)
    {

        long z =this.notesDAO.save(note);
        Log.d("demo","i in DB"+z);
        return z;
    }

    public boolean updateNote(Notes note)
    {
        return this.notesDAO.update(note);
    }

    public boolean delete(Notes note)
    {
        return this.notesDAO.delete(note);
    }
    public Notes getNote(long id)
    {
        return this.notesDAO.getNote(id);
    }
    public ArrayList<Notes> getNotes()
    {
         return this.notesDAO.getAll();
    }
}




