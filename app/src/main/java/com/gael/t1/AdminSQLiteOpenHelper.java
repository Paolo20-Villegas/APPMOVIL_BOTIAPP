package com.gael.t1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
    public static final String DbName="sql.db";
    public AdminSQLiteOpenHelper(@Nullable Context context) {
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table usuarios(usuario TEXT primary key, contraseña TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {
        BaseDeDatos.execSQL("drop table if exists usuarios");
    }

    public boolean insertData(String usuario, String contraseña ){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues ContentValues= new ContentValues();
        ContentValues.put("usuario", usuario);
        ContentValues.put("contraseña", contraseña);
        long result = myDB.insert("usuarios",null, ContentValues);
        if(result==-1) return false;
        else return true;
    }

    public boolean checkUsername(String usuario){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from usuarios where usuario = ?", new String[]{usuario});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }

    public boolean checkUser(String usuario, String contraseña){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from usuarios where usuario = ? and contraseña = ?", new String[]{usuario, contraseña});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
}
