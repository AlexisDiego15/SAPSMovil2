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

    public class CambioFragment extends Fragment implements View.OnClickListener{
    String em;
    TextView nom, ape, usr, tel;
    Button guardar;
    Cursor fila;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cambio, container, false);
        ClienteMain activity = (ClienteMain) getActivity();
        em = activity.getMyData();
        nom = (TextView) view.findViewById(R.id.txt_nom);
        ape = (TextView) view.findViewById(R.id.tex_ape);
        usr = (TextView) view.findViewById(R.id.txt_usr);
        tel = (TextView) view.findViewById(R.id.txt_tel);
        guardar = (Button) view.findViewById(R.id.guarda);
        guardar.setOnClickListener(this);

        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();


        fila = bd.rawQuery("select nombre,apellido,usuario,celular from cliente where correo='" + em + "'", null);

        if (fila.moveToFirst() == true) {
            String n = fila.getString(0);
            String a= fila.getString(1);
            String u = fila.getString(2);
            String c = fila.getString(3);

            nom.setText(n);
            ape.setText(a);
            usr.setText(u);
            tel.setText(c);
        }

        return view;
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }

    @Override
    public void onClick(View view) {

        ClienteSQLite clien = new ClienteSQLite(getActivity(), "clientes", null, 1);
        SQLiteDatabase bd = clien.getWritableDatabase();

        String n2 = nom.getText().toString();
        String a2 = ape.getText().toString();
        String u2 = usr.getText().toString();
        String c2 = tel.getText().toString();

        if(nom.getText().length()>=2){
            if(ape.getText().length()>=2){
                    if(usr.getText().length()>=2){
                            if(validatePhoneNumber(tel.getText().toString())) {


                                ContentValues registro = new ContentValues();
                                registro.put("nombre", n2);
                                registro.put("apellido", a2);
                                registro.put("usuario", u2);
                                registro.put("celular", c2);

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
                                tel.setError("Escribe un número valido");
                                tel.requestFocus();
                            }}else{
                            usr.setError("Escribe un usuario valido");
                            usr.requestFocus();
                        } }
            else{
                ape.setError("Escribe un apellido valido");
                ape.requestFocus();
            }
        }
        else{
            nom.setError("Escribe un nombre valido");
            nom.requestFocus();
        }


    }
}
