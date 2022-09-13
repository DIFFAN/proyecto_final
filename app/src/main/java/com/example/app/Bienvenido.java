package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        EditText txtuno;
        EditText txtdos;
        TextView txtresultado;
        Button btn,inicio;
        Button btnborrar;

        txtuno =(EditText) findViewById(R.id.txtuno);
        txtdos = (EditText) findViewById(R.id.txtdos);
        txtresultado =(TextView) findViewById(R.id.txtresultado);
        btn=(Button) findViewById(R.id.btn);
        btnborrar =(Button) findViewById(R.id.btnborrar);
        inicio =(Button) findViewById(R.id.inicio);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1;
                int n2;
                int suma;

                n1 = Integer.parseInt(txtuno.getText().toString());
                n2 = Integer.parseInt(txtdos.getText().toString());

                suma = n1 + n2;
                txtresultado.setText("El resultado es: " + suma);
            }
        });

        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtuno.setText("");
                txtdos.setText("");
                txtresultado.setText("");
            }
        });
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bienvenido.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}