package ar.com.erzsoftware.eruralmovil.modelos;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrador on 09/10/2017.
 */

public class FacturaPDF {
    private String Id;
    private String Empresa;
    private String periodo;
    private String nroasto;
    private String codcomp;
    private String comp;
    private String pvcomp;
    private String nrcomp;
    private String pdf;

    public String getId() {return Id;}

    public void setId(String id) {Id = id;}

    public String getEmpresa() {return Empresa;}

    public void setEmpresa(String empresa) {Empresa = empresa;}

    public String getPeriodo() {return periodo;}

    public void setPeriodo(String periodo) {this.periodo = periodo;}

    public String getNroasto() {return nroasto;}

    public void setNroasto(String nroasto) {this.nroasto = nroasto;}

    public String getCodcomp() {return codcomp;}

    public void setCodcomp(String codcomp) {this.codcomp = codcomp;}

    public String getComp() {return comp;}

    public void setComp(String comp) {this.comp = comp;}

    public String getPvcomp() {return pvcomp;}

    public void setPvcomp(String pvcomp) {this.pvcomp = pvcomp;}

    public String getNrcomp() {return nrcomp;}

    public void setNrcomp(String nrcomp) {this.nrcomp = nrcomp;}

    public String getPdf() {return pdf;}

    public void setPdf(String pdf) {this.pdf = pdf;}

    public FacturaPDF(){
        this.Id="";
        this.Empresa="";
        this.periodo="";
        this.nroasto="";
        this.codcomp="";
        this.comp="";
        this.pvcomp="";
        this.nrcomp="";
        this.pdf="";

    }

    public FacturaPDF setdesdeJSONArray(JSONObject Resultado) throws JSONException {
        this.Id=Resultado.getString("Id");
        this.Empresa=Resultado.getString("Empresa");
        this.periodo=Resultado.getString("periodo");
        this.nroasto=Resultado.getString("nroasto");
        this.codcomp=Resultado.getString("codcomp");
        this.comp=Resultado.getString("comp");
        this.pvcomp=Resultado.getString("pvcomp");
        this.nrcomp=Resultado.getString("nrcomp");


        this.pdf=Resultado.getString("pdf");



        return this;
    }
}
