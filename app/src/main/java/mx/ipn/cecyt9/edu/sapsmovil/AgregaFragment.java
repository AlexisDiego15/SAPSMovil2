package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AgregaFragment extends Fragment implements View.OnClickListener{
    TextView nom, ape, usr, pass;
    Button registro;
    Cursor fila;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregaradmi, container, false);


        nom = (TextView) view.findViewById(R.id.nombre);
        ape = (TextView) view.findViewById(R.id.apellido);
        usr = (TextView) view.findViewById(R.id.usuario);
        pass = (TextView) view.findViewById(R.id.contra);
        registro = (Button) view.findViewById(R.id.registro);
        registro.setOnClickListener(this);

    return view;
    }
    @Override
    public void onClick(View view) {
        if(nom.getText().length()>=2){
            if(ape.getText().length()>=2){
                    if(usr.getText().length()>=2){
                        if(pass.getText().length()>=2){

                                ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
                                SQLiteDatabase bd = clien.getWritableDatabase();

                                String n = nom.getText().toString();
                                String a = ape.getText().toString();
                                String u = usr.getText().toString();
                                String p = pass.getText().toString();

                                fila = bd.rawQuery("select usuario from admin where usuario='" + u + "'", null);

                                if (fila.moveToFirst() == true) {
                                    String us = fila.getString(0);

                                    if (u.equals(us)) {

                                        Toast toast1 = Toast.makeText(getActivity(),
                                                "Usuario ya registrado", Toast.LENGTH_SHORT);
                                        toast1.show();

                                    }
                                } else {
                                    ContentValues datos = new ContentValues();

                                    datos.put("nombre", n);
                                    datos.put("apellido", a);
                                    datos.put("usuario", u);
                                    datos.put("contra", p);

                                    bd.insert("admin", null, datos);
                                    bd.close();

                                    Toast toast1 = Toast.makeText(getActivity(),
                                            "Registro exitoso", Toast.LENGTH_SHORT);
                                    toast1.show();

                                    ConfiguracionFragment nextFrag= new ConfiguracionFragment();
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.main_fragment_placeholder, nextFrag,"findThisFragment")
                                            .addToBackStack(null)
                                            .commit();


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
                ape.setError("Escribe un apellido valido");
                ape.requestFocus();
            }
        }
        else{
            nom.setError("Escribe un nombre valido");
            nom.requestFocus();
        }
    }
}