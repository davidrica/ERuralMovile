package ar.com.erzsoftware.eruralmovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.print.PrintAttributes;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.controladores.ctrservices;
import ar.com.erzsoftware.eruralmovil.datos.AuxiliaresDB;
import ar.com.erzsoftware.eruralmovil.datos.FacturasDB;
import ar.com.erzsoftware.eruralmovil.datos.erural;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.Factura;
import ar.com.erzsoftware.eruralmovil.modelos.FacturaPDF;
import ar.com.erzsoftware.eruralmovil.modelos.MisFacturas;

public class listado extends AppCompatActivity {

    public final static Auxiliares miauxi = MainActivity.miauxi;
    public FacturasDB dbfact;
    public ArrayList<Factura> datos = new ArrayList<Factura>();

    public ListView lvstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listado);

        setContentView(R.layout.content_listado);
        Intent intent = getIntent();
        dbfact = new FacturasDB(getBaseContext());

        CargarFacturas();


    }
    public void CargarFacturas(){
//=============================================
        datos= dbfact.Listado();

        lvstring = (ListView) findViewById(R.id.ListView_listado);
        lvstring.setAdapter(new MisFacturas(this, R.layout.activity_mis__facturas, datos){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = view.findViewById(R.id.txtVto1);
                    if (texto_superior_entrada != null) {
                        texto_superior_entrada.setText(((Factura) entrada).getFevenc1());
                    }
                    TextView texto_inferior_entrada = view.findViewById(R.id.txtTotal);
                    if (texto_inferior_entrada != null) {
                        texto_inferior_entrada.setText(((Factura) entrada).getTotal());
                    }
                    TextView actual= view.findViewById(R.id.txtActual);
                    if (actual != null) {
                        actual.setText(((Factura) entrada).getMedact());
                    }
                    TextView consu= view.findViewById(R.id.txtConsu);
                    if (consu != null) {
                        consu.setText(((Factura) entrada).getConsumo());
                    }
                    TextView ante= view.findViewById(R.id.txtAnte);
                    if (ante != null) {
                        ante.setText(((Factura) entrada).getMedant());
                    }
                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null) {
                        if (((Factura) entrada).getPagada().equals("S")) {
                            imagen_entrada.setImageResource(R.mipmap.ic_stop);
                        } else {
                            imagen_entrada.setImageResource(R.mipmap.ic_select);
                        }
                    }
                    //imagen_entrada.setImageResource( ((Factura) entrada).getPagada());
                }


            }
        });
        lvstring.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Object listItem = lvstring.getItemAtPosition(position);
                String per = ((Factura) listItem).getPerasto();
                String nro = ((Factura) listItem).getNroasto();


                final ctrservices miservice = new ctrservices(getBaseContext());

                final ProgressDialog pd;
                pd = new ProgressDialog(listado.this);
                pd.setTitle("Obteniendo Factura...");
                //pd.setIcon();
                pd.setMessage("Aguarde unos instantes...");
                pd.setIndeterminate(false);
                pd.setCancelable(false);
                pd.show();


                miservice.setBaseContext(getBaseContext());
                miservice.VerFacturasPDF(getBaseContext(),per,nro );
                Runnable miExe = new Runnable() {
                    public void run() {
                        int i = 0;
                        do {
                            i = miservice.getRespuestas().length();

                        } while (i == 0);

                        try {
                            String id = "-1";
                            String Empresa = "Error";

                            JSONArray respuesta = new JSONArray(miservice.getRespuestas());

                            for (i = 0; i < respuesta.length(); i++) {
                                // [{"Id":"-1","Empresa":"No se pueden obtener los datos"}]

                                JSONObject jsonobject = respuesta.getJSONObject(i);
                                if (jsonobject.has("Id")) {
                                    id = jsonobject.getString("Id");
                                }
                                Empresa = jsonobject.getString("Empresa");

                                if (id.equals("-1")) {
                                } else {
                                    FacturaPDF fact = new FacturaPDF();
                                    fact.setdesdeJSONArray(jsonobject);
//============================================CREAR
                                    String string64 = fact.getPdf();
                                    Log.d("FacturasPDF",string64);

                                    try {


                                        byte[] pdfAsBytes = Base64.decode(string64, 0);
                                        //File filePath = new File(Environment.getDownloadCacheDirectory() + "/braodcasts.pdf");
                                        File path= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());

                                        String targetPdf = path+"/fact-"+ fact.getPeriodo()+fact.getPvcomp()+fact.getNrcomp() +".pdf";
                                        Log.d("FacturasPDF",targetPdf);

                                        FileOutputStream os = new FileOutputStream(targetPdf);
                                        os.write(pdfAsBytes);
                                        os.close();
//====abrir
                                        File pdfFile = new File(targetPdf);//File path
                                        if (pdfFile.exists()) //Checking for the file is exist or not
                                        {
                                            Uri path2 = Uri.fromFile(pdfFile);
                                            Intent objIntent = new Intent(Intent.ACTION_VIEW);
                                            objIntent.setDataAndType(path2, "application/pdf");
                                            objIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(objIntent);//Staring the pdf viewer
                                        } else {

                                            Toast.makeText(getBaseContext(), "Archivo no existe! ", Toast.LENGTH_SHORT).show();

                                        }

                                    }catch(FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                    //============================================CREAR

                                }


                            }


                        } catch (JSONException e) {
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        pd.dismiss();
                    }
                };
                Thread miThread = new Thread(miExe);
                miThread.start();


//                Toast.makeText(getBaseContext(),lvstring.getItemAtPosition(index).toString(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        lvstring.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Para Descargar la Factura. Mantenga Presionado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }
    public void FacturasCerrar(View view){
        finish();
    }
    public void FacturasActualizar(View view){
        final ctrservices miservice = new ctrservices(getBaseContext());

        final ProgressDialog pd;
        pd = new ProgressDialog(listado.this);
        pd.setTitle("Consultando Facturas..");
        //pd.setIcon();
        pd.setMessage("Aguarde unos instantes...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();


        miservice.setBaseContext(getBaseContext());
        miservice.VerFacturas(getBaseContext(), view, miauxi.getnroaux());
        Runnable miExe =new Runnable() {
            public void run() {
                int i = 0;
                do {
                    i = miservice.getRespuestas().length();
                    //Log.d("Login","Cargando");
                } while (i == 0);

                try {
                    JSONArray respuesta = new JSONArray(miservice.getRespuestas());
                    dbfact.Vaciar();
                    for (i = 0; i < respuesta.length(); i++) {

                        JSONObject jsonobject = respuesta.getJSONObject(i);
                        Factura fact = new Factura();
                        fact.setdesdeJSONArray(jsonobject, miauxi.getnroaux());
                        dbfact.guardarFactura(fact);

                    }

                } catch (JSONException e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                pd.dismiss();
                finish();
            }
        };
        Thread miThread = new Thread(miExe);
        miThread.start();
    }
}
