package com.example.repcovid.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesDBOpenHelper extends SQLiteOpenHelper {
    private final String sqlCreate ="CREATE TABLE pacientes("+"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "rut TEXT,"+
            "nombre TEXT," +
            "apellido TEXT," +
            "fecha TEXT," +
            "areaTrabajo TEXT," +
            "sintomas BOOLEAN," +
            "temperatura REAL," +
            "tos BOOLEAN," +
            "presion INTEGER)";
    public PacientesDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
