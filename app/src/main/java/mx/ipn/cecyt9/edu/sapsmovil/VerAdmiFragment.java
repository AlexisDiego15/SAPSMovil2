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

public class VerAdmiFragment extends Fragment {
    ListView mensajes;
    Cursor fila;
    String id_cli, informacion, informacion2,informacion3;
    ArrayList<String> lista;
    ArrayList<Admin> listausuarios = new ArrayList<Admin>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_veradmin, container, false);

        mensajes = (ListView)view.findViewById(R.id.clientes);

        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        Admin usuario = null;

        fila = bd.rawQuery("select id,nombre,apellido,usuario from admin", null);

        while (fila.moveToNext()==true  ) {
            usuario = new Admin();
            usuario.setId(fila.getInt(0));
            usuario.setNombre(fila.getString(1));
            usuario.setApellido(fila.getString(2));
            usuario.setUser(fila.getString(3));
            listausuarios.add(usuario);

            lista = new ArrayList<String>();

            for (int i = 0; i < listausuarios.size(); i++) {
              lista.add(listausuarios.get(i).getId() + " " + listausuarios.get(i).getUser());
            }


            ArrayAdapter listilla = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
            mensajes.setAdapter(listilla);

            mensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    informacion = listausuarios.get(pos).getId()+"\n";
                    informacion2 = listausuarios.get(pos).getNombre()+"\n";
                    informacion2+= " "+listausuarios.get(pos).getApellido()+"\n";
                    informacion3 = listausuarios.get(pos).getUser()+"\n";

                    Fragment fragment = new DetalleAdminFragment();
                    Bundle mens = new Bundle();
                    replaceFragment(fragment);
                    mens.putString("mensaje", informacion);
                    mens.putString("mensaje2", informacion2);
                    mens.putString("mensaje3", informacion3);
                    fragment.setArguments(mens);

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




