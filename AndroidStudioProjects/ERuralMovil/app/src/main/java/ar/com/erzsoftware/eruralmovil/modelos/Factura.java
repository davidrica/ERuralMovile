package ar.com.erzsoftware.eruralmovil.modelos;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrador on 22/09/2017.
 */

public class Factura {
    protected String Id;
    protected String periodo;
    protected String liquidacion;
    protected String nroaux;
    protected String cesp;
    protected String pvcomp;
    protected String nrcomp;
    protected String fecomp;
    protected String feclect;
    protected String fevenc1;
    protected String medant;
    protected String medact;
    protected String consumo;
    protected String total;
    protected String estado;
    protected String pagada;
    protected String perasto;
    protected String nroasto;

    protected FacturaPDF facturapdf;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getId() {return Id;}

    public void setId(String id) {Id = id;}

    public String getNroaux() {return nroaux;}

    public void setNroaux(String nroaux) {this.nroaux = nroaux;}

    public String getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

    public String getCesp() {
        return cesp;
    }

    public void setCesp(String cesp) {
        this.cesp = cesp;
    }

    public String getPvcomp() {
        return pvcomp;
    }

    public void setPvcomp(String pvcomp) {
        this.pvcomp = pvcomp;
    }

    public String getNrcomp() {
        return nrcomp;
    }

    public void setNrcomp(String nrcomp) {
        this.nrcomp = nrcomp;
    }

    public String getFecomp() {
        return fecomp;
    }

    public void setFecomp(String fecomp) {
        this.fecomp = fecomp;
    }

    public String getFeclect() {
        return feclect;
    }

    public void setFeclect(String feclect) {
        this.feclect = feclect;
    }

    public String getFevenc1() {
        return fevenc1;
    }

    public void setFevenc1(String fevenc1) {
        this.fevenc1 = fevenc1;
    }

    public String getMedant() {
        return medant;
    }

    public void setMedant(String medant) {
        this.medant = medant;
    }

    public String getMedact() {
        return medact;
    }

    public void setMedact(String medact) {
        this.medact = medact;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPagada() {
        return pagada;
    }

    public void setPagada(String pagada) {
        this.pagada = pagada;
    }

    public String getPerasto() {return perasto;}

    public void setPerasto(String perasto) {this.perasto = perasto;}

    public String getNroasto() {return nroasto;}

    public void setNroasto(String nroasto) {this.nroasto = nroasto;}

    public Factura(){
        this.Id="";
        this.periodo="";
        this.liquidacion="";
        this.nroaux="";
        this.cesp="";
        this.pvcomp="";
        this.nrcomp="";
        this.fecomp="";
        this.feclect="";
        this.fevenc1="";
        this.medant="";
        this.medact="";
        this.consumo="";
        this.total="";
        this.estado="";
        this.pagada="";
        this.perasto="";
        this.nroasto="";
        this.facturapdf= new FacturaPDF();
    }
    public Factura setdesdeJSONArray(JSONObject Resultado, String ncli) throws JSONException {

        this.Id=Resultado.getString("Id");
        this.periodo=Resultado.getString("periodo");
        this.liquidacion=Resultado.getString("liquidacion");
        this.nroaux=ncli;
        this.cesp=Resultado.getString("cesp");
        this.pvcomp=Resultado.getString("pvcomp");
        this.nrcomp=Resultado.getString("nrcomp");
        this.fecomp=Resultado.getString("fecomp");
        this.feclect=Resultado.getString("feclect");
        this.fevenc1=Resultado.getString("fevenc1");
        this.medant=Resultado.getString("medant");
        this.medact=Resultado.getString("medact");
        this.consumo=Resultado.getString("consumo");
        this.total=Resultado.getString("total");
        this.estado=Resultado.getString("estado");
        this.pagada=Resultado.getString("pagada");
        if (Resultado.getString("perasto") == ""){
            this.perasto="0";
        }else {
            this.perasto = Resultado.getString("perasto");
        }
        if (Resultado.getString("nroasto") == ""){
            this.nroasto="0";
        }else{
            this.nroasto= Resultado.getString("nroasto");
        }

        return this;
    }

}
