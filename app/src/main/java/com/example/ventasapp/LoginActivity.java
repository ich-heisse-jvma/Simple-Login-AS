package com.example.ventasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtCorreoLogin, txtPassLogin;
    Button btnLogin, btnIrRegistro;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreoLogin = findViewById(R.id.txtCorreoLogin);
        txtPassLogin = findViewById(R.id.txtPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnIrRegistro = findViewById(R.id.btnIrRegistro);

        dbHelper = new DbHelper(this);

        btnLogin.setOnClickListener(v -> {
            String correo = txtCorreoLogin.getText().toString();
            String pass = txtPassLogin.getText().toString();

            if (correo.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.login(correo, pass)) {
                int idUsuario = dbHelper.obtenerIdUsuario(correo, pass);
                String nombre = dbHelper.obtenerNombrePorCorreo(correo);

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("id_usuario", idUsuario);
                i.putExtra("nombre", nombre);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        btnIrRegistro.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class))
        );
    }
}
