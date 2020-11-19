package com.example.repcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.repcovid.adapters.PacientesListAdapter;
import com.example.repcovid.dao.PacientesDAO;
import com.example.repcovid.dao.PacientesDAODB;
import com.example.repcovid.dto.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class ActivityPrincipal extends AppCompatActivity {
    private FloatingActionButton agregarPacienteBtn;
    private List<Paciente> pacientes;
    private PacientesDAO pacientesDAO = new PacientesDAODB(this);
    private ListView pacientesLv;
    private PacientesListAdapter adapter;
    protected void onResume(){
        super.onResume();
        this.pacientes= this.pacientesDAO.getAll();
        this.pacientesLv = findViewById(R.id.listaMA);
        this.adapter = new PacientesListAdapter(this,R.layout.pacientes_list,this.pacientes);
        this.pacientesLv.setAdapter(this.adapter);
        this.pacientesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Paciente paciente = pacientes.get(i);
                Intent intent = new Intent(ActivityPrincipal.this,VerPaciente.class);
                intent.putExtra("paciente",paciente);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        this.agregarPacienteBtn = findViewById(R.id.btn_ap);
        this.agregarPacienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincipal.this, RegistrarPaciente.class);
                startActivity(intent);
            }
        });
    }

}