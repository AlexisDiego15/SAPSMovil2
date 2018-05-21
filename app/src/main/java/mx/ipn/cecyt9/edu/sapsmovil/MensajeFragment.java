package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MensajeFragment extends Fragment implements View.OnClickListener {

    String em;
    ListView mensajes;
    Cursor fila;
    Button envia;
    TextView mensajin;
    String informacion;
    ArrayList<String> lista;
    ArrayList<Usuario> listausuarios = new ArrayList<Usuario>();;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mensajes, container, false);
        ClienteMain activity = (ClienteMain) getActivity();
        em = activity.getMyData();

        mensajes = (ListView)view.findViewById(R.id.list_msj);
        envia = (Button) view.findViewById(R.id.enviar);
        mensajin = (TextView) view.findViewById(R.id.txt_msj);
        envia.setOnClickListener(this);


        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        Usuario usuario = null;


        fila = bd.rawQuery("select id_men,remitente,cmensaje,respuesta from mensaje where remitente='" + em + "'", null);

        while (fila.moveToNext()==true  ) {
            usuario = new Usuario();
            usuario.setId(fila.getInt(0));
            usuario.setRem(fila.getString(1));
            usuario.setMen(fila.getString(2));
            usuario.setRes(fila.getString(3));
            listausuarios.add(usuario);

            lista = new ArrayList<String>();

            for (int i = 0; i < listausuarios.size(); i++) {
                lista.add(listausuarios.get(i).getId() + " Mensaje: " + listausuarios.get(i).getMen() + "\n" +
                        " Respuesta: " + listausuarios.get(i).getRes());
            }


            ArrayAdapter listilla = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
            mensajes.setAdapter(listilla);

            mensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    informacion= listausuarios.get(pos).getId().toString();

                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
                    dialogo1.setTitle("Importante");
                    dialogo1.setMessage("¿ Acepta borrar este mensaje?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            aceptar();
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            cancelar();
                        }
                    });
                    dialogo1.show();
                }



            });
        }
        return view;

    }
    public void aceptar() {
        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        int cant = bd.delete("mensaje","id_men='"+informacion+"'", null);
        bd.close();
        if (cant == 1) {
            Toast toast = Toast.makeText(getActivity(),
                    "Mensaje eliminado", Toast.LENGTH_SHORT);
            toast.show();
            Fragment fragment = new MensajeFragment();
            replaceFragment(fragment);


        } else {
            Toast toast2 = Toast.makeText(getActivity(),
                    "Ocurrio un error en la eliminacion", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_placeholder, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void cancelar() {
        Fragment fragment = new MensajeFragment();
        replaceFragment(fragment);
    }



    @Override
    public void onClick(View view) {
            ClienteSQLite clie = new ClienteSQLite(getActivity(), "clientes", null, 1);
            SQLiteDatabase db = clie.getWritableDatabase();
        if(mensajin.getText().length()>=2){
            String m = mensajin.getText().toString();
            String r = "Sin respuesta aun";

            ContentValues datos = new ContentValues();

            datos.put("cmensaje", m);
            datos.put("respuesta", r);
            datos.put("remitente", em);

            db.insert("mensaje", null, datos);
            db.close();

            Toast toast1 = Toast.makeText(getActivity(),
                    "Mensaje enviado", Toast.LENGTH_SHORT);
            toast1.show();

            Fragment fragment = new MensajeFragment();
            replaceFragment(fragment);
    }else{
            Toast toast1 = Toast.makeText(getActivity(),
                    "Escribe un mensaje valido", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
}
