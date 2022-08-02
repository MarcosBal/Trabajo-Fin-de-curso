package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvNotLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.ProyectoFinDeCurso);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNotLogin = findViewById(R.id.tvNotLogin);
        tvNotLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InicioActivity.class);
                startActivity(i);
            }
        });
    }
}