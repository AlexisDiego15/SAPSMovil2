package mx.ipn.cecyt9.edu.sapsmovil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetalleAdminFragment extends Fragment {

    String info, info2, info3;
    TextView id, nombre, usuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalleadmin, container, false);

        Bundle recuperas = new Bundle();
        recuperas = getArguments();
        info = recuperas.getString("mensaje");
        info2 = recuperas.getString("mensaje2");
        info3 = recuperas.getString("mensaje3");

        id = (TextView) view.findViewById(R.id.editText10);
        nombre = (TextView) view.findViewById(R.id.editText11);
        usuario = (TextView) view.findViewById(R.id.editText12);

        id.setText("ID: "+info);
        id.setEnabled(false);
        nombre.setText("Nombre: "+info2);
        nombre.setEnabled(false);
        usuario.setText("Usuario: "+info3);
        usuario.setEnabled(false);

        return view;
    }
}
