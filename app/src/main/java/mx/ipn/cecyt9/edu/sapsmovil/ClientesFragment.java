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

public class ClientesFragment extends Fragment {
    ListView mensajes;
    Cursor fila;
    String id_cli, informacion;
    ArrayList<String> lista;
    ArrayList<Cliente> listausuarios = new ArrayList<Cliente>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        mensajes = (ListView)view.findViewById(R.id.clientes);

        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        Cliente usuario = null;

        fila = bd.rawQuery("select id,nombre,apellido,correo,usuario,celular from cliente", null);

        while (fila.moveToNext()==true  ) {
            usuario = new Cliente();
            usuario.setId(fila.getInt(0));
            usuario.setNom(fila.getString(1));
            usuario.setApe(fila.getString(2));
            usuario.setCor(fila.getString(3));
            usuario.setUsu(fila.getString(4));
            usuario.setTel(fila.getInt(5));
            listausuarios.add(usuario);

            lista = new ArrayList<String>();

            for (int i = 0; i < listausuarios.size(); i++) {
                lista.add(listausuarios.get(i).getId() + " " + listausuarios.get(i).getUsu());
            }


            ArrayAdapter listilla = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
            mensajes.setAdapter(listilla);

            mensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    informacion = "Id Usuario: "+listausuarios.get(pos).getId()+"\n";
                    informacion+= "Nombre: "+listausuarios.get(pos).getNom()+"\n";
                    informacion+= "Apellido: "+listausuarios.get(pos).getApe()+"\n";
                    informacion+= "Correo: "+listausuarios.get(pos).getCor()+"\n";
                    informacion+= "Usuario: "+listausuarios.get(pos).getUsu()+"\n";
                    informacion+= "Telefono: "+listausuarios.get(pos).getTel()+"\n";

                    Fragment fragment = new ClienteFragment();
                    Bundle men = new Bundle();
                    replaceFragment(fragment);
                    men.putString("mensaje", informacion);
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




