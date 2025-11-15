package com.example.ventasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    TextView txtBienvenida;
    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBienvenida = findViewById(R.id.txtBienvenida);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        String nombre = getIntent().getStringExtra("nombre");
        // int idUsuario = getIntent().getIntExtra("id_usuario", -1); // para cualquier operación futura (roles, permisos, historial)
        if (nombre != null)
            txtBienvenida.setText("Bienvenido, " + nombre);
        else
            txtBienvenida.setText("Bienvenido al sistema");

        btnCerrarSesion.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}
