package com.example.josep.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static com.example.josep.sqlite.BaseDeDatos.CREATE_TABLE_ESTUDIANTE;
import static com.example.josep.sqlite.BaseDeDatos.CREATE_TABLE_PROFESOR;
import static com.example.josep.sqlite.BaseDeDatos.DROP_TABLE_ESTUDIANTE;
import static com.example.josep.sqlite.BaseDeDatos.DROP_TABLE_PROFESOR;

/**
 * Created by Josep on 17/12/2016.
 */

public class BaseDeDatos {

    private static final String DATABASE_NAME = "Florida.db";

    private static final int DB_SCHEME_VERSION = 1;


    public static final String Table_Estudiante = "ESTUDIANTE";

    public static final String Table_Profesor = "PROFESOR";

    public static final String ID = "id";
    public static final String NOMBRE = "Nombre";
    public static final String CURSO = "Curso";
    public static final String EDAD = "Edad";
    public static final String CICLO = "Ciclo";
    public static final String NOTA = "Nota";
    public static final String DESPACHO = "Despacho";

    private MyDbHelper dbHelper;


//metodos sql para introducir y eliminar estudiantes
    public static final String CREATE_TABLE_ESTUDIANTE = "create table " + Table_Estudiante + " ("
            + ID + " integer primary key autoincrement," + NOMBRE + " text not null,"
            + CICLO + " text not null," + CURSO + " text not null," + EDAD + " Integer not null,"
            + NOTA + " Integer not null);";

    public static final String DROP_TABLE_ESTUDIANTE = "DROP TABLE IF EXISTS " + Table_Estudiante + ";";


    public static final String CREATE_TABLE_PROFESOR = "create table " + Table_Profesor + " ("
            + ID + " integer primary key autoincrement," + NOMBRE + " text not null," + EDAD + " Integer not null,"
            + DESPACHO + " text not null,"+ CICLO + " text not null," + CURSO + " text not null);";

    public static final String DROP_TABLE_PROFESOR = "DROP TABLE IF EXISTS " + Table_Profesor + ";";

    private SQLiteDatabase db;


    public BaseDeDatos(Context context) {
//pasamos los parametros para crear o actualizar la base de datos
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DB_SCHEME_VERSION);
        open();
    }

    public void open() {
// hacemos editable la base de datos
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarEstudiante(String nombre, String ciclo, String curso, Integer edad, Integer nota) {

// guardamos los datos y se los pasamos para introducirlos en la base de datos

        ContentValues Values = new ContentValues();


        Values.put(NOMBRE, nombre);
        Values.put(CICLO, ciclo);
        Values.put(CURSO, curso);
        Values.put(EDAD, edad);
        Values.put(NOTA, nota);

        db.insert(Table_Estudiante, null, Values);

    }
    public void borrarEstudiante(int id, Context context) {

        //pasamos el id para borrar al estudiante
        db.delete(Table_Estudiante, "id=" + id, null);
        Toast.makeText(context,"Ha sido borrado",Toast.LENGTH_SHORT).show();



    }
    public void insertarProfesores(String nombre, int edad, String despacho,String ciclo, String curso) {

        // guardamos los datos y se los pasamos para introducirlos en la base de datos

        ContentValues Values = new ContentValues();

        Values.put(NOMBRE, nombre);
        Values.put(EDAD, edad);
        Values.put(CICLO, ciclo);
        Values.put(CURSO, curso);
        Values.put(DESPACHO, despacho);

        db.insert(Table_Profesor, null, Values);
    }



    public void borrarProfesor(int id , Context context) {
        //pasamos el id para borrar el profesor

        db.delete(Table_Profesor, "id=" + id,null);
        Toast.makeText(context,"Ha sido borrado",Toast.LENGTH_SHORT).show();
    }


    public void vacioTotal(Context context){
        try {
                //devolvemos la base de datos a su primera version es decir a cuando se creo y no tenia datos
            dbHelper.onUpgrade(db,1,1);
            Toast.makeText(context,"Ha sido borrada toda la base de datos",Toast.LENGTH_SHORT).show();

        }catch (Exception e) {

        }
    }

    }


 class MyDbHelper extends SQLiteOpenHelper{

        Context c;

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            c = context;
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

//ccreamos las dos tablas de la base de datos
            db.execSQL(CREATE_TABLE_ESTUDIANTE);
            db.execSQL(CREATE_TABLE_PROFESOR);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //eliminamos las tablas y llamamos al onCreate para crearlas

            db.execSQL(DROP_TABLE_PROFESOR);
            db.execSQL(DROP_TABLE_ESTUDIANTE);
            onCreate(db);


        }
    }



