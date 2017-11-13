package ar.com.erzsoftware.eruralmovil.controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.MainActivity;
import ar.com.erzsoftware.eruralmovil.activity_Welcome;
import ar.com.erzsoftware.eruralmovil.datos.AuxiliaresDB;
import ar.com.erzsoftware.eruralmovil.datos.EmpresasDB;
import ar.com.erzsoftware.eruralmovil.datos.FacturasDB;
import ar.com.erzsoftware.eruralmovil.datos.LecturasDB;
import ar.com.erzsoftware.eruralmovil.lecturasbrw;
import ar.com.erzsoftware.eruralmovil.listado;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.DatosDSN;
import ar.com.erzsoftware.eruralmovil.modelos.Factura;
import ar.com.erzsoftware.eruralmovil.modelos.empresa;
import ar.com.erzsoftware.eruralmovil.modelos.firereclamos;
import ar.com.erzsoftware.eruralmovil.modelos.lecturas;

import static ar.com.erzsoftware.eruralmovil.MainActivity.mictrPrincipal;

/**
 * Created by Administrador on 13/10/2017.
 */

public class ctrPrincipal {
    private Context context;
    private Auxiliares miAuxi;
    private empresa miEmp;
    private DatosDSN midatosdsn;
    private AuxiliaresDB dbauxi ;
    private EmpresasDB dbempresa ;
    private LecturasDB dblect;
    private FacturasDB dbfact;
    private ctrservices miservice;
    private ctrfirebase ctrfire;
    private boolean ingreso;
    private ArrayList<Factura> AFacDatos = new ArrayList<Factura>();
    private ArrayList<lecturas> datoslect = new ArrayList<lecturas>();
    private ArrayList<empresa> afireempresa = new ArrayList<empresa>();

    public ctrPrincipal(Context context){
        this.context    = context;
        this.midatosdsn = new DatosDSN();
        this.dbauxi     = new AuxiliaresDB(this.context);
        this.dblect     = new LecturasDB(this.context);
        this.dbempresa  = new EmpresasDB(this.context);
        this.dbfact     = new FacturasDB(this.context);
        this.miAuxi     = dbauxi.ObtenerUltimoActivo();
        this.miEmp      = dbempresa.ObtenerUltimaEmpresa();
        this.miservice  = new ctrservices(this.context) ;
        this.ctrfire    = new ctrfirebase();
        this.ingreso    = false;
    }

    public boolean getIngreso() {return ingreso;    }

    public void setIngreso(boolean ingreso) {this.ingreso = ingreso;}

    public Context getContext() {return context;}

    public void setContext(Context context) {this.context = context;}

    public Auxiliares getMiAuxi() {return miAuxi;}

    public void setMiAuxi(Auxiliares miAuxi) {this.miAuxi = miAuxi;}

    public DatosDSN getMidatosdsn() {return midatosdsn;}

    public void setMidatosdsn(DatosDSN midatosdsn) {this.midatosdsn = midatosdsn;}

    public AuxiliaresDB getDbauxi() {return dbauxi;}

    public void setDbauxi(AuxiliaresDB dbauxi) {this.dbauxi = dbauxi;}

    public ctrservices getMiservice() {return miservice;}

    public void setMiservice(ctrservices miservice) {this.miservice = miservice;}

    public LecturasDB getDblect() {return dblect;}

    public void setDblect(LecturasDB dblect) {this.dblect = dblect;}

