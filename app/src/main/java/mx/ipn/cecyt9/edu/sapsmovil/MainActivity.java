package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.editText);
        pass = findViewById(R.id.editText3);
    }

    public void validacion(View v) {

        ClienteSQLite clien = new ClienteSQLite(this, "clientes", null, 1);
        SQLiteDatabase bd =clien.getWritableDatabase();

        String usuario = email.getText().toString();
        String contra = pass.getText().toString();

        fila = bd.rawQuery("select usuario,contra from cliente where usuario='"+usuario+"' and contra ='"+contra+"'", null);

        if(fila.moveToFirst()==true){
            String us = fila.getString(0);
            String pa = fila.getString(1);

            if(usuario.equals(us)&&contra.equals(pa)){

                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Bienvenido", Toast.LENGTH_SHORT);
                toast1.show();

                Intent c = new Intent(this, ClienteMain.class);
                Bundle datos = new Bundle();
                datos.putString("correo", us);
                c.putExtras(datos);
                startActivity(c);
                finish();
            }
        }
        else {
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void crear(View v){
        Intent inicio = new Intent(this, Registro.class);
        startActivity(inicio);
    }
}
