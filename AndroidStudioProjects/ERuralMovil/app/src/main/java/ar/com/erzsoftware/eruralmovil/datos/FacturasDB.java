package ar.com.erzsoftware.eruralmovil.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.modelos.Factura;

/**
 * Created by Administrador on 13/10/2017.
 */

public class FacturasDB extends erural {

    public FacturasDB(Context context) { super(context);}

    private Factura CargarObj (Cursor res){
        Factura miFact = new Factura();
        miFact.setId(res.getString(res.getColumnIndex("_id")));
        miFact.setPeriodo(res.getString(res.getColumnIndex("periodo")));
        miFact.setPerasto(res.getString(res.getColumnIndex("perasto")));
        miFact.setNroasto(res.getString(res.getColumnIndex("nroasto")));
        miFact.setLiquidacion(res.getString(res.getColumnIndex("liquidacion")));
        miFact.setNroaux(res.getString(res.getColumnIndex("nroaux")));
        miFact.setCesp(res.getString(res.getColumnIndex("cesp")));
        miFact.setPvcomp(res.getString(res.getColumnIndex("pvcomp")));
        miFact.setNrcomp(res.getString(res.getColumnIndex("nrcomp")));
        miFact.setFecomp(res.getString(res.getColumnIndex("fecomp")));
        miFact.setFeclect(res.getString(res.getColumnIndex("feclect")));
        miFact.setFevenc1(res.getString(res.getColumnIndex("fevenc1")));
        miFact.setMedant(res.getString(res.getColumnIndex("medant")));
        miFact.setMedact(res.getString(res.getColumnIndex("medact")));
        miFact.setConsumo(res.getString(res.getColumnIndex("consumo")));
        miFact.setTotal(res.getString(res.getColumnIndex("total")));
        miFact.setEstado(res.getString(res.getColumnIndex("estado")));
        miFact.setPagada(res.getString(res.getColumnIndex("pagada")));

        return miFact ;
    }

    public ArrayList<Factura> Listado(){
        ArrayList<Factura> datos = new ArrayList<Factura>();

        SQLiteDatabase db = super.getReadableDatabase();


        Cursor cursor = db.query("FACT", new String[] { "*" },
                null,
                null, null, null, "periodo Desc", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            datos.add(CargarObj(cursor));
        }
        Log.d("DataBase Factuaslistado","Listado");


        return  datos;
    }

    public void guardarFactura(Factura mifac){
        SQLiteDatabase db = super.getReadableDatabase();


        String cmd ="INSERT INTO FACT( _id,periodo,perasto,nroasto,liquidacion,nroaux, " +
                " cesp,pvcomp,nrcomp,fecomp,feclect," +
                " fevenc1," +
                " medant ," +
                " medact ," +
                " consumo," +
                " total ," +
                " estado," +
                " pagada)VALUES (" ;
        cmd+="'"+mifac.getId() +"',";
        cmd+="'"+mifac.getPeriodo() +"',";
        cmd+="'"+mifac.getPerasto() +"',";
        cmd+="'"+mifac.getNroasto() +"',";
        cmd+="'"+mifac.getLiquidacion()+"',";
        cmd+="'"+mifac.getNroaux() +"',";
        cmd+="'"+mifac.getCesp() +"',";
        cmd+="'"+mifac.getPvcomp() +"',";
        cmd+="'"+mifac.getNrcomp() +"',";
        cmd+="'"+mifac.getFecomp() +"',";
        cmd+="'"+mifac.getFeclect() +"',";
        cmd+="'"+mifac.getFevenc1() +"',";
        cmd+="'"+mifac.getMedant()+"',";
        cmd+="'"+mifac.getMedact()+"',";
        cmd+="'"+mifac.getConsumo()+"',";
        cmd+="'"+mifac.getTotal()+"',";
        cmd+="'"+mifac.getEstado()+"',";
        cmd+="'"+mifac.getPagada()+"');";

        Log.d("DataBase guardarFactura",cmd);

        super.ExecutarSQL( cmd );

    }

    public void Vaciar(){


        Log.d("DataBase ","Vaciar Facturas");

        super.ExecutarSQL( "DELETE FROM FACT;" );

    }
}
