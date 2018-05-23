package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CuentaFragment extends Fragment implements View.OnClickListener {
    String em;
    Button msj, cam, camp, eli, cie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);

        ClienteMain activity = (ClienteMain) getActivity();
        em = activity.getMyData();

        msj = (Button)view.findViewById(R.id.mensaje);
        cam = (Button)view.findViewById(R.id.cambio);
        camp = (Button)view.findViewById(R.id.cambiop);
        eli = (Button)view.findViewById(R.id.elimina);
        cie = (Button)view.findViewById(R.id.cerrar);

        msj.setOnClickListener(this);
        cam.setOnClickListener(this);
        camp.setOnClickListener(this);
        eli.setOnClickListener(this);
        cie.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.mensaje:
                fragment = new MensajeFragment();
                replaceFragment(fragment);
                break;

            case R.id.cambio:
                fragment = new CambioFragment();
                replaceFragment(fragment);
                break;
            case R.id.cambiop:
                fragment = new ContraFragment();
                replaceFragment(fragment);
                break;
            case R.id.elimina:
                fragment = new EliminaFragment();
                replaceFragment(fragment);
                break;
            case R.id.cerrar:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_placeholder, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}