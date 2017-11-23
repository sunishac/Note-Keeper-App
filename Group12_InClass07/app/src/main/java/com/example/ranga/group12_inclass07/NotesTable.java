package com.example.ranga.group12_inclass07;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ranga on 2/27/2017.
 */

public class NotesTable {

    static final String TABLENAME="Notes";
    static final String COLUMN_ID="_id";
    static final String COLUMN_NOTETEXT="noteText";
    static final String COLUMN_Priority="priority";
    static final String COLUMN_Time="updateTime";
    static final String COLUMN_Status="status";

    public static void onCreate(SQLiteDatabase db)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME +"(");
        sb.append(COLUMN_ID +" integer primary key autoincrement,");
        sb.append(COLUMN_NOTETEXT +" text not null,");
        sb.append(COLUMN_Priority + " text not null,");
        sb.append(COLUMN_Time + " text not null,");
        sb.append(COLUMN_Status + " text not null);");
        try{
            db.execSQL(sb.toString());
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void onUpgrade(SQLiteDatabase db,int old_version,int new_version)
    {
        db.execSQL("DROP TABLE IF EXISTS" +TABLENAME);
        onCreate(db);
    }

}
