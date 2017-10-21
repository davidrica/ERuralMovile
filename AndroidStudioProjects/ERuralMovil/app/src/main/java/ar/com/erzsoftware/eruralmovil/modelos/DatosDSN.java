package ar.com.erzsoftware.eruralmovil.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrador on 19/09/2017.
 */

public class DatosDSN {
    protected String host;
    protected String db;
    protected String user;
    protected String pass;

    public DatosDSN (){
        this.host="-1";
        this.db="";
        this.user="";
        this.pass="";
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public DatosDSN setdesdeJSONArray(JSONArray Resultado) throws JSONException {
        JSONArray mainObject = Resultado;
        for(int i=0; i < mainObject.length(); i++) {

            JSONObject jsonobject = mainObject.getJSONObject(i);
            JSONObject jsonobject2 = jsonobject.getJSONObject("hosts");
            this.setHost(jsonobject2.getString("host"));
            this.setDb(jsonobject2.getString("db"));
            this.setUser(jsonobject2.getString("user"));
            this.setPass(jsonobject2.getString("pass"));
        }
        return this;
    }
}
