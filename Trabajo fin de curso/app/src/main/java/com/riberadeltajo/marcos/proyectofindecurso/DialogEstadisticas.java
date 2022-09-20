package com.riberadeltajo.marcos.proyectofindecurso;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogEstadisticas extends DialogFragment {

    TextView ganadas, perdidas, empatadas;
    Estadisticas e;

    public DialogEstadisticas(Estadisticas e) {
        this.e = e;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return new Dialog(getActivity());
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogo_estadisticas, container, false);
        ganadas = v.findViewById(R.id.ganadas);
        perdidas = v.findViewById(R.id.perdidas);
        empatadas = v.findViewById(R.id.empatadas);
        ganadas.setText(e.getGanadas()+"");
        perdidas.setText(e.getPerdidas()+"");
        empatadas.setText(e.getEmpates()+"");
        return v;
    }

}
