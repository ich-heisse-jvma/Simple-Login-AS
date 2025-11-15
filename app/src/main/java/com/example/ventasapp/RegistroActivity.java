package com.example.ventasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText txtNombre, txtCorreo, txtPass;
    Button btnRegistrar;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPass = findViewById(R.id.txtPass);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        dbHelper = new DbHelper(this);

        btnRegistrar.setOnClickListener(v -> {
            String nombre = txtNombre.getText().toString();
            String correo = txtCorreo.getText().toString();
            String pass = txtPass.getText().toString();

            if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.registrarUsuario(nombre, correo, pass)) {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
