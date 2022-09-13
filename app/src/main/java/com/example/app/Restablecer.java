package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Restablecer extends AppCompatActivity {

    EditText correoE;
    Button restablecer, atra;

    String email = "";

    FirebaseAuth mAuth;

    ProgressDialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer);
        correoE = (EditText) findViewById(R.id.correoE);
        restablecer = (Button) findViewById(R.id.restablecer);
        atra = (Button) findViewById(R.id.atra);

        mAuth = FirebaseAuth.getInstance();
        dialogo = new ProgressDialog(this);

        restablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = correoE.getText().toString();

                if(!email.isEmpty()){
                    dialogo.setMessage("Espere un momento...");
                    dialogo.setCanceledOnTouchOutside(false);
                    dialogo.show();
                    resetPassword();

                }else{
                    Toast.makeText(Restablecer.this, "Ingresar correo electronico correcto", Toast.LENGTH_SHORT).show();
                }

            }
        });
        atra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Restablecer.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void resetPassword(){

        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Restablecer.this, "Revisar su correo electronico", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Restablecer.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(Restablecer.this, "No sea a podido enviar el correo de restablecer contraseña", Toast.LENGTH_SHORT).show();
                }
                dialogo.dismiss();
            }
        });

    }
}