# Login Simple Usando Java

### Para usar la app:

1. Abrir proyecto usando AS
2. Conectar dispositivo por medio USB o usar emulador Pixel 6a API 36
> Si se elige usar teléfono realizar configuración de depuración en modo developer
3. Dar click en Run App

Archivos usados en `app/src/main/java/com/example/ventasapp/`: 
- [DBHelper.java](https://github.com/ich-heisse-jvma/Simple-Login-AS/blob/main/app/src/main/java/com/example/ventasapp/DbHelper.java)
- [LoginActivity.java](https://github.com/ich-heisse-jvma/Simple-Login-AS/blob/main/app/src/main/java/com/example/ventasapp/LoginActivity.java)
- [MainActivity.java](https://github.com/ich-heisse-jvma/Simple-Login-AS/blob/main/app/src/main/java/com/example/ventasapp/MainActivity.java)
- [RegistroActivity.java](https://github.com/ich-heisse-jvma/Simple-Login-AS/blob/main/app/src/main/java/com/example/ventasapp/RegistroActivity.java)

Archivos usados en `app/src/main/res/layout/`:

- [activity_login.xml](https://github.com/ich-heisse-jvma/Simple-Login-AS/blob/main/app/src/main/res/layout/activity_login.xml)
- [activity_main.xml](https://github.com/ich-heisse-jvma/Simple-Login-AS/blob/main/app/src/main/res/layout/activity_main.xml)
- [activity_registro.xml](https://github.com/ich-heisse-jvma/Simple-Login-AS/blob/main/app/src/main/res/layout/activity_registro.xml)

Configuración de pantalla principal usando primero el `activity_login.xml` y no `activity_main.xml` en:

```
app/src/main/AndroidManifest.xml
```

_La base de datos se guarda en_:
```
app/src/main/assets/ventas_db.sqlite
```


## Tablas usadas para el desarrollo


### Tabla: Usuario

| Campo  | Tipo de dato | Restricciones|
| ------------- |:-------------:|:----:|
| id_usuario| INTEGER     | PRIMARY KEY AUTOINCREMENT |
| nombre_usuario| TEXT     | NOT NULL |
| correo        | TEXT     | NOT NULL UNIQUE|
| pass          | TEXT     | NOT NULL |
| creado_en     | DATETIME | DEFAULT CURRENT_TIMESTAMP |
| id_rol        | INTEGER | FOREIGN KEY|

### Tabla: Rol

| Campo  | Tipo de dato | Restricciones |
| ------------- |:-------------:|:----:|
| id_rol| INTEGER     |PRIMARY KEY AUTOINCREMENT |
| nombre_rol        | TEXT     | NOT NULL|

_Si se prefiere código_:

```
-- Crear tabla rol
CREATE TABLE IF NOT EXISTS rol (
    id_rol INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_rol TEXT NOT NULL UNIQUE
);

-- Crear tabla usuario
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_usuario TEXT NOT NULL,
    correo TEXT NOT NULL UNIQUE,
    pass TEXT NOT NULL,
    creado_en DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_rol INTEGER,
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);
```


#### Nota

> Aunque integre varios campos, todos no serán usados realmente porque la
> idea es hacer un inicio de sesión simple 