    public void BuscarLecturasUrl(final Context BaseContext, final View view, AppCompatActivity activity){
        final ctrservices miservice = this.getMiservice();

        final ProgressDialog pd;
        pd = new ProgressDialog(activity);
        pd.setTitle("Consultando Lecturas..");
        //pd.setIcon();
        pd.setMessage("Aguarde unos instantes...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();


        miservice.setBaseContext(BaseContext);
        miservice.VerLecturas(BaseContext, view, mictrPrincipal.getMiAuxi().getnroaux());
        Runnable miExe =new Runnable() {
            public void run(){
                int i=0;
                do {
                    i=miservice.getRespuestas().length();
                    //Log.d("Login","Cargando");
                }while(i == 0);

                try {
                    JSONArray respuesta = new JSONArray(miservice.getRespuestas());

                    dblect.Vaciar();
                    String id ="-1";
                    String Empresa = "Error";
                    for (i = 0; i < respuesta.length(); i++) {

                        JSONObject jsonobject = respuesta.getJSONObject(i);
                        if (jsonobject.has("Id")) {
                            id=jsonobject.getString("Id");
                        }
                        Log.d("LECTURAS", id);
                        if (jsonobject.has("Empresa")) {
                            Empresa = jsonobject.getString("Empresa");
                            Log.d("LECTURAS String", jsonobject.getString("Empresa"));
                        }


                        Log.d("LECTURAS", Empresa );
                        if (id.equals("-1")) {
                        } else {
                            lecturas milect= new lecturas();
                            milect.setdesdeJSONArray(jsonobject, mictrPrincipal.getMiAuxi().getnroaux());
                            dblect.guardarLectura(milect);


                        }

                    }
//                    Log.d("LECTURAS", "AAAABrir");
                    Intent intent = new Intent(ctrPrincipal.this.getContext(), lecturasbrw.class);
                    view.getContext().startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(BaseContext, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                pd.dismiss();
            }
        };
        Thread miThread = new Thread(miExe);
        miThread.start();
    }

    public ArrayList<lecturas> getDatoslect() {

        this.datoslect = this.dblect.Listado();
        return datoslect;
    }

    public FacturasDB getDbfact() {return dbfact;}

    public void setDbfact(FacturasDB dbfact) {this.dbfact = dbfact;}

    public ArrayList<Factura> getAFacDatos() {
        this.AFacDatos = this.dbfact.Listado();
        return AFacDatos;
    }

    public void BuscarFacturasUrl(final Context BaseContext, final View view, AppCompatActivity activity){

        final ctrservices miservice = this.getMiservice();
        miservice.setBaseContext(BaseContext);

        final ProgressDialog pd;
        pd = new ProgressDialog(activity);
        pd.setTitle("Consultando Facturas..");
        //pd.setIcon();
        pd.setMessage("Aguarde unos instantes...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();



        miservice.VerFacturas(BaseContext, view, mictrPrincipal.getMiAuxi().getnroaux());
        Runnable miExe =new Runnable() {
            public void run(){
                int i=0;
                do {
                    i=miservice.getRespuestas().length();
                    //Log.d("Login","Cargando");
                }while(i == 0);

                try {
                    JSONArray respuesta = new JSONArray(miservice.getRespuestas());



                    dbfact.Vaciar();

                    for (i = 0; i < respuesta.length(); i++) {

                        JSONObject jsonobject = respuesta.getJSONObject(i);
                        Factura fact = new Factura();
                        fact.setdesdeJSONArray(jsonobject, mictrPrincipal.getMiAuxi().getnroaux());

                        dbfact.guardarFactura(fact);

                    }
                    Intent intent = new Intent(ctrPrincipal.this.getContext(), listado.class);
                    view.getContext().startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(BaseContext, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                pd.dismiss();
            }
        };
        Thread miThread = new Thread(miExe);
        miThread.start();
    }

    public void setAFacDatos(ArrayList<Factura> AFacDatos) {this.AFacDatos = AFacDatos;}

    public void setDatoslect(ArrayList<lecturas> datoslect) {this.datoslect = datoslect;}

    public void Ingresar(final Context BaseContext, final View view, Editable nCli, Editable pass, MainActivity mainActivity){
        final ctrservices miservice = this.getMiservice();

        String miurl = "http://"+mictrPrincipal.getMiEmp().host+"/validar/";

        miservice.setMiurl(miurl);
        this.setMiservice(miservice);


        miservice.setBaseContext(BaseContext);


        final ProgressDialog pd;
        pd = new ProgressDialog(mainActivity);
        pd.setTitle("Ingresando..");
        pd.setMessage("Aguarde unos instantes...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);

        pd.show();
        miservice.Loggin(BaseContext, view, nCli, pass);

        Runnable miExe =new Runnable() {
            public void run(){
                int i=0;
                do {
                    i=miservice.getRespuestas().length();

                }while(i == 0);

                String id = "-1";
                String msj = "Error desconocido.";



                try {

                    //pd.dismiss();
                    //Log.d("Ingresar Crtprincipal",miservice.getRespuestas());

                    JSONArray respuesta = null;

                    respuesta = new JSONArray(miservice.getRespuestas());



                    for (i = 0; i < respuesta.length(); i++) {
                        Log.d("Login", String.valueOf(i));
                        JSONObject jsonobject = respuesta.getJSONObject(i);
                        id = jsonobject.getString("Id");
                        msj = jsonobject.getString("empresa");

                        //Toast.makeText(BaseContext, "Prueba 2", Toast.LENGTH_LONG).show();
                    }
//                    Toast.makeText(BaseContext, "Prueba 3", Toast.LENGTH_LONG).show();
                    if (id.equals("-1")) {
                        Snackbar.make(view, msj, Snackbar.LENGTH_LONG).setAction("Action", null).show();


                    } else {

                        //Auxiliares miauxi = new Auxiliares();
                        miAuxi.setdesdeJSONArray(respuesta);
                        midatosdsn.setdesdeJSONArray(respuesta);
                        if (miAuxi.getId() != "0") {
                            //actualizar auxiliar -usuario activo

                            mictrPrincipal.getDbauxi().ActualizarAuxi(miAuxi);
                            mictrPrincipal.setMiAuxi(miAuxi);
                            mictrPrincipal.setIngreso(true);

                            Intent intent = new Intent(ctrPrincipal.this.getContext(), activity_Welcome.class);
                            view.getContext().startActivity(intent);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                    //Toast.makeText(BaseContext, msj, Toast.LENGTH_LONG).show();
                }

                pd.dismiss();
            }
        };
        Thread miThread = new Thread(miExe);
        miThread.start();

    }

    public ctrfirebase getCtrfire() {return ctrfire;}

    public void setCtrfire(ctrfirebase ctrfire) {this.ctrfire = ctrfire;}

    public empresa getMiEmp() {return miEmp;}

    public void setMiEmp(empresa miEmp) {this.miEmp = miEmp;}

    public EmpresasDB getDbempresa() {return dbempresa;}

    public ArrayList<empresa> getAfireempresa() {return afireempresa;}

    public void setAfireempresa(ArrayList<empresa> afireempresa) {this.afireempresa = afireempresa;}

    public void setDbempresa(EmpresasDB dbempresa) {this.dbempresa = dbempresa;}

    public void EnviarReclamo(final Context BaseContext, View view, firereclamos miReclamo,AppCompatActivity activity){
        final ctrservices miservice = this.getMiservice();


        miservice.setBaseContext(BaseContext);


        final ProgressDialog pd;
        pd = new ProgressDialog(activity);
        pd.setTitle("Enviando Reclamo..");
        pd.setMessage("Aguarde unos instantes...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();
        miservice.EnviarReclamos(BaseContext, view, miReclamo);

        Runnable miExe =new Runnable() {
            public void run(){
                int i=0;
                do {
                    i=miservice.getRespuestas().length();

                }while(i == 0);

                Log.d("Reclamos",miservice.getRespuestas());
                pd.dismiss();
            }
        };
        Thread miThread = new Thread(miExe);
        miThread.start();

    }
}
