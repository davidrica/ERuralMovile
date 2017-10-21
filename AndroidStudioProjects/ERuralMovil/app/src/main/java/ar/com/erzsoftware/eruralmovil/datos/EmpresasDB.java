package ar.com.erzsoftware.eruralmovil.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import ar.com.erzsoftware.eruralmovil.modelos.empresa;

/**
 * Created by Administrador on 13/10/2017.
 */

public class EmpresasDB extends erural {

    public EmpresasDB(Context context) {
        super(context);
    }

    private empresa CargarObj ( Cursor res){
        empresa miEmp = new empresa();
        if (res.getCount()>0) {
            miEmp.id=res.getString(res.getColumnIndex("_id"));
            miEmp.denom=res.getString(res.getColumnIndex("denom"));
            miEmp.host=res.getString(res.getColumnIndex("host"));
            miEmp.dbase=res.getString(res.getColumnIndex("dbase"));
            miEmp.user=res.getString(res.getColumnIndex("user"));
            miEmp.pass=res.getString(res.getColumnIndex("pass"));
            miEmp.estado=res.getString(res.getColumnIndex("estado"));
            miEmp.tipo=res.getString(res.getColumnIndex("tipo"));
            miEmp.key=res.getString(res.getColumnIndex("key"));

        }else{
            res.close();
        }
        return miEmp;
    }

    public void Insertar(){    }

    public empresa ObtenerUltimaEmpresa(){

        SQLiteDatabase db = super.getReadableDatabase();


        Cursor cursor = db.query("EMPRESA", new String[] { "*" },
                null,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        Cursor res =cursor;
        empresa miAux=CargarObj(res);
        Log.d("DataBase Empresa",miAux.id);
        return miAux;


    }
    public void guardarEmpresa(empresa miEmpresa){
        SQLiteDatabase db = super.getReadableDatabase();


        String cmd ="INSERT INTO EMPRESA( _id, denom, host,  dbase ,  user ," +
                " pass , estado , tipo ,key)VALUES (" ;
        cmd+="'1',";
        cmd+="'"+miEmpresa.denom +"',";
        cmd+="'"+miEmpresa.host +"',";
        cmd+="'"+miEmpresa.dbase +"',";
        cmd+="'"+miEmpresa.user+"',";
        cmd+="'"+miEmpresa.pass +"',";
        cmd+="'"+miEmpresa.estado +"',";
        cmd+="'"+miEmpresa.tipo +"',";
        cmd+="'"+miEmpresa.key+"');";

        Log.d("DataBase guardarEmpresa",cmd);

        super.ExecutarSQL( cmd );

    }
    public void Vaciar(){


        Log.d("DataBase ","Vaciar Empresa");

        super.ExecutarSQL( "DELETE FROM EMPRESA;" );

    }
}
