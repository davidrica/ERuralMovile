package ar.com.erzsoftware.eruralmovil.controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import ar.com.erzsoftware.eruralmovil.MainActivity;
import ar.com.erzsoftware.eruralmovil.activity_Welcome;
import ar.com.erzsoftware.eruralmovil.modelos.firereclamos;
import cz.msebera.android.httpclient.Header;


/**
 * Created by Administrador on 22/09/2017.
 */

public class ctrservices {
    protected String miurl;
    protected String findAbonado;
    protected String findFacturas;
    protected String findFacturasPDF;
    protected String findArticulos;
    protected String findLecturas;
    protected String AddLecturas;
    protected String AddReclamos;

    protected Context baseContext;
    protected String respuestas = "";

    protected Context context;


    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    public Context getBaseContext() {
        return baseContext;
    }

    public void setBaseContext(Context baseContext) {
        this.baseContext = baseContext;
    }

    protected Context BaseContext;

    public String getMiurl() {
        return miurl;
    }

    public void setMiurl(String miurl) {
        this.miurl = miurl;
    }

    public String getFindAbonado() {
        return findAbonado;
    }

    public void setFindAbonado(String findAbonado) {
        this.findAbonado = findAbonado;
    }

    public String getFindFacturas() {
        return findFacturas;
    }

    public void setFindFacturas(String findFacturas) {
        this.findFacturas = findFacturas;
    }

    public String getFindFacturasPDF() {return findFacturasPDF;}

    public void setFindFacturasPDF(String findFacturasPDF) {this.findFacturasPDF = findFacturasPDF;}

    public String getFindArticulos() {
        return findArticulos;
    }

    public void setFindArticulos(String findArticulos) {
        this.findArticulos = findArticulos;
    }

    public String getFindLecturas() {return findLecturas;}

    public void setFindLecturas(String findLecturas) {this.findLecturas = findLecturas;}

    public String getAddLecturas() {return AddLecturas;}

    public void setAddLecturas(String addLecturas) {AddLecturas = addLecturas;}

    public String getAddReclamos() {return AddReclamos;}

    public void setAddReclamos(String addReclamos) {AddReclamos = addReclamos;}

    public ctrservices(Context context) {
        this.miurl = "http://10.0.0.3/validar/";
        //this.miurl="http://186.109.91.169/validar/";
        this.findAbonado = "buscarabonado.php";
        this.findFacturas = "buscarfacturas.php";
        this.findFacturasPDF = "buscarfacturaspdf.php";
        this.findFacturas = "buscarfacturas.php";
        this.findArticulos = "buscararti.php";
        this.findLecturas = "buscarlecturas.php";
        this.AddLecturas = "lecturas.php";
        this.AddReclamos = "enviar.php";

        this.context = context;
        //this.loopjListener = listener;


        //
    }




    public void VerFacturas(final Context BaseContext, View view, String nCli) {

        respuestas="";

        final String Url = this.getMiurl() + this.getFindFacturas();
        //final String Url="http://186.109.91.169/validar/buscarabonado.php";

        final RequestParams params = new RequestParams();

        params.add("ncli", String.valueOf(nCli));
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.get(Url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {


                    String res = new String(responseBody);
                    respuestas = (String) res;

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(BaseContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void VerFacturasPDF(final Context BaseContext, String periodo,String nroasto) {

        respuestas="";

        final String Url = this.getMiurl() + this.getFindFacturasPDF();
        //final String Url="http://186.109.91.169/validar/buscarabonado.php";

        final RequestParams params = new RequestParams();

        params.add("periodo", periodo);
        params.add("nroasto", nroasto);
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.get(Url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {


                    String res = new String(responseBody);
                    respuestas = (String) res;

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(BaseContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void VerArticulos(final Context BaseContext, View view, String nCli) {
        respuestas="";


        final String Url = this.getMiurl() + this.getFindArticulos();
        //final String Url = "http://10.0.0.3/validar/buscararti.php";

    //final String Url="http://186.109.91.169/validar/buscarabonado.php";
        Log.d("Arti" ,Url);
        final RequestParams params = new RequestParams();

        params.add("narti", String.valueOf(nCli));
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.get(Url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {


                    String res = new String(responseBody);
                    respuestas = (String) res;

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(BaseContext, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    public  void Loggin(final Context BaseContext, View view, Editable nCli, Editable pass) {

        respuestas="";

        final String Url = this.getMiurl() + this.getFindAbonado();
        //String Url="http://186.109.91.169/validar/buscarabonado.php";

        final RequestParams params = new RequestParams();
        Log.d("Ingresar", String.valueOf(nCli));
        Log.d("Ingresar", String.valueOf(pass));
        params.add("ncli", String.valueOf(nCli));
        params.add("ndoc", String.valueOf(pass));

        AsyncHttpClient cliente = new AsyncHttpClient();
        RequestHandle a = cliente.get(Url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String res = new String(responseBody);
                    respuestas = (String) res;
                    Log.d("Ingresar", respuestas);
                    // loopjListener.taskCompleted(res);

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                respuestas = error.getMessage();
                //Toast.makeText(BaseContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        //return respuestas;
    }

    public void VerLecturas(final Context BaseContext, View view, String nCli) {


        final String Url = this.getMiurl() + this.getFindLecturas();

        respuestas="";
        final RequestParams params = new RequestParams();

        params.add("nroaux", String.valueOf(nCli));
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.get(Url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String res = new String(responseBody);
                    respuestas = (String) res;
                    Log.d("LECTURAS",res);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(BaseContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void AddLecturas(final Context BaseContext, View view, String empresa,String periodo,String tipaux,String nroaux,String feclect,String tiplectura,String consumo){


        final String Url = this.getMiurl() + this.getAddLecturas();


        final RequestParams params = new RequestParams();

        params.add("empresa", String.valueOf(empresa));
        params.add("periodo", String.valueOf(periodo));
        params.add("tipaux", String.valueOf(tipaux));
        params.add("nroaux", String.valueOf(nroaux));
        params.add("feclect", String.valueOf(feclect));
        params.add("tiplectura", String.valueOf(tiplectura));
        params.add("consumo", String.valueOf(consumo));
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.get(Url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String res = new String(responseBody);
                    respuestas = (String) res;
                    Log.d("LECTURAS",res);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(BaseContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void EnviarReclamos(final Context BaseContext, View view, firereclamos miReclamo){

        respuestas="";
        final String Url = this.getMiurl() + this.getAddReclamos();


        final RequestParams params = new RequestParams();

        params.add("nrousuario", miReclamo.usuario);
        params.add("telefono", miReclamo.telefono);
        params.add("tema", miReclamo.tema);
        params.add("descripcion", miReclamo.descripcion);

        AsyncHttpClient cliente = new AsyncHttpClient();

        cliente.get(Url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {

                    String res = new String(responseBody);
                    respuestas = (String) res;
                    Log.d("Reclamos",res);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(BaseContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}