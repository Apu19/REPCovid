package com.example.repcovid.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesDBOpenHelper extends SQLiteOpenHelper {
    private final String sqlCreate ="CREATE TABLE pacientes("+"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            ","+
            "," +
            "," +
            "," +
            "," +
            "," +
            "," +
            "," +
            ")";
    public PacientesDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
