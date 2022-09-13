package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class basedatos extends AppCompatActivity {

    EditText nm,ap,em,tl;
    Button guardar,me;
    datos datos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedatos);

        em = (EditText) findViewById(R.id.em);
        ap = (EditText) findViewById(R.id.ap);
        nm = (EditText) findViewById(R.id.nm);
        tl = (EditText) findViewById(R.id.tl);


        guardar = (Button) findViewById(R.id.guardar);
        me = (Button) findViewById(R.id.me);

        datos = new datos();

        FirebaseDatabase dato;
        dato= FirebaseDatabase.getInstance();

        DatabaseReference dbref;
        dbref = dato.getReference().child("Datos Personales");

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datos.setMail(em.getText().toString().trim());
                datos.setApellido(ap.getText().toString().trim());
                datos.setNombre(nm.getText().toString().trim());
                datos.setTelefono(tl.getText().toString().trim());

                dbref.push().setValue(datos);
                Toast.makeText(basedatos.this, "Datos Enviados", Toast.LENGTH_SHORT).show();

                nm.setText("");
                em.setText("");
                ap.setText("");
                tl.setText("");
            }
        });

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(basedatos.this, menu.class);
                startActivity(i);
                finish();
            }
        });

    }
}