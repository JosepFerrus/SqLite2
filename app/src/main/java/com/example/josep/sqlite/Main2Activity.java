package com.example.josep.sqlite;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class Main2Activity extends AppCompatActivity {

    EditText nombre, ciclo, curso, edad, despacho;

    Button introd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombre = (EditText) findViewById(R.id.nombre);
        ciclo = (EditText) findViewById(R.id.ciclo);
        curso = (EditText) findViewById(R.id.curso);
        edad = (EditText) findViewById(R.id.edad);
        despacho = (EditText) findViewById(R.id.despacho);
        introd = (Button) findViewById(R.id.button);


//llamamos al metodo para insertare profesores

        introd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos bd = new BaseDeDatos(getApplicationContext());
                bd.insertarProfesores(nombre.getText().toString(),Integer.parseInt(edad.getText().toString()),despacho.getText().toString()
                        ,ciclo.getText().toString().toLowerCase(),curso.getText().toString().toLowerCase());
//vaciamos los edittext despues de insertar los datos
                nombre.setText("");
                ciclo.setText("");
                curso.setText("");
                edad.setText("");
                despacho.setText("");
            }
        });
    }

//creamos el menu
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
            case R.id.ver:
                Intent p = new Intent(getApplicationContext(),Mostrar.class);
                startActivity(p);
                return true;
            default:
                return false;
        }
    }
}
