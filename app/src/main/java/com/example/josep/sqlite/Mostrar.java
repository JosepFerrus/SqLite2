package com.example.josep.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    EditText curso, ciclo;

    Button estudiantes, profesores, todos;

    String datos;

    ListView lista;

    ArrayList<String> results = new ArrayList<String>();

    String curso1 = "";
    String ciclo1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);



        curso = (EditText) findViewById(R.id.curso);
        ciclo = (EditText) findViewById(R.id.ciclo);

        estudiantes = (Button) findViewById(R.id.estudiantes);
        profesores = (Button) findViewById(R.id.profesores);
        todos = (Button) findViewById(R.id.todo);

        lista = (ListView) findViewById(R.id.list);

        estudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                curso1= curso.getText().toString().toLowerCase();
                ciclo1= ciclo.getText().toString().toLowerCase();



                if((curso1.equals(""))&&(ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.ver(results, "ESTUDIANTE");
                }
                else if((curso1.equals(""))&&(!ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCiclo(results,"ESTUDIANTE", ciclo1);
                }else
                if((!curso1.equals(""))&&(ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCurso(results,"ESTUDIANTE", curso1);
                }else
                if((!curso1.equals(""))&&(!ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCicloYCurso(results,"ESTUDIANTE", ciclo1, curso1);
                }
                   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,results);

                    lista.setAdapter(adapter);



            }
        });

        profesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                curso1= curso.getText().toString().toLowerCase();
                ciclo1= ciclo.getText().toString().toLowerCase();



                if((curso1.equals(""))&&(ciclo1.equals(""))){

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.ver(results, "PROFESOR");
                }else
                if((curso1.equals(""))&&(!ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCiclo(results,"PROFESOR", ciclo1);
                }else
                if((!curso1.equals(""))&&(ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCurso(results,"PROFESOR", curso1);
                }else
                if((!curso1.equals(""))&&(!ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCicloYCurso(results,"PROFESOR", ciclo1, curso1);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,results);

                lista.setAdapter(adapter);



            }
        });

        todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                curso1= curso.getText().toString().toLowerCase();
                ciclo1= ciclo.getText().toString().toLowerCase();



                if((curso1.equals(""))&&(ciclo1.equals(""))){

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.ver(results,"ESTUDIANTE");
                    db.ver(results,"PROFESOR");
                }else
                if((curso1.equals(""))&&(!ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCiclo(results,"ESTUDIANTE", ciclo1);
                    db.verCiclo(results,"PROFESOR", ciclo1);
                }else
                if((!curso1.equals(""))&&(ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCurso(results,"ESTUDIANTE", curso1);
                    db.verCurso(results,"PROFESOR", curso1);

                }else
                if((!curso1.equals(""))&&(!ciclo1.equals(""))) {

                    results.clear();
                    BaseDeDatos db = new BaseDeDatos(getApplicationContext());
                    db.verCicloYCurso(results,"ESTUDIANTE", ciclo1, curso1);
                    db.verCicloYCurso(results,"PROFESOR", ciclo1, curso1);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,results);

                lista.setAdapter(adapter);



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
