package com.example.ventasapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ventas_db.sqlite";
    private static final int DB_VERSION = 1;
    private Context context;
    private String dbPath;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        dbPath = context.getDatabasePath(DB_NAME).getPath();
        copiarBaseDatosSiNoExiste();
    }

    private void copiarBaseDatosSiNoExiste() {
        File dbFile = new File(dbPath);

        if (!dbFile.exists()) {
            this.getReadableDatabase();
            copiarBDDesdeAssets();
        }
    }

    private void copiarBDDesdeAssets() {
        try {
            InputStream input = context.getAssets().open(DB_NAME);
            OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void onCreate(SQLiteDatabase db) {}
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    // Registrar usuario
    public boolean registrarUsuario(String nombre, String correo, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre_usuario", nombre);
        values.put("correo", correo);
        values.put("pass", pass);

        long result = db.insert("usuario", null, values);
        return result != -1;
    }

    // Login
    public boolean login(String correo, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM usuario WHERE correo = ? AND pass = ?",
                new String[]{correo, pass}
        );

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    public String obtenerNombrePorCorreo(String correo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nombre_usuario FROM usuario WHERE correo = ?",
                new String[]{correo}
        );

        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(0);
            cursor.close();
            return nombre;
        }

        cursor.close();
        return null;
    }

    public int obtenerIdUsuario(String correo, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id_usuario FROM usuario WHERE correo=? AND pass=?",
                new String[]{correo, pass}
        );

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }

        cursor.close();
        return -1; // Usuario no encontrado
    }


}
