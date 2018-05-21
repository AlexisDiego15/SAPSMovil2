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

import org.w3c.dom.Text;

public class EliminaFragment extends Fragment implements View.OnClickListener{
    String em;
    Button elimina;
    TextView contra;
    Cursor fila;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elimina, container, false);
        ClienteMain activity = (ClienteMain) getActivity();
        em = activity.getMyData();
        contra = (TextView) view.findViewById(R.id.contra_eli);
        elimina = (Button) view.findViewById(R.id.eliminar);
        elimina.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        String ante = contra.getText().toString();

        fila = bd.rawQuery("select contra from cliente where correo='" + em + "'", null);

        if (fila.moveToFirst() == true) {
            String n = fila.getString(0);

            if(ante.equals(n)){


                int cant = bd.delete("cliente","correo='" + em + "'", null);
                bd.close();

                if (cant == 1) {
                    Toast toast = Toast.makeText(getActivity(),
                            "Cuenta eliminada", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().onBackPressed();


                } else {
                    Toast toast2 = Toast.makeText(getActivity(),
                            "Ocurrio un error en la eliminacion", Toast.LENGTH_SHORT);
                    toast2.show();
            }

            }else{
                Toast toast2 = Toast.makeText(getActivity(),
                        "La contraseña actual no coincide", Toast.LENGTH_SHORT);
                toast2.show();
            }

        }

    }
}
