package com.example.repcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.repcovid.dao.PacientesDAODB;
import com.example.repcovid.dto.Paciente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RegistrarPaciente extends AppCompatActivity {
    private PacientesDAODB pacientesDAO = new PacientesDAODB(this);
    private EditText rut, nombre, apellido, fecha, temperatura, presion;
    private Switch sintoma, tos;
    private Spinner areaTrabajo;
    private String[] itemsAreaTrabajo;
    private int dia, mes, anio;
    private Button btnRegistro;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        this.rut = findViewById(R.id.rutPacienteForm);
        this.nombre = findViewById(R.id.nombrePacienteForm);
        this.apellido = findViewById(R.id.apellidoPacienteForm);
        this.fecha = findViewById(R.id.fechaTxt);
        this.areaTrabajo = findViewById(R.id.areaPacienteForm);
        this.sintoma = findViewById(R.id.sintomaPacienteForm);
        this.temperatura = findViewById(R.id.tempPacienteForm);
        this.tos = findViewById(R.id.tosPacienteForm);
        this.presion = findViewById(R.id.presionPacienteForm);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.btnRegistro = findViewById(R.id.btnRegistroForm);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.areaTrabajo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.areaTrabajo.setAdapter(adapter);
        this.itemsAreaTrabajo = getResources().getStringArray(R.array.areaTrabajo);
        this.fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                anio = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrarPaciente.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
            }
        });
        this.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String comparar = null;
                if (rut.getText().toString().isEmpty()) {
                    errores.add("Debe ingresar el rut del paciente");
                } else {
                    char extracto = rut.getText().toString().charAt(rut.getText().toString().length() - 2);
                    if (rut.getText().toString().length() == 9 || rut.getText().toString().length() == 10 && extracto == '-') {
                        comparar = rut.getText().toString().substring(rut.length() - 6, rut.length() - 2);
                    } else {
                        errores.add("El rut es invalido");
                    }
                }
                if (nombre.getText().toString().trim().isEmpty()) {
                    errores.add("Debe ingresar el nombre");
                }
                if (apellido.getText().toString().trim().isEmpty()) {
                    errores.add("Debe ingresar el apellido");
                }
                String fechaPaciente = fecha.getText().toString();
                if (fechaPaciente.isEmpty()) {
                    errores.add("Debe seleccionar la fecha");
                }
                String areaTrabajoTxt = areaTrabajo.getSelectedItem().toString();
                if (areaTrabajoTxt.equalsIgnoreCase("Seleccionar")) {
                    errores.add("Debe seleccionar area de trabajo");
                }
                float temp = 0;
                String tempGuardar = temperatura.getText().toString().trim();
                if (tempGuardar.isEmpty()) {
                    errores.add("Ingrese la temperatura");
                } else {
                    try {
                        temp = Float.parseFloat(tempGuardar);
                        if (temp <= 20.0) {
                            throw new NumberFormatException();
                        }
                    } catch (Exception ex) {
                        errores.add("La temperatura debe ser mayor que 20");
                    }
                }
                int presionArterial = 0;
                String presionGuardar = presion.getText().toString().trim();
                if (presionGuardar.isEmpty()) {
                    errores.add("Digite la presión");
                } else {
                    try {
                        presionArterial = Integer.parseInt(presionGuardar);
                        if (presionArterial <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (Exception ex) {
                        errores.add("La presión debe ser mayor a 0");
                    }
                }
                if (errores.isEmpty()) {
                    Paciente p = new Paciente();
                    p.setRut(rut.getText().toString().trim());
                    p.setNombre(nombre.getText().toString().trim());
                    p.setApellido(apellido.getText().toString().trim());
                    p.setFechaExamen(fechaPaciente);
                    p.setAreaTrabajo(areaTrabajoTxt);
                    p.setSintomas(sintoma.isChecked());
                    p.setTemperatura(temp);
                    p.setTos(tos.isChecked());
                    p.setPresionArterial(presionArterial);
                    pacientesDAO.add(p);
                    Intent intent = new Intent(RegistrarPaciente.this, ActivityPrincipal.class);
                    startActivity(intent);
                } else {
                    mostrarErrores(errores);
                }

            }
        });

    }

    private void mostrarErrores(List<String> errores) {
        String mensaje = "";
        for (String e : errores) {
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegistrarPaciente.this);
        alertBuilder.setTitle("Error de validación")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }
}