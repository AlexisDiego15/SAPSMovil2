package mx.ipn.cecyt9.edu.sapsmovil;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CatalogoFragment extends Fragment {

    ListView productos;
    int[] IMAGES={R.drawable.naranja,R.drawable.naranja,R.drawable.naranja,R.drawable.naranja,R.drawable.naranja};
    String[] NAMES={"naranja","naranja","naranja","naranja","naranja"};
    String[] PRECIO={"12.50","12.50","12.50","12.50","12.50"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogo, container, false);

        productos = (ListView) view.findViewById(R.id.catalogo);
        CustoAdapter custoAdapter = new CustoAdapter();
        productos.setAdapter(custoAdapter);

    return view;
    }

    class CustoAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.producto, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
            TextView nombre = (TextView) view.findViewById(R.id.nombre_prod);
            TextView precio = (TextView) view.findViewById(R.id.precio_prod);

            imageView.setImageResource(IMAGES[i]);
            nombre.setText(NAMES[i]);
            precio.setText(PRECIO[i]);
            return view;
        }
    }


}