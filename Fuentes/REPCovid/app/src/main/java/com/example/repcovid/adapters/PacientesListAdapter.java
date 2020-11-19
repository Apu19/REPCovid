package com.example.repcovid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.repcovid.R;
import com.example.repcovid.dto.Paciente;

import java.util.List;

public class PacientesListAdapter extends ArrayAdapter<Paciente> {
    private List<Paciente> pacientes;
    private Activity activity;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.pacientes_list, null, true);
        //cargar contenido del layout personalizado
        TextView rut = fila.findViewById(R.id.rutList);
        TextView nombreCompleto = fila.findViewById(R.id.nombre_apellido_list);
        TextView fecha = fila.findViewById(R.id.fechaList);
        ImageView img = fila.findViewById(R.id.imgList);
        Paciente actual = pacientes.get(position);
        rut.setText(actual.getRut());
        nombreCompleto.setText(actual.getNombre() + " " + actual.getApellido());
        fecha.setText(actual.getFechaExamen());
        if(actual.isSintomas()){
            img.setImageResource(R.drawable.ic_baseline_warning_24);
        }else{

        }
        return fila;
    }

    public PacientesListAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.pacientes = objects;
        this.activity = context;
    }
}
