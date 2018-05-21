package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mensajes, container, false);
        ClienteMain activity = (ClienteMain) getActivity();
        em = activity.getMyData();

        mensajes = (ListView)view.findViewById(R.id.list_msj);
        envia = (Button) view.findViewById(R.id.enviar);
        mensajin = (TextView) view.findViewById(R.id.txt_msj);
        envia.setOnClickListener(this);


        ArrayList<String> lista;
        ArrayList<Usuario> listausuarios;

        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        Usuario usuario = null;
        listausuarios= new ArrayList<Usuario>();

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
        }
        return view;

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
    }else{
            Toast toast1 = Toast.makeText(getActivity(),
                    "Escribe un mensaje valido", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
}
