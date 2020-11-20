package com.example.repcovid.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.repcovid.dto.Paciente;
import com.example.repcovid.helpers.PacientesDBOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PacientesDAODB implements PacientesDAO{
    private PacientesDBOpenHelper db;

    public PacientesDAODB(Context contexto) {
        this.db = new PacientesDBOpenHelper(contexto, "DBPacientes", null, 1);
    }
    @Override
    public Paciente add(Paciente p) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO pacientes(" + "rut,nombre,apellido,fecha,areaTrabajo,sintomas,temperatura,tos,presion) " +
                "VALUES('"+p.getRut()+"','"+p.getNombre()+"','"+p.getApellido()+"','"+p.getFechaExamen()+"','"+p.getAreaTrabajo()+
                "','"+p.isSintomas()+"',"+p.getTemperatura()+",'"+p.isTos()+"',"+p.getPresionArterial()+")");
        writer.execSQL(sql);
        writer.close();
        return p;
    }

    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.db.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try {
            if (reader != null) {
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido,fecha,areaTrabajo,sintomas,temperatura,tos" +
                        ",presion FROM pacientes", null);
                if (c.moveToFirst()) {
                    do {
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFechaExamen(c.getString(4));
                        p.setAreaTrabajo(c.getString(5));
                        p.setSintomas(Boolean.parseBoolean(c.getString(6)));
                        p.setTemperatura(c.getFloat(7));
                        p.setTos(Boolean.parseBoolean(c.getString(8)));
                        p.setPresionArterial(c.getInt(9));
                        pacientes.add(p);
                    } while (c.moveToNext());
                }
                reader.close();
            }

        } catch (Exception ex) {
            pacientes = null;
        }
        return pacientes;
    }
}
