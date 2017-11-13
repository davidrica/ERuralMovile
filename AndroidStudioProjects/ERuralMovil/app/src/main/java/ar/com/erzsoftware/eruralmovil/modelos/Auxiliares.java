package ar.com.erzsoftware.eruralmovil.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ar.com.erzsoftware.eruralmovil.BaseAdapters.MisFacturas;

/**
 * Created by Administrador on 19/09/2017.
 */

public class Auxiliares {
    protected String id ;
    protected String empresa;
    protected String tipaux;
    protected String nroaux;
    protected String denom;
    protected String tele;
    protected String dire;
    protected String loca;
    protected String codloca;
    protected String postal;
    protected String prov;
    protected String siva;
    protected String tipdoc;
    protected String nrodoc;
    protected String cuit;

    protected MisFacturas facturas;

    public Auxiliares(){
        this.id ="0";
        this.empresa="";
        this.tipaux="";
        this.nroaux="";
        this.denom="";
        this.tele="";
        this.dire="";
        this.loca="";
        this.codloca="";
        this.postal="";
        this.prov="";
        this.siva="";
        this.tipdoc="";
        this.nrodoc="";
        this.cuit="";
        //this.facturas= new MisFacturas();
    }
    public void setId(String id){
        this.id =id;
    }
    public String getId(){
        return id;
    }
    //==============================================
    public void setempresa(String empresa){
        this.empresa=empresa;
    }
    public String getempresa(){
        return empresa;
    }
    //==============================================
    public void settipaux(String tipaux){
        this.tipaux=tipaux;
    }
    public String gettipaux(){
        return tipaux;
    }
    //==============================================
    public void setnroaux(String nroaux){
        this.nroaux=nroaux;
    }
    public String getnroaux(){
        return nroaux;
    }
    //==============================================
    public void setdenom(String denom){
        this.denom=denom;
    }
    public String getdenom(){
        return denom;
    }
    //==============================================
    public void settele(String tele){
        this.tele=tele;
    }
    public String gettele(){
        return tele;
    }
    //==============================================
    public void setdire(String dire){
        this.dire=dire;
    }
    public String getdire(){
        return dire;
    }
    //==============================================
    public void setloca(String loca){
        this.loca=loca;
    }
    public String getloca(){
        return loca;
    }
    //==============================================
    public void setcodloca(String codloca){
        this.codloca=codloca;
    }
    public String getcodloca(){
        return codloca;
    }
    //==============================================
    public void setpostal(String postal){
        this.postal=postal;
    }
    public String getpostal(){
        return postal;
    }
    //==============================================
    public void setprov(String prov){
        this.prov=prov;
    }
    public String getprov(){
        return prov;
    }
    //==============================================
    public void setsiva(String siva){
        this.siva=siva;
    }
    public String getsiva(){
        return siva;
    }
    //==============================================
    public void settipdoc(String tipdoc){
        this.tipdoc=tipdoc;
    }
    public String gettipdoc(){
        return tipdoc;
    }
    //==============================================
    public void setnrodoc(String nrodoc){
        this.nrodoc=nrodoc;
    }
    public String getnrodoc(){
        return nrodoc;
    }
    //==============================================
    public void setcuit(String cuit){
        this.cuit=cuit;
    }
    public String getcuit(){
        return cuit;
    }


    public Auxiliares setdesdeJSONArray(JSONArray Resultado) throws JSONException {
        JSONArray mainObject = Resultado;
        for(int i=0; i < mainObject.length(); i++) {

            JSONObject jsonobject = mainObject.getJSONObject(i);
            this.setId(jsonobject.getString("Id"));
            this.setempresa(jsonobject.getString("empresa"));
            this.settipaux(jsonobject.getString("tipaux"));
            this.setnroaux(jsonobject.getString("nroaux"));
            this.setdenom(jsonobject.getString("denom"));
            this.settele(jsonobject.getString("tele"));
            this.setdire(jsonobject.getString("dire"));
            this.setloca(jsonobject.getString("loca"));
            this.setcodloca(jsonobject.getString("codloca"));
            this.setpostal(jsonobject.getString("postal"));
            this.setprov(jsonobject.getString("prov"));
            this.setsiva(jsonobject.getString("siva"));
            this.settipdoc(jsonobject.getString("tipdoc"));
            this.setnrodoc(jsonobject.getString("nrodoc"));
            this.setcuit(jsonobject.getString("cuit"));

        }
        return this;
    }

}
