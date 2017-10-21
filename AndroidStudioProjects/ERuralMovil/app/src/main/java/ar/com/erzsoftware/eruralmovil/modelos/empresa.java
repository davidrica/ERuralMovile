package ar.com.erzsoftware.eruralmovil.modelos;

/**
 * Created by Administrador on 19/10/2017.
 * {"001":{"denom":"DEMO","host":"localhost","dbase":"erural","user":"root","pass":"cx0011","estado":"S","tipo":"L"}}
 */

public class empresa {
    public String id;
    public String denom;
    public String host;
    public String dbase;
    public String user;
    public String pass;
    public String estado;
    public String tipo;
    public String key;

    public empresa(){
        this.id="0";
        this.denom="";
        this.host="";
        this.dbase="";
        this.user="";
        this.pass="";
        this.estado="";
        this.tipo="";
        this.key="";

    }

    public empresa(String denom, String host, String dbase, String user, String pass, String estado, String tipo) {
        this.denom = denom;
        this.host = host;
        this.dbase = dbase;
        this.user = user;
        this.pass = pass;
        this.estado = estado;
        this.tipo = tipo;
    }
}
