package mx.ipn.cecyt9.edu.sapsmovil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClienteFragment extends Fragment {

    String info,info2,info3,info4,info5,info6, total;
    TextView deta, deta2, deta3, deta4, deta5, deta6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detallec, container, false);

        Bundle recupera = new Bundle();
        recupera = getArguments();
        info = recupera.getString("mensaje");
        info2 = recupera.getString("mensaje2");
        info3 = recupera.getString("mensaje3");
        info4 = recupera.getString("mensaje4");
        info5 = recupera.getString("mensaje5");
        info6 = recupera.getString("mensaje6");


        deta = (TextView) view.findViewById(R.id.detalle);
        deta.setText(info);
        deta.setEnabled(false);
        deta2 = (TextView) view.findViewById(R.id.detalle2);
        deta2.setText(info2);
        deta2.setEnabled(false);
        deta3 = (TextView) view.findViewById(R.id.detalle3);
        deta3.setText(info3);
        deta3.setEnabled(false);
        deta4 = (TextView) view.findViewById(R.id.detalle4);
        deta4.setText(info4);
        deta4.setEnabled(false);
        deta5 = (TextView) view.findViewById(R.id.detalle5);
        deta5.setText(info5);
        deta5.setEnabled(false);
        deta6 = (TextView) view.findViewById(R.id.detalle6);
        deta6.setText(info6);
        deta6.setEnabled(false);

        return view;
    }
}
