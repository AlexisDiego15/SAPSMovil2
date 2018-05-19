package mx.ipn.cecyt9.edu.sapsmovil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MensajeFragment extends Fragment {
    String em;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mensajes, container, false);
        ClienteMain activity = (ClienteMain) getActivity();
        em = activity.getMyData();

        return view;
    }
}