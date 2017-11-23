package com.example.ranga.group12_inclass07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ranga on 2/27/2017.
 */

public class DatabaseOpenHelper  extends SQLiteOpenHelper {


    static final String DB_NAME="mynotes1.db";
    static final int DB_VERSION=1;

    public DatabaseOpenHelper(Context mcontect)
    {
        super(mcontect,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        NotesTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        NotesTable.onUpgrade(sqLiteDatabase,i,i1);
    }
}

