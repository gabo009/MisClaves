package com.example.nacho.misclaves;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
            et_Nombre.setText(bundle.getString("nombre"));
            et_Usuario.setText(bundle.getString("user"));
            et_Url.setText(bundle.getString("url"));
            et_Contrasena.setText(bundle.getString("contrasena"));
            et_Comentario.setText(bundle.getString("comentario"));
        }

        btm_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.grabar:
                        if (!et_Nombre.getText().toString().isEmpty()&&!et_Usuario.getText().toString().isEmpty()&&!et_Contrasena.getText().toString().isEmpty()){
                            Intent intent = new Intent(EditActivity.this, ViewActivity.class);
                            if (bundle.containsKey("posicion")) {
                                intent.putExtra("posicion", posicion);
                                intent.putExtra("usuario", rut);
                                intent.putExtra("nombre", et_Nombre.getText().toString());
                                intent.putExtra("user", et_Usuario.getText().toString());
                                intent.putExtra("url", et_Url.getText().toString());
                                intent.putExtra("contrasena", et_Contrasena.getText().toString());
                                intent.putExtra("comentario", et_Comentario.getText().toString());
                                startActivity(intent);
                                finish();
                                return true;
                            } else {
                                intent.putExtra("usuario", rut);
                                intent.putExtra("nombre", et_Nombre.getText().toString());
                                intent.putExtra("user", et_Usuario.getText().toString());
                                intent.putExtra("url", et_Url.getText().toString());
                                intent.putExtra("contrasena", et_Contrasena.getText().toString());
                                intent.putExtra("comentario", et_Comentario.getText().toString());
                                startActivity(intent);
                                finish();
                                return true;
                            }
                        }else {
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
