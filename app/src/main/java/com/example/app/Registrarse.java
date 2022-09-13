package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registrarse extends AppCompatActivity {

    EditText correo1, clave1, clave2, nombres, apellidos,fecha;
    Button registro, atras;
    String email = "";
    String claves = "";
    String name = "";
    String lastname = "";
    String date = "";

    private FirebaseAuth fAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        correo1 = (EditText) findViewById(R.id.correo1);
        clave1 = (EditText) findViewById(R.id.clave1);
        clave2 = (EditText) findViewById(R.id.clave2);
        registro = (Button) findViewById(R.id.registro);
        atras = (Button) findViewById(R.id.atras);
        nombres = (EditText) findViewById(R.id.name);
        apellidos = (EditText) findViewById(R.id.lastname);
        fecha = (EditText) findViewById(R.id.date);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registrarse.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }


    public void onStart() {
        super.onStart();
        FirebaseUser currenUser = fAuth.getCurrentUser();

    }

    public void registrarUsuario(View view){

        claves = clave1.getText().toString();
        date = fecha.getText().toString();
        name = nombres.getText().toString();
        lastname = apellidos.getText().toString();
        email = correo1.getText().toString();


        if (clave1.getText().toString().equals(clave2.getText().toString())){
            fAuth.createUserWithEmailAndPassword(correo1.getText().toString(),clave1.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                Map<String, Object> map = new HashMap<>();

                                map.put("Clave", claves);
                                map.put("Email", email);
                                map.put("FechaNacimiento", date);
                                map.put("Apellidos", lastname);
                                map.put("Nombres", name);


                                String id = fAuth.getCurrentUser().getUid();
                                mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task2) {
                                        if(task2.isSuccessful()){
                                            //Log.d(TAG, "onComplete: success");
                                            FirebaseUser user = fAuth.getCurrentUser();
                                            //updateUi(user);
                                            Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        }else{
                                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                            //updateUI(null);


                                        }

                                    }
                                });

                            }else{
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                //updateUI(null);

                            }
                        }
                    });
        }else{
            Toast.makeText(this, "La contraseña no coinciden", Toast.LENGTH_SHORT).show();

        }
    }
}