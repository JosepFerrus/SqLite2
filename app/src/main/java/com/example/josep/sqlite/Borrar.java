package com.example.josep.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Borrar extends AppCompatActivity {

    EditText id;

    Button borEstu, borProf, borTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        id = (EditText) findViewById(R.id.id);
        borEstu = (Button) findViewById(R.id.borEst);
        borProf = (Button) findViewById(R.id.borProf);
        borTodo = (Button) findViewById(R.id.borTodo);
//llamamos al metodo para borrar estudiantes
        borEstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos bd = new BaseDeDatos(getApplicationContext());
                bd.borrarEstudiante(Integer.parseInt(id.getText().toString()),getApplicationContext());
            }
        });
        //llamamos al metodo para borrar profesores
        borProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos bd = new BaseDeDatos(getApplicationContext());
                bd.borrarProfesor(Integer.parseInt(id.getText().toString()),getApplicationContext());
            }
        });
        //llamamos al metodo para borrar los datos de la base de datos
        borTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos bd = new BaseDeDatos(getApplicationContext());
                bd.vacioTotal(getApplicationContext());
            }
        });



    }

// creamos el menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Cambiamos de pantalla al seleccionar la opcion del menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.añadir_Estudiante:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                return true;
            case R.id.añadir_Profesor:
                Intent c = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(c);
                return true;
            case R.id.borrar:
                Intent z = new Intent(getApplicationContext(),Borrar.class);
                startActivity(z);
                return true;
            default:
                return false;
        }
    }
}
