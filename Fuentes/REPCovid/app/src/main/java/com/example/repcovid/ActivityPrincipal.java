package com.example.repcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;




public class ActivityPrincipal extends AppCompatActivity {
    private FloatingActionButton agregarPacienteBtn;
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