package com.example.sumefly.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbUsuario extends DbHelper{

    Context context;
    public DbUsuario(Context context) {
        super(context);
        this.context = context;
    }
    /*
    public long insertarContacto(String nombre, String email, String password){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("email", email);
            values.put("password", password);
            db.insert(TABLE_USER, null, values);
            id = db.insert(TABLE_USER, null, values);

        }catch(Exception ex){
            ex.toString();
        }
        return id;
    }

     */
}
