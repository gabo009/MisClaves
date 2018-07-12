package com.example.nacho.misclaves;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private ListView lv_Cuentas;
    private ImageButton ibt_Agregar;
    private ArrayAdapter adaptador;
    public ArrayList<Cuentas> cuentas = new ArrayList<Cuentas>();
    Bundle bundle;
    String rut;
    String nombrearchivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lv_Cuentas = (ListView) findViewById(R.id.lv_Cuentas);
        ibt_Agregar = (ImageButton) findViewById(R.id.ibt_Agregar);
        Bundle bundle = getIntent().getExtras();
        rut = bundle.get("usuario").toString();
        nombrearchivo = "cuentas"+rut+".txt";
        //grabarArchivo(nombrearchivo);

        leerArchivo(nombrearchivo);

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cuentas);
        lv_Cuentas.setAdapter(adaptador);

        if (bundle.containsKey("posicion")&&bundle.containsKey("borrar")) {
            borrarUsuario(bundle.getInt("posicion"));
        } else if (bundle.containsKey("posicion")){
            Cuentas c = cuentas.get(bundle.getInt("posicion"));
            c.setNombre(bundle.get("nombre").toString());
            c.setUsuario(bundle.get("user").toString());
            c.setUrl(bundle.get("url").toString());
            c.setClave(bundle.get("contrasena").toString());
            c.setObservacion(bundle.get("comentario").toString());
            grabarArchivo(nombrearchivo);
        } else if (bundle.containsKey("nombre")) {
            cuentas.add(new Cuentas(bundle.get("nombre").toString(), bundle.get("user").toString(), bundle.get("url").toString(), bundle.get("contrasena").toString(), bundle.get("comentario").toString()));
            grabarArchivo(nombrearchivo);
        }

        lv_Cuentas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                borrarUsuario(position);
                return true;
            }
        });
        lv_Cuentas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cuentas c = cuentas.get(position);
                Intent intent = new Intent(ViewActivity.this, EditActivity.class);
                intent.putExtra("usuario", rut);
                intent.putExtra("posicion", position);
                intent.putExtra("nombre", c.getNombre());
                intent.putExtra("user", c.getUsuario());
                intent.putExtra("url", c.getUrl());
                intent.putExtra("contrasena", c.getClave());
                intent.putExtra("comentario", c.getObservacion());
                grabarArchivo(nombrearchivo);
                startActivity(intent);
                finish();
            }
        });
        ibt_Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, EditActivity.class);
                intent.putExtra("usuario", rut);
                grabarArchivo(nombrearchivo);
                startActivity(intent);
                finish();
            }
        });
    }


    public void leerArchivo(String archivo) {
        cuentas.clear();
        try (InputStreamReader isr = new InputStreamReader(openFileInput(archivo));
            BufferedReader br = new BufferedReader(isr)) {
            String line = br.readLine();
            while (line != null) {
                String[] datos = line.split("\\|",-2);
                cuentas.add(new Cuentas(datos[0], datos[1], datos[2], datos[3], datos[4]));
                line = br.readLine();
            }

        } catch (FileNotFoundException fex) {
            Log.d("TAG_", "Archivo "+archivo+" no existe");
        } catch (IOException e) {
            Log.d("TAG_", "No se pudo leer archivo "+archivo);
        }
    }
    private void grabarArchivo(String archivo){
        try (OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(archivo, Activity.MODE_PRIVATE))){
            for (Cuentas cuenta:cuentas) {
                osw.write(cuenta.getNombre()+"|"+cuenta.getUsuario()+"|"+cuenta.getUrl()+"|"+cuenta.getClave()+"|"+cuenta.getObservacion()+"\n");
            }
            //osw.write("");
        } catch (Exception ex){
            Log.d("TAG_", "No se pudo crear el archivo "+archivo);
        }
    }
    private void borrarUsuario(int posicion){
        cuentas.remove(posicion);
        grabarArchivo(nombrearchivo);
        leerArchivo(nombrearchivo);
        adaptador.notifyDataSetChanged();
    }
}
