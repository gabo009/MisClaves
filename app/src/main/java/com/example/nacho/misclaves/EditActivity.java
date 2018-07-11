package com.example.nacho.misclaves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    private EditText et_Nombre, et_Usuario, et_Url, et_Contrasena, et_Pass, et_Comentario;
    private TextView tv_Rut;
    private Button btn_Guardar, btn_Eliminar;
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
        btn_Guardar = (Button) findViewById(R.id.btn_Guardar);
        btn_Eliminar = (Button) findViewById(R.id.btn_Eliminar);
        sw_Ver = (Switch) findViewById(R.id.sw_Ver);

        if (et_Nombre.getText().toString().isEmpty()||et_Usuario.getText().toString().isEmpty()||et_Contrasena.getText().toString().isEmpty()){
            btn_Guardar.setEnabled(false);
        }

        et_Nombre.setOnFocusChangeListener(listener);
        et_Usuario.setOnFocusChangeListener(listener);
        et_Contrasena.setOnFocusChangeListener(listener);

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


        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                }else{
                    intent.putExtra("usuario", rut);
                    intent.putExtra("nombre", et_Nombre.getText().toString());
                    intent.putExtra("user", et_Usuario.getText().toString());
                    intent.putExtra("url", et_Url.getText().toString());
                    intent.putExtra("contrasena", et_Contrasena.getText().toString());
                    intent.putExtra("comentario", et_Comentario.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (et_Nombre.getText().toString().isEmpty() || et_Usuario.getText().toString().isEmpty() || et_Contrasena.getText().toString().isEmpty()) {
                btn_Guardar.setEnabled(false);
            } else {
                btn_Guardar.setEnabled(true);
            }
        }
    };
}
