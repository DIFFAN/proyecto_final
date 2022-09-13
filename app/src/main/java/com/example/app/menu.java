package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button bd,geo,stor,sesion;

        bd = (Button) findViewById(R.id.bd);
        geo = (Button) findViewById(R.id.geo);
        stor = (Button) findViewById(R.id.stor);
        sesion = (Button) findViewById(R.id.sesion);

        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, basedatos.class);
                startActivity(i);
                finish();
            }
        });

        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, Mapa.class);
                startActivity(i);
            }
        });

        stor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, almacenamiento.class);
                startActivity(i);
                finish();
            }
        });
        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}