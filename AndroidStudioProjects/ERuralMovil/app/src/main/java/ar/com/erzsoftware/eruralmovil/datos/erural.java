package ar.com.erzsoftware.eruralmovil.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.Factura;
import ar.com.erzsoftware.eruralmovil.modelos.lecturas;

/**
 * Created by Administrador on 25/09/2017.
 */

public class erural extends SQLiteOpenHelper {

    private static int version = 7;
    private static String name = "eruralDB" ;
    private static SQLiteDatabase.CursorFactory factory = null;
    //====================Tabla acturas
    private String TablaFacts = "CREATE TABLE FACT(_id INTEGER PRIMARY KEY, periodo TEXT NOT NULL, perasto TEXT NOT NULL,nroasto TEXT NOT NULL, " +
            " liquidacion TEXT,nroaux TEXT, cesp TEXT, pvcomp TEXT, nrcomp TEXT,fecomp TEXT,feclect TEXT,fevenc1 TEXT,medant TEXT,medact TEXT," +
            " consumo TEXT,total TEXT,estado TEXT, pagada TEXT);";
    private String TablaFactsIndex = "CREATE UNIQUE INDEX per_liq ON FACT(periodo,liquidacion);";
    //====================Tabla Auxiliares
    private String TablaAuxis="CREATE TABLE AUXI(_id INTEGER PRIMARY KEY,empresa TEXT NOT NULL,tipaux TEXT,nroaux TEXT,denom TEXT,tele TEXT," +
            " dire TEXT,loca TEXT, codloca TEXT, postal TEXT, prov TEXT, siva TEXT, tipdoc TEXT, nrodoc TEXT, cuit TEXT);" ;
    private String TablaAuxisIndex="CREATE UNIQUE INDEX nro ON AUXI(nroaux);";
    //====================Tabla Lecturas
    private String TablaLecturas= "CREATE TABLE LECTURAS( _id INTEGER PRIMARY KEY, empresa TEXT NOT NULL,  periodo TEXT,  tipaux TEXT,  nroaux TEXT," +
            " feclect TEXT, tiplectura TEXT, consumo INTEGER);";

    //====================Tabla EmpresaSeleccion
    private String TablaEmpresa= "CREATE TABLE EMPRESA( _id INTEGER PRIMARY KEY, denom TEXT NOT NULL,  host TEXT,  dbase TEXT,  user TEXT," +
            " pass TEXT, estado TEXT, tipo TEXT,key TEXT);";


    public erural(Context context){ super(context, name, factory, version);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( TablaFacts  );

        sqLiteDatabase.execSQL(TablaFactsIndex  );

        sqLiteDatabase.execSQL( TablaAuxis);

        sqLiteDatabase.execSQL( TablaAuxisIndex );

        sqLiteDatabase.execSQL(TablaLecturas );

        sqLiteDatabase.execSQL(TablaEmpresa);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS FACT");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AUXI");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LECTURAS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS EMPRESA");

        sqLiteDatabase.execSQL(TablaFacts);
        sqLiteDatabase.execSQL(TablaFactsIndex );

        sqLiteDatabase.execSQL(TablaAuxis);
        sqLiteDatabase.execSQL(TablaAuxisIndex);

        sqLiteDatabase.execSQL(TablaLecturas);

        sqLiteDatabase.execSQL(TablaEmpresa);

    }

    public void ExecutarSQL(String commandSQL){
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL( commandSQL );
        Log.d("DataBase ExecutarSQL",commandSQL);

    }




    public Cursor obtenerLecturas(){

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query("LECTURAS", new String[] { "*" },
                null,
                null, null, null, "_id Desc", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public void guardarLecturas(lecturas milect){

        SQLiteDatabase db = this.getReadableDatabase();



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

        db.execSQL( cmd );


    }

}
