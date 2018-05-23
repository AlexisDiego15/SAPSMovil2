package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RespuestaFragment extends Fragment implements View.OnClickListener {

    String id, info, resp;
    TextView _id, _msj, _respuesta;
    Button _res;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);

        Bundle recupera = new Bundle();
        recupera = getArguments();
        info =recupera.getString("mensaje");
        id = recupera.getString("id_m");
        resp = recupera.getString("resp");


        _res = (Button) view.findViewById(R.id.responder);
        _id = (TextView) view.findViewById(R.id.view_id);
        _msj = (TextView) view.findViewById(R.id.view_msj);
        _respuesta = (TextView) view.findViewById(R.id.txt_res);

        _res.setOnClickListener(this);


        _id.setText(id);
        _msj.setText(info);

        if(!resp.equals("Sin respuesta aun")){
            _respuesta.setEnabled(false);
            _respuesta.setText("Ya fue respondido el mensaje");
            _res.setEnabled(false);

        }



        return view;
    }

    @Override
    public void onClick(View view) {



            ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
            SQLiteDatabase bd = clien.getWritableDatabase();

            String r2 = _respuesta.getText().toString();

            if (_respuesta.getText().length() >= 2) {

                ContentValues registro = new ContentValues();
                registro.put("respuesta", r2);

                int cant = bd.update("mensaje", registro, "id_men='" + id + "'", null);
                bd.close();

                if (cant == 1) {
                    Toast toast = Toast.makeText(getActivity(),
                            "Respuesta enviada", Toast.LENGTH_SHORT);
                    toast.show();
                    MsjAdmiFragment nextFrag = new MsjAdmiFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment_placeholder, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();

                } else {
                    Toast toast2 = Toast.makeText(getActivity(),
                            "Ocurrio un error en los cambios", Toast.LENGTH_SHORT);
                    toast2.show();
                    MsjAdmiFragment nextFrag = new MsjAdmiFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment_placeholder, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }


            } else {
                _respuesta.setError("Escribe una respuesta valida");
                _respuesta.requestFocus();
            }



    }
}