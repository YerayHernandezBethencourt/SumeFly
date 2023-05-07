package com.example.sumefly.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DBAudio extends DbHelper{

    Context context;
    public DBAudio(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarAudio(int id_record, int id_user, String title, String audio){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id_record", id_record);
            values.put("id_user", id_user);
            values.put("title", title);
            values.put("audio", audio);
            db.insert(TABLE_RECORDS, null, values);
            id = db.insert(TABLE_RECORDS, null, values);

        }catch(Exception ex){
            ex.toString();
        }
        return id;
    }
}
