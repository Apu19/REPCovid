package com.example.repcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.repcovid.dto.Paciente;

public class VerPaciente extends AppCompatActivity {
    private Toolbar toolbar;
    Paciente paciente;
    private TextView rutView,nombreApellidoView,fechaView,areaTrabajoView,sintomasView,tosView,tempView,presionArterialView;
    private ListView verPacienteView;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.rutView = findViewById(R.id.rutListView);
        this.nombreApellidoView =findViewById(R.id.nombreApellidoListView);
        this.fechaView = findViewById(R.id.fechaListView);
        this.areaTrabajoView = findViewById(R.id.areaTrabajoView);
        this.sintomasView = findViewById(R.id.sintomasListView);
        this.tosView = findViewById(R.id.tosListView);
        this.tempView = findViewById(R.id.tempListView);
        this.presionArterialView = findViewById(R.id.presionListView);
        if (getIntent().getExtras() != null) {
            this.paciente = (Paciente) getIntent().getSerializableExtra("paciente");
            this.rutView.setText(paciente.getRut());
            this.nombreApellidoView.setText(paciente.getNombre()+" "+ paciente.getApellido());
            this.fechaView.setText(paciente.getFechaExamen());
            this.areaTrabajoView.setText(paciente.getAreaTrabajo());
            if(paciente.isSintomas()==true){
                this.sintomasView.setText("Si");
            }
            if(paciente.isSintomas()==false){
                this.sintomasView.setText("No");
            }
            if(paciente.isTos()==true){
                this.tosView.setText("Si");
            }
            if(paciente.isTos()==false){
                this.tosView.setText("No");
            }
            String temp = Integer.toString((int) paciente.getTemperatura());
            this.tempView.setText(temp);
            String presion = Integer.toString(paciente.getPresionArterial());
            this.presionArterialView.setText(presion);
        }
    }
}