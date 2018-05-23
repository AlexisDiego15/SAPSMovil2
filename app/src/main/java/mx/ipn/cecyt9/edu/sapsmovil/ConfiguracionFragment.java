package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ConfiguracionFragment extends Fragment implements View.OnClickListener {

    Button agr, ver, campa, cie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        agr = (Button)view.findViewById(R.id.agrega);
        ver = (Button)view.findViewById(R.id.ver);
        campa = (Button)view.findViewById(R.id.cambioco);
        cie = (Button)view.findViewById(R.id.cierra);

        agr.setOnClickListener(this);
        ver.setOnClickListener(this);
        campa.setOnClickListener(this);
        cie.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.agrega:
                fragment = new AgregaFragment();
                replaceFragment(fragment);
                break;

            case R.id.ver:
                fragment = new VerAdmiFragment();
                replaceFragment(fragment);
                break;
            case R.id.cambioco:
                fragment = new ContraAdmFragment();
                replaceFragment(fragment);
                break;
            case R.id.cierra:
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
