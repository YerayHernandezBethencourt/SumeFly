package com.example.sumefly.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sumefly.entidades.Audios;

import java.sql.Timestamp;
import java.util.ArrayList;

public class DBAudio extends DbHelper{

    Context context;

    public DBAudio(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarAudio(int id_user, String title, byte[] audio, String fecha){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase(); //SE QUEDA AQUI
            System.out.println("klklklklklklklklklkklklklklklklklklklklkklklklklklklklklklklkkl3");
            ContentValues values = new ContentValues();
            System.out.println("klklklklklklklklklkklklklklklklklklklklkklklklklklklklklklklkkl4");

            values.put("id_user", id_user);
            values.put("title", title);
            values.put("audio", audio);
            values.put("fecha", fecha);
            System.out.println("klklklklklklklklklkklklklklklklklklklklkklklklklklklklklklklkkl5");
            db.insert(TABLE_RECORDS, null, values);
            System.out.println("klklklklklklklklklkklklklklklklklklklklkklklklklklklklklklklkkl5");
            id = db.insert(TABLE_RECORDS, null, values);
            System.out.println("ID: " + id);
        }catch(Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Audios> mostrarAudios(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Audios> listaAudios = new ArrayList<>();
        Audios audios = null;
        Cursor cursorAudio = null;
        cursorAudio = db.rawQuery("SELECT title, fehcha FROM " + TABLE_RECORDS, null);
        if(cursorAudio.moveToFirst()){
            do{
                audios = new Audios();
                audios.setId(cursorAudio.getInt(0));
                audios.setId_record(cursorAudio.getInt(1));
                audios.setId_user(cursorAudio.getInt(2));
                audios.setTitle(cursorAudio.getString(3));
                audios.setAudio(cursorAudio.getString(4));
                audios.setFecha(Timestamp.valueOf(cursorAudio.getString(5)));
                listaAudios.add(audios);
            }while(cursorAudio.moveToNext());
        }
        cursorAudio.close();
        return listaAudios;
    }
}
