package com.example.josep.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
//inicializamos las variables
    EditText nombre, ciclo, curso, edad, nota;

    Button introd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.nombre);
        ciclo = (EditText) findViewById(R.id.ciclo);
        curso = (EditText) findViewById(R.id.curso);
        edad = (EditText) findViewById(R.id.edad);
        nota = (EditText) findViewById(R.id.notam);
        introd = (Button) findViewById(R.id.introducir);


//metodo en el cual pasamos los datos a la clase de datos para introducirlos
        introd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos bd = new BaseDeDatos(getApplicationContext());
               bd.insertarEstudiante(nombre.getText().toString(),ciclo.getText().toString(),curso.getText().toString(),
                        parseInt(edad.getText().toString()),parseInt(nota.getText().toString()));
// Vaciamos los edittext una vez hecho el insert
                nombre.setText("");
                ciclo.setText("");
                curso.setText("");
                edad.setText("");
                nota.setText("");


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
