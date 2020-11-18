package com.example.repcovid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_log;
    private EditText rut;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.rut = findViewById(R.id.nombreLogin);
        this.password = findViewById(R.id.passLogin);
        this.btn_log = findViewById(R.id.btn_login);
        this.btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String comparar = null;
                if (rut.getText().toString().isEmpty()) {
                    errores.add("Debe ingresar el nombre de usuario");
                    rut.setBackground(getDrawable(R.drawable.borde_edittext));
                } else {
                    char extracto = rut.getText().toString().charAt(rut.getText().toString().length() - 2);
                    if (rut.getText().toString().length() == 9 || rut.getText().toString().length() == 10 && extracto == '-') {
                        comparar = rut.getText().toString().substring(rut.length() - 6, rut.length() - 2);
                        rut.setBackground(getDrawable(R.drawable.borde_edit_text2));
                    } else {
                        errores.add("Su nombre es invalido");
                        rut.setBackground(getDrawable(R.drawable.borde_edittext));
                    }
                }
                int clave = 0;
                String claveUso = password.getText().toString();
                if (claveUso.isEmpty()) {
                    errores.add("Ingrese la contraseña");
                    password.setBackground(getDrawable(R.drawable.borde_edittext));
                } else {
                    try {
                        clave = Integer.parseInt(claveUso);
                        if (clave >= 0 && comparar.equals(password.getText().toString().trim())) {
                            password.setBackground(getDrawable(R.drawable.borde_edit_text2));
                        } else {
                            errores.add("Clave incorrecta");
                            password.setBackground(getDrawable(R.drawable.borde_edittext));
                        }
                    } catch (Exception ex) {
                        errores.add("La clave solo debe contener números");
                        password.setBackground(getDrawable(R.drawable.borde_edittext));
                    }
            }
                if (errores.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ActivityPrincipal.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, errores.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}