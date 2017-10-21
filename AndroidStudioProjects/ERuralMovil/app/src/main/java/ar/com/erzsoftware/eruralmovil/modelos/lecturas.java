package ar.com.erzsoftware.eruralmovil.modelos;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrador on 04/10/2017.
 */

public class lecturas {
    private String Id;
    private String Empresa;
    private String periodo;
    private String tipaux;
    private String nroaux;
    private String feclect;
    private String tiplectura;
    private int consumo;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTipaux() {
        return tipaux;
    }

    public void setTipaux(String tipaux) {
        this.tipaux = tipaux;
    }

    public String getNroaux() {
        return nroaux;
    }

    public void setNroaux(String nroaux) {
        this.nroaux = nroaux;
    }

    public String getFeclect() {
        return feclect;
    }

    public void setFeclect(String feclect) {
        this.feclect = feclect;
    }

    public String getTiplectura() {
        return tiplectura;
    }

    public void setTiplectura(String tiplectura) {
        this.tiplectura = tiplectura;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public lecturas(){
        this.Id="";
        this.Empresa="";
        this.periodo="";
        this.tipaux="";
        this.nroaux="";
        this.feclect="";
        this.tiplectura="";
        this.consumo=0;

    }
    public lecturas setdesdeJSONArray(JSONObject Resultado, String ncli) throws JSONException {

        this.Id=Resultado.getString("Id");
        this.Empresa=Resultado.getString("Empresa");
        this.periodo=Resultado.getString("periodo");
        this.tipaux=Resultado.getString("tipaux");
        this.nroaux=Resultado.getString("nroaux");
        this.feclect=Resultado.getString("feclect");
        this.tiplectura=Resultado.getString("tiplectura");
        this.consumo= Integer.parseInt(Resultado.getString("consumo"));
        return this;
    }
}
