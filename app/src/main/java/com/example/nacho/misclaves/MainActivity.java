package com.example.nacho.misclaves;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et_Rut, et_Clave;
    Button btn_Ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Rut = (EditText) findViewById(R.id.et_Rut);
        et_Clave = (EditText) findViewById(R.id.et_Clave);
        btn_Ingresar = (Button) findViewById(R.id.btn_Ingresar);

        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
                if (!pref.getString(et_Rut.getText().toString(), "@").equals("@")){
                    if (pref.getString(et_Rut.getText().toString(), "").equals(et_Clave.getText().toString())){
                        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                        intent.putExtra("usuario", et_Rut.getText().toString());
                        startActivity(intent);
                    }else{
                        et_Clave.setError("Contraseña incorrecta");
                    }
                }else{
                    guardarUsuario();
                }
            }
        });

    }

    private void guardarUsuario(){
        SharedPreferences pref = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(et_Rut.getText().toString(), et_Clave.getText().toString());
        edit.commit();
    }


}
