package com.example.repcovid.dao;

import com.example.repcovid.dto.Paciente;

import java.util.List;

public interface PacientesDAO {
    Paciente add(Paciente p);
    List<Paciente> getAll();
}
