package ar.com.erzsoftware.eruralmovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.controladores.ctrservices;
import ar.com.erzsoftware.eruralmovil.datos.LecturasDB;
import ar.com.erzsoftware.eruralmovil.datos.erural;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.lecturas;

public class lecturasAddEdit extends AppCompatActivity {
    public final static ctrPrincipal mictrPrincipal = MainActivity.mictrPrincipal;

    public final static Auxiliares miauxi = MainActivity.miauxi;
    public Date feclect= new Date();

    public LecturasDB dblect;
    public ArrayList<lecturas> datoslect = new ArrayList<lecturas>();

    public EditText edConsumo;
    public TextView edFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturas);

        Intent intent = getIntent();

        edFecha=(TextView)findViewById(R.id.edAddLecturasFecha);
        edConsumo = (EditText)findViewById(R.id.edAddLecturasConsumo);
        edFecha.setText(DateFormat.format("dd/MM/yyyy", feclect.getTime()));
        dblect= new LecturasDB(getBaseContext());
        mictrPrincipal.setDblect(dblect);

    }
    public void LecturasEnviar(View view) {
        /*
        empresa=001
        periodo=201710
        tipaux=ERU
        nroaux=1
        feclect=2017-10-04
        tiplectura=m
        consumo=100
        */

        if (edFecha.length() != 0) {
            if (edConsumo.length() != 0) {
                final String tipaux = miauxi.gettipaux();
                final String fecha = String.valueOf(edFecha.getText());
                final String tiplectura ="M";
                final String empresa = miauxi.getempresa();
                final String periodo = (String) DateFormat.format("yyyyMM", feclect.getTime());
                final String nroaux = miauxi.getnroaux();
                final String consumo = String.valueOf(edConsumo.getText());
                final ctrservices miservice = new ctrservices(getBaseContext());

                final ProgressDialog pd;
                pd = new ProgressDialog(lecturasAddEdit.this);
                pd.setTitle("Enviando Lectura..");
                //pd.setIcon();
                pd.setMessage("Aguarde unos instantes...");
                pd.setIndeterminate(false);
                pd.setCancelable(false);
                pd.show();


                miservice.setBaseContext(getBaseContext());
                miservice.AddLecturas(getBaseContext(), view, empresa,periodo,tipaux,nroaux,fecha,tiplectura,consumo);
                Runnable miExe =new Runnable() {
                    public void run(){
                        int i=0;
                        do {
                            i=miservice.getRespuestas().length();
                            //Log.d("Login","Cargando");
                        }while(i == 0);

                        try {
                            JSONArray respuesta = new JSONArray(miservice.getRespuestas());

                            String id ="-1";
                            String error ="-1";
                            String Empresa = "Error";
                            for (i = 0; i < respuesta.length(); i++) {

                                JSONObject jsonobject = respuesta.getJSONObject(i);
                                if (jsonobject.has("error")) {
                                    error=jsonobject.getString("error");
                                }
                                if (jsonobject.has("Id")) {
                                    id=jsonobject.getString("Id");
                                }
                                Empresa = jsonobject.getString("codigo");
                                if (error.equals("-1")) {
                                    Log.d("LECTURAS", "ERROR");
                                } else {
                                    Log.d("LECTURAS", "OK");
//                                    erural dbHelper = new erural(getBaseContext());
//                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    lecturas milect= new lecturas();


                                    milect.setId(id);
                                    milect.setEmpresa(empresa);
                                    milect.setPeriodo(periodo);
                                    milect.setTipaux(tipaux);
                                    milect.setNroaux(nroaux);
                                    milect.setFeclect(fecha);
                                    milect.setTiplectura(tiplectura);
                                    milect.setConsumo(Integer.parseInt(consumo));
//                                    milect.setdesdeJSONArray(jsonobject, miauxi.getnroaux());
                                    dblect.guardarLectura(milect);

//                                    dbHelper.guardarLecturas(milect);
                                    finish();
                                }

                                //Toast.makeText(getBaseContext(), fact.getPeriodo(), Toast.LENGTH_LONG).show();
                            }
                            //abrimos el listado

                        } catch (JSONException e) {
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        pd.dismiss();

                    }
                };
                Thread miThread = new Thread(miExe);
                miThread.start();

            }
        }
    }
}
