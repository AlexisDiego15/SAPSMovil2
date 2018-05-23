package mx.ipn.cecyt9.edu.sapsmovil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MsjAdmiFragment extends Fragment {

    ListView mensajes;
    Cursor fila;
    String informacion, id_men;
    ArrayList<String> lista;
    ArrayList<Usuario> listausuarios = new ArrayList<Usuario>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msjadmi, container, false);

        mensajes = (ListView)view.findViewById(R.id.ver_msj);

        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        Usuario usuario = null;


        fila = bd.rawQuery("select id_men,remitente,cmensaje,respuesta from mensaje", null);

        while (fila.moveToNext()==true  ) {
            usuario = new Usuario();
            usuario.setId(fila.getInt(0));
            usuario.setRem(fila.getString(1));
            usuario.setMen(fila.getString(2));
            usuario.setRes(fila.getString(3));
            listausuarios.add(usuario);

            lista = new ArrayList<String>();

            for (int i = 0; i < listausuarios.size(); i++) {
                lista.add("Mensaje: " + listausuarios.get(i).getMen() + "\n" +
                        "Usuario: " + listausuarios.get(i).getRem()+ "\n" +
                        "Respuesta: " +listausuarios.get(i).getRes());
            }


            ArrayAdapter listilla = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
            mensajes.setAdapter(listilla);

            mensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    id_men = listausuarios.get(pos).getId().toString();
                    String respuestilla = listausuarios.get(pos).getRes();
                    informacion = "Mensaje: "+listausuarios.get(pos).getMen()+"\n";
                    informacion+= "Usuario: "+listausuarios.get(pos).getRem()+"\n";
                    Fragment fragment = new RespuestaFragment();
                    Bundle men = new Bundle();
                    replaceFragment(fragment);
                    men.putString("mensaje", informacion);
                    men.putString("id_m", id_men);
                    men.putString("resp", respuestilla);

                    fragment.setArguments(men);

                }
                });
    }
        return view;
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_placeholder, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

