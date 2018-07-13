package com.example.nacho.misclaves;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private EditText et_Nombre, et_Usuario, et_Url, et_Contrasena, et_Pass, et_Comentario;
    private TextView tv_Rut;
    private BottomNavigationView btm_menu;
    private Switch sw_Ver;
    int posicion;
    String rut;
    Bundle bundle;
    Cuentas cuenta = new Cuentas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        et_Nombre = (EditText) findViewById(R.id.et_Nombre);
        et_Usuario = (EditText) findViewById(R.id.et_Usuario);
        et_Url = (EditText) findViewById(R.id.et_Url);
        et_Contrasena = (EditText) findViewById(R.id.et_Contrasena);
        et_Pass = (EditText) findViewById(R.id.et_Pass);
        et_Comentario = (EditText) findViewById(R.id.et_Comentario);
        tv_Rut = (TextView) findViewById(R.id.tv_Rut);
        btm_menu = (BottomNavigationView) findViewById(R.id.btm_menu);
        sw_Ver = (Switch) findViewById(R.id.sw_Ver);

        bundle = getIntent().getExtras();
        rut = bundle.getString("usuario");
        if (bundle.containsKey("posicion")) {
            posicion = bundle.getInt("posicion");
            tv_Rut.setText(rut);
            cuenta = new Cuentas(bundle.getString("nombre"), bundle.getString("user"), bundle.getString("url"), " ", bundle.getString("comentario"));
            cuenta.setClaveEncript(bundle.getString("contrasena"));
            et_Nombre.setText(cuenta.getNombre());
            et_Usuario.setText(cuenta.getUsuario());
            et_Url.setText(cuenta.getUrl());
            et_Comentario.setText(cuenta.getObservacion());
        }

        sw_Ver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!(cuenta.desEncript(et_Pass.getText().toString()) == null)) {
                        et_Contrasena.setText(cuenta.desEncript(et_Pass.getText().toString()));
                    } else {
                        et_Contrasena.setText(cuenta.getClave());
                    }
                } else {
                    et_Contrasena.setText(cuenta.getClave());
                }
            }
        });
        btm_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.grabar:
                        if (!et_Nombre.getText().toString().isEmpty() && !et_Usuario.getText().toString().isEmpty() && !et_Contrasena.getText().toString().isEmpty()) {

                            if (bundle.containsKey("posicion")) {
                                Intent intent = new Intent(EditActivity.this, ViewActivity.class);
                                intent.putExtra("posicion", posicion);
                                intent.putExtra("usuario", rut);
                                intent.putExtra("nombre", et_Nombre.getText().toString());
                                intent.putExtra("user", et_Usuario.getText().toString());
                                intent.putExtra("url", et_Url.getText().toString());
                                intent.putExtra("comentario", et_Comentario.getText().toString());
                                intent.putExtra("contrasena", et_Contrasena.getText().toString());
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(EditActivity.this, ViewActivity.class);
                                intent.putExtra("usuario", rut);
                                intent.putExtra("nombre", et_Nombre.getText().toString());
                                intent.putExtra("user", et_Usuario.getText().toString());
                                intent.putExtra("url", et_Url.getText().toString());
                                intent.putExtra("contrasena", et_Contrasena.getText().toString());
                                intent.putExtra("comentario", et_Comentario.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                            return true;
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                            builder.setIcon(R.drawable.ic_alert);
                            builder.setTitle("Error");
                            builder.setMessage("Debe ingresar todos los datos obligatorios (Nombre, Usuario y Contraseña)");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                            return false;
                        }
                    case R.id.eliminar:
                        if (bundle.containsKey("posicion")) {
                            Intent intent = new Intent(EditActivity.this, ViewActivity.class);
                            intent.putExtra("usuario", rut);
                            intent.putExtra("posicion", posicion);
                            intent.putExtra("borrar", true);
                            startActivity(intent);
                            finish();
                            return true;
                        } else {
                            return false;
                        }
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditActivity.this, ViewActivity.class);
        intent.putExtra("usuario", rut);
        startActivity(intent);
        finish();
    }
}
