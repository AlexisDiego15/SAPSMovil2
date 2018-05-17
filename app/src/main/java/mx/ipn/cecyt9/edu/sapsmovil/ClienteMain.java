package mx.ipn.cecyt9.edu.sapsmovil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import static mx.ipn.cecyt9.edu.sapsmovil.R.styleable.BottomNavigationView;

public class ClienteMain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private String correo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_main);

        Bundle recibe = new Bundle();
        recibe = this.getIntent().getExtras();
        correo = recibe.getString("correo");

        Toast toast1 = Toast.makeText(getApplicationContext(),
                "Bienvenido "+correo, Toast.LENGTH_SHORT);
        toast1.show();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setInitialFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment = null;
        switch (item.getItemId())
        {
            case R.id.nav_inicio:
                fragment = new InicioFragment();
                break;

            case R.id.nav_catalogo:
                fragment = new CatalogoFragment();
                break;

            case R.id.nav_carrito:
                fragment = new CarritoFragment();
                break;

            case R.id.nav_cuenta:
                 fragment = new CuentaFragment();
                break;
        }
        replaceFragment(fragment);
        return true;
    }
    public String getMyData() {
        return correo;
    }

    private void setInitialFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_placeholder, new InicioFragment());
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_placeholder, fragment);
        fragmentTransaction.commit();

    }
}