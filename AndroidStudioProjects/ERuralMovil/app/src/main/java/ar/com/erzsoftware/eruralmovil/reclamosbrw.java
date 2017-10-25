package ar.com.erzsoftware.eruralmovil;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.crypto.SecretKey;

import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.Factura;
import ar.com.erzsoftware.eruralmovil.modelos.MisFacturas;
import ar.com.erzsoftware.eruralmovil.modelos.MisReclamos;
import ar.com.erzsoftware.eruralmovil.modelos.firereclamos;

public class reclamosbrw extends AppCompatActivity {
    public final static ctrPrincipal mictrPrincipal = MainActivity.mictrPrincipal;

    public final static Auxiliares miauxi = MainActivity.miauxi;
    public ListView lvstring;

    public  ArrayList<firereclamos> datos = new ArrayList<firereclamos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_reclamosbrw);
        setContentView(R.layout.content_reclamosbrw);

//firebase=====================================================================================
        DatabaseReference myRef = mictrPrincipal.getCtrfire().getRefReclamos();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loadData(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("Firebase", "Error en lectura -VALUE-.", error.toException());
            }
        });
//===================================================================================
    }

    //load some initial data into out list
    private void loadData(DataSnapshot dataSnapshot) {
        datos.clear();
        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
            //String nKey = childSnapshot.getValue(String.class);
            firereclamos value = childSnapshot.getValue(firereclamos.class);

            value.key = childSnapshot.getKey();
            if (miauxi.getnroaux().equals(value.usuario)) {
                datos.add(value);
                //key
                //Log.d("Firebase", value.key);

            }
        }
        //Log.d("Firebase", String.valueOf(datos.size()));
        if (!datos.isEmpty()) {

            lvstring = (ListView) findViewById(R.id.ListView_reclamos);

            lvstring.setAdapter(new MisReclamos(this, R.layout.activity_reclamosbrw, datos) {

                @Override
                public void onEntrada(Object entrada, View view) {
                    if (entrada != null) {
                        int colEnviado = 0xFFCC0000;
                        int colAceptado= 0xFF669900;
                        int colListo   = 0xFF669900;

                        String fecha =((firereclamos) entrada).fecha;
                        String tema=((firereclamos) entrada).tema;
                        String estado=((firereclamos) entrada).estado;

                        TextView texto_superior_entrada = view.findViewById(R.id.txtReclamoFecha);
                        TextView texto_inferior_entrada = view.findViewById(R.id.txtReclamoTema);
                        TextView actual                 = view.findViewById(R.id.txtReclamoEstado);

                        if (texto_superior_entrada != null) {
                            texto_superior_entrada.setText(fecha);
                        }
                        if (texto_inferior_entrada != null) {
                            texto_inferior_entrada.setText(tema);
                        }
                        if (actual != null) {
                            actual.setText(estado);
                            Log.d("Firebase",estado);
                            if (estado.equals("ENVIADO")){
                                actual.setTextColor(colEnviado);
                            }
                            if (estado.equals("ACEPTADO")){
                                actual.setTextColor(colAceptado);
                            }
                            if (estado.equals("LISTO")){
                                actual.setTextColor(colListo);
                            }


                        }

                    }
              }
            });
            lvstring.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object listItem = lvstring.getItemAtPosition(position);
                    String fireestado = ((firereclamos) listItem).estado;
//                    Snackbar.make(view, "Buenas " +fireestado , Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
            lvstring.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

                    MenuInflater inflater = getMenuInflater();

                    inflater.inflate(R.menu.reclamos, contextMenu);


                }

            });
        }

    }
    //here we maintain our products in various departments
    public void ReclamosCerrar(View view){finish();}

    public void ReclamosAdd(View view){
        Intent intent = new Intent(getBaseContext(), reclamosadd.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo cmi =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo ();
        Object listItem = lvstring.getItemAtPosition(cmi.position);

        switch (item.getItemId()) {
            case R.id.menu_reclamos_borrar:
                Log.d("Reclamos","borrar");
                //DescargarPDF(per,nro);
                return true;
            case R.id.menu_reclamos_ver:
                Log.d("Reclamos","Ver");
                return true;
            case R.id.menu_reclamos_modificar:
                Log.d("Reclamos","Modificar");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
