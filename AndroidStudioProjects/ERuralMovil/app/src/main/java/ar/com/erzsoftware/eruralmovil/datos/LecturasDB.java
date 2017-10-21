package ar.com.erzsoftware.eruralmovil.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.modelos.lecturas;

/**
 * Created by Administrador on 13/10/2017.
 */

public class LecturasDB extends erural {
    public LecturasDB(Context context) {super(context);}

    private lecturas CargarObj(Cursor res){
        lecturas mLet = new lecturas();

        mLet.setId          (res.getString(res.getColumnIndex("_id")));
        mLet.setEmpresa     (res.getString(res.getColumnIndex("empresa")));
        mLet.setPeriodo     (res.getString(res.getColumnIndex("periodo")));
        mLet.setTipaux      (res.getString(res.getColumnIndex("tipaux")));
        mLet.setNroaux      (res.getString(res.getColumnIndex("nroaux")));
        mLet.setFeclect     (res.getString(res.getColumnIndex("feclect")));
        mLet.setTiplectura  (res.getString(res.getColumnIndex("tiplectura")));
        mLet.setConsumo     (Integer.parseInt(res.getString(res.getColumnIndex("consumo"))));

        return mLet;

    }
    public ArrayList<lecturas> Listado (){

        ArrayList<lecturas> datos = new ArrayList<lecturas>();
        SQLiteDatabase db = super.getReadableDatabase();


        Cursor cursor = db.query("LECTURAS", new String[] { "*" },
                null,
                null, null, null, "_id Desc", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            datos.add(CargarObj(cursor));
        }
        Log.d("DataBase Lecturalistado","Listado");


        return  datos;
    }

    public void Vaciar(){


        Log.d("DataBase ","Vaciar LECTURAS");

        super.ExecutarSQL( "DELETE FROM LECTURAS;" );

    }

    public void guardarLectura(lecturas milect){
        SQLiteDatabase db = super.getReadableDatabase();

        String cmd ="INSERT INTO LECTURAS( _id,empresa,periodo,tipaux,nroaux, " +
                " feclect,tiplectura,consumo )VALUES (" ;
        cmd+="'"+milect.getId() +"',";
        cmd+="'"+milect.getEmpresa() +"',";
        cmd+="'"+milect.getPeriodo() +"',";
        cmd+="'"+milect.getTipaux()+"',";
        cmd+="'"+milect.getNroaux() +"',";
        cmd+="'"+milect.getFeclect() +"',";
        cmd+="'"+milect.getTiplectura() +"',";
        cmd+=milect.getConsumo() +");";

        Log.d("DataBase guardarLectura",cmd);

        super.ExecutarSQL( cmd );

    }

}
