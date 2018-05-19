package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.ContentValues;
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

public class ContraFragment extends Fragment implements View.OnClickListener {
    String em;
    TextView ant, nue;
    Button guarda;
    Cursor fila;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contra, container, false);
        ClienteMain activity = (ClienteMain) getActivity();
        em = activity.getMyData();
        ant = (TextView) view.findViewById(R.id.contra_ant);
        nue = (TextView) view.findViewById(R.id.contra_nue);
        guarda = (Button) view.findViewById(R.id.guardacon);
        guarda.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        String ante = ant.getText().toString();
        String nuev = nue.getText().toString();

        fila = bd.rawQuery("select contra from cliente where correo='" + em + "'", null);

        if (fila.moveToFirst() == true) {
            String n = fila.getString(0);

            if(ante.equals(n)){

                ContentValues registro = new ContentValues();
                registro.put("contra", nuev);

                int cant = bd.update("cliente", registro, "correo='" + em + "'", null);
                bd.close();

                if (cant == 1) {
                    Toast toast = Toast.makeText(getActivity(),
                            "Cambios correctos", Toast.LENGTH_SHORT);
                    toast.show();
                    CuentaFragment nextFrag= new CuentaFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment_placeholder, nextFrag,"findThisFragment")
                            .addToBackStack(null)
                            .commit();

                } else {
                    Toast toast2 = Toast.makeText(getActivity(),
                            "Ocurrio un error en los cambios", Toast.LENGTH_SHORT);
                    toast2.show();
                    CuentaFragment nextFrag= new CuentaFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment_placeholder, nextFrag,"findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }

            }else{
                Toast toast2 = Toast.makeText(getActivity(),
                        "La contraseña actual no coincide", Toast.LENGTH_SHORT);
                toast2.show();
            }

        }

    }
}
