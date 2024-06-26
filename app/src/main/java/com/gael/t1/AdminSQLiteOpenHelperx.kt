package com.gael.t1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

//CONFIGURACION DE LA BASE DE DATOS CORREGIR MUCHO
class AdminSQLiteOpenHelperx(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int = 3
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(BaseDeDatos: SQLiteDatabase) {
        BaseDeDatos.execSQL("CREATE TABLE productos(codigo INTEGER primary key AUTOINCREMENT, nombre text,descripcion text, precio real)")
    }

    override fun onUpgrade(BaseDeDatos: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        BaseDeDatos.execSQL("drop Table if exists productos")
        onCreate(BaseDeDatos)
    }

    }