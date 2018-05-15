package mx.ipn.cecyt9.edu.sapsmovil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* Created by Alexis on 13/05/2018. */

public class ClienteSQLite extends SQLiteOpenHelper {
    public ClienteSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table cliente(id integer primary key autoincrement, nombre text, apellido text, correo text, usuario text, contra text, celular integer)");
        bd.execSQL("insert into cliente values(1, 'alexis', 'diego', 'alexis', 'alexis', 'alexis', 5563190817)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int old, int nuevo) {
        bd.execSQL("create table cliente(id integer primary key autoincrement, nombre text, apellido text, correo text, usuario text, contra text, celular integer)");
        bd.execSQL("insert into cliente values(1, 'alexis', 'diego', 'alexis', 'alexis', 'alexis', 5563190817)");
    }
}
