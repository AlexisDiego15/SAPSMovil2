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
        bd.execSQL("create table mensaje(id_men integer primary key autoincrement, remitente text, cmensaje text, respuesta text)");
        bd.execSQL("create table productos(id_pro integer primary key autoincrement, nombrep text, precio integer, tipo text, descripcion text, cantidad integer)");
       /*bd.execSQL("insert into productos values(null, 'bistek', 120, 'carnes', 'carne fresca', 120)");
        bd.execSQL("insert into productos values(null, 'leche deslactosada', 9, 'lacteos', 'leche fresca', 200)");
        bd.execSQL("insert into productos values(null, 'melon', 12, 'frutas', 'fruta fresca', 218)");
        bd.execSQL("insert into productos values(null, 'jitomate', 18, 'verduras', 'verdura fresca', 172)");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int old, int nuevo) {
        bd.execSQL("create table cliente(id integer primary key autoincrement, nombre text, apellido text, correo text, usuario text, contra text, celular integer)");
        bd.execSQL("create table mensaje(id_men integer primary key autoincrement, remitente text, cmensaje text, respuesta text)");
        bd.execSQL("create table productos(id_pro integer primary key autoincrement, nombrep text, precio integer, tipo text, descripcion text, cantidad integer)");
       /*bd.execSQL("insert into productos values(null, 'bistek', 120, 'carnes', 'carne fresca', 120)");
        bd.execSQL("insert into productos values(null, 'leche deslactosada', 9, 'lacteos', 'leche fresca', 200)");
        bd.execSQL("insert into productos values(null, 'melon', 12, 'frutas', 'fruta fresca', 218)");
        bd.execSQL("insert into productos values(null, 'jitomate', 18, 'verduras', 'verdura fresca', 172)");*/
    }}
