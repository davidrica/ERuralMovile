package ar.com.erzsoftware.eruralmovil.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;

/**
 * Created by Administrador on 13/10/2017.
 */

public class AuxiliaresDB extends erural {
    public AuxiliaresDB(Context context) { super(context); }

    private Auxiliares CargarObj ( Cursor res){
        Auxiliares miAux = new Auxiliares();
        if (res.getCount()>0) {
            //_id, empresa, tipaux, nroaux, denom, tele,dire,loca, codloca , postal , prov , siva , tipdoc , nrodoc , cuit

            miAux.setId     (res.getString(res.getColumnIndex("_id")));
            miAux.setempresa(res.getString(res.getColumnIndex("empresa")));
            miAux.settipaux (res.getString(res.getColumnIndex("tipaux")));
            miAux.setnroaux (res.getString(res.getColumnIndex("nroaux")));
            miAux.setdenom  (res.getString(res.getColumnIndex("denom")));
            miAux.settele   (res.getString(res.getColumnIndex("tele")));
            miAux.setdire   (res.getString(res.getColumnIndex("dire")));
            miAux.setloca   (res.getString(res.getColumnIndex("loca")));
            miAux.setcodloca(res.getString(res.getColumnIndex("codloca")));
            miAux.setpostal (res.getString(res.getColumnIndex("postal")));
            miAux.setprov   (res.getString(res.getColumnIndex("prov")));
            miAux.setsiva   (res.getString(res.getColumnIndex("siva")));
            miAux.settipdoc (res.getString(res.getColumnIndex("tipdoc")));
            miAux.setnrodoc (res.getString(res.getColumnIndex("nrodoc")));
            miAux.setcuit   (res.getString(res.getColumnIndex("cuit")));

        }else{
            res.close();
        }
        return miAux;
    }

    public Auxiliares ObtenerUltimoActivo(){

        SQLiteDatabase db = super.getReadableDatabase();


        Cursor cursor = db.query("AUXI", new String[] { "*" },
                null,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        Cursor res =cursor;
        Auxiliares miAux=CargarObj(res);

        return miAux;


    }
    public Auxiliares ActualizarAuxi (Auxiliares miAux){
        super.ExecutarSQL("DELETE FROM AUXI;");

        String cmd ="INSERT INTO AUXI(_id, "+
                " empresa,tipaux,nroaux,denom,tele," +
                " dire,loca,codloca,postal,prov," +
                " siva,tipdoc,nrodoc,cuit) VALUES (1,";
        cmd+="'"+miAux.getempresa() +"',";
        cmd+="'"+miAux.gettipaux() +"',";
        cmd+="'"+miAux.getnroaux() +"',";
        cmd+="'"+miAux.getdenom() +"',";
        cmd+="'"+miAux.gettele() +"',";
        cmd+="'"+miAux.getdire() +"',";
        cmd+="'"+miAux.getloca() +"',";
        cmd+="'"+miAux.getcodloca() +"',";
        cmd+="'"+miAux.getpostal() +"',";
        cmd+="'"+miAux.getprov() +"',";
        cmd+="'"+miAux.getsiva() +"',";
        cmd+="'"+miAux.gettipdoc() +"',";
        cmd+="'"+miAux.getnrodoc() +"',";
        cmd+="'"+miAux.getcuit() +"');";


        super.ExecutarSQL( cmd );

        return miAux;
    }
}
