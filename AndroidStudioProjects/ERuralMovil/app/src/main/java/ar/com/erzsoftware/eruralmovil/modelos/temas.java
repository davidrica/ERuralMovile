package ar.com.erzsoftware.eruralmovil.modelos;

/**
 * Created by Administrador on 23/10/2017.
 */
// {"001":{"desc":"Corte en el Suministro"}}
// {"002":{"desc":"Cable Cortado o Por cortarse"}}
// {"003":{"desc":"Columna/Poste Caido"}}




public class temas {
    public String id;
    public String desc;
    public String key;
    public temas (){
        this.id="0";
        this.desc="";
        this.key="";
    }

    public temas( String desc) {
        this.desc = desc;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getDesc() {return desc;}

    public void setDesc(String desc) {this.desc = desc;}

    public String getKey() {return key;}

    public void setKey(String key) {this.key = key;}
}
