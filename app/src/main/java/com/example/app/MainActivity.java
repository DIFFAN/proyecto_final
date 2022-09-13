package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText correo, clave;
    Button ingresar,registrarse,registrar;
    TextView reset;

    FirebaseAuth fAuth;
    DatabaseReference mDatabase;





    NotificationCompat.Builder notificacion;
    private static final String CHANNEL_ID = "canal";
    private PendingIntent pedingIntent;


    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = (EditText) findViewById(R.id.correo);
        clave = (EditText) findViewById(R.id.clave);
        ingresar = (Button) findViewById(R.id.ingresar);
        registrarse = (Button) findViewById(R.id.registrarse);
        registrar = (Button) findViewById(R.id.registrar);
        reset = (TextView) findViewById(R.id.rest);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();





        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString().trim();
                String pass = clave.getText().toString().trim();





                //validar campo correo//validar campo clave
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Inserte correo", Toast.LENGTH_LONG).show();
                    correo.setError("correo requerido");
                    return;
                }
                //if (password.length()<6)
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(MainActivity.this, "Inserte contraseña", Toast.LENGTH_LONG).show();
                    //pass.setError("clave requerido");
                    return;
                }


                fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Inicio Correcto", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),menu.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString().trim();
                String pass = clave.getText().toString().trim();
                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                            //regresar a logeo

                        }else{
                            Toast.makeText(MainActivity.this, "Error de registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registrarse.class);

                startActivity(i);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Restablecer.class);
                startActivity(i);
                finish();
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void shownotificacion(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"New", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        shoenotificacion();
    }
    private void shoenotificacion(){
        setPedingIntent(Bienvenido.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setContentTitle("Notificacion")
                .setContentText("a ingresado a la aplicacion correctamente")
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pedingIntent);
        NotificationManagerCompat nm = NotificationManagerCompat.from(getApplicationContext());
        nm.notify(1,builder.build());

    }
    private void setPedingIntent(Class<?>clsActivity){
        Intent intent = new Intent(this,clsActivity);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(clsActivity);
        stackBuilder.addNextIntent(intent);
        pedingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

    }

}