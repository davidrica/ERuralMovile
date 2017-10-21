package ar.com.erzsoftware.eruralmovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.IDN;
import java.security.Key;
import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.controladores.ctrservices;
import ar.com.erzsoftware.eruralmovil.datos.FacturasDB;
import ar.com.erzsoftware.eruralmovil.datos.LecturasDB;
import ar.com.erzsoftware.eruralmovil.datos.erural;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.Factura;
import ar.com.erzsoftware.eruralmovil.modelos.lecturas;


public class activity_Welcome extends AppCompatActivity {
    public final static ctrPrincipal mictrPrincipal = MainActivity.mictrPrincipal;

    public final static Auxiliares miauxi =mictrPrincipal.getMiAuxi();
    public FacturasDB dbfact;
    public ArrayList<Factura> datos = new ArrayList<Factura>();

    public LecturasDB dblect;
    public ArrayList<lecturas> datoslect = new ArrayList<lecturas>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__welcome);

        Intent intent = getIntent();
        TextView Bienvenidos = (TextView) findViewById(R.id.txtwelcome);
        Bienvenidos.setText(miauxi.getdenom());


    }

    public void ConsultarFacturas(View view){

        dbfact = new FacturasDB(getBaseContext());
        mictrPrincipal.setDbfact(dbfact );
        datos= mictrPrincipal.getAFacDatos();

        if (!datos.isEmpty()) {
            Factura miFact = datos.get(0);
            String nro = miFact.getNroaux();


            if (!miauxi.getnroaux().equals(nro)){
                Log.d("Facturas","ok");
                BuscarFacturas(view);
            }else {
                Log.d("Facturas","not");
                //abrimos el listado
                Intent intent = new Intent(getBaseContext(), listado.class);
                startActivity(intent);

            }
        }else {
            BuscarFacturas(view);
        }


    }

    public void Terminar(View view) {
        finish();
    }

    public void BuscarFacturas(View view){
        mictrPrincipal.BuscarFacturasUrl(getBaseContext(),view,activity_Welcome.this);

    }

    public void EnviarLectura(View  view) {
        dblect= new LecturasDB(getBaseContext());
        mictrPrincipal.setDblect(dblect);

        datoslect =mictrPrincipal.getDatoslect();

        if (!datoslect.isEmpty()) {
            lecturas milect = datoslect.get(0);
            String nro = milect.getNroaux();
            if (!miauxi.getnroaux().equals(nro)){
                BuscarLecturas(view);
            }else {
                //abrimos el listado
                Intent intent = new Intent(getBaseContext(), lecturasbrw.class);
                startActivity(intent);

            }
        }else {
            BuscarLecturas(view);
        }




    }

    public void BuscarLecturas(View view) {

        mictrPrincipal.BuscarLecturasUrl(getBaseContext(),view,activity_Welcome.this);

    }

    public void VerReclamos(final View view){
        Intent intent = new Intent(getBaseContext(), reclamosbrw.class);
        startActivity(intent);

    }

}
