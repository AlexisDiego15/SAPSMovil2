package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    TextView name,apel,email,pass, usr, tel;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        name = findViewById(R.id.editText2);
        apel = findViewById(R.id.editText4);
        email = findViewById(R.id.editText5);
        usr = findViewById(R.id.editText6);
        pass = findViewById(R.id.editText7);
        tel = findViewById(R.id.editText9);
    }

    public void regresar(View v){
        Intent inicio = new Intent(this, MainActivity.class);
        startActivity(inicio);
        finish();
    }

    private boolean isValidEmaillId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }

    public void crear(View v){

        if(name.getText().length()>=2){
            if(apel.getText().length()>=2){
                if(isValidEmaillId(email.getText().toString().trim())){
                    if(usr.getText().length()>=2){
                        if(pass.getText().length()>=2){
                                if(validatePhoneNumber(tel.getText().toString())) {

                                    ClienteSQLite clien = new ClienteSQLite(this, "clientes", null, 1);
                                    SQLiteDatabase bd = clien.getWritableDatabase();

                                    String nom = name.getText().toString();
                                    String ape = apel.getText().toString();
                                    String ema = email.getText().toString();
                                    String user = usr.getText().toString();
                                    String pas = pass.getText().toString();
                                    String num = tel.getText().toString();

                                    fila = bd.rawQuery("select correo from cliente where correo='" + ema + "'", null);

                                    if (fila.moveToFirst() == true) {
                                        String us = fila.getString(0);

                                        if (ema.equals(us)) {

                                            Toast toast1 = Toast.makeText(getApplicationContext(),
                                                    "Correo ya registrado", Toast.LENGTH_SHORT);
                                            toast1.show();

                                        }
                                    } else {
                                        ContentValues datos = new ContentValues();

                                        datos.put("nombre", nom);
                                        datos.put("apellido", ape);
                                        datos.put("correo", ema);
                                        datos.put("usuario", user);
                                        datos.put("contra", pas);
                                        datos.put("celular", num);

                                        bd.insert("cliente", null, datos);
                                        bd.close();

                                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                                "Registro exitoso", Toast.LENGTH_SHORT);
                                        toast1.show();

                                        Intent c = new Intent(this, ClienteMain.class);
                                        startActivity(c);
                                        finish();


                                    }
                                }else{
                                    tel.setError("Escribe un número valido");
                                    tel.requestFocus();
                                }
                        }
                        else{
                            pass.setError("Escribe una contraseña valida");
                            pass.requestFocus();
                        }
                    }
                    else{
                        usr.setError("Escribe un usuario valido");
                        usr.requestFocus();
                    }
                }
                else{
                    email.setError("Escribe un email valido");
                    email.requestFocus();
                }
            }
            else{
                apel.setError("Escribe un apellido valido");
                apel.requestFocus();
            }
        }
        else{
            name.setError("Escribe un nombre valido");
            name.requestFocus();
        }
    }
}