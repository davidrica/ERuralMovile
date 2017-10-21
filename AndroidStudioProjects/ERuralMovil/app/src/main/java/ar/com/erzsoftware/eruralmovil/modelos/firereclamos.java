package ar.com.erzsoftware.eruralmovil.modelos;

/**
 * Created by Administrador on 10/10/2017.
 */

public class firereclamos {
    public String key;
    public  String descripcion;
    public  String estado;
    public  String fecha;
    public  String prioridad;
    public  String telefono;
    public  String tema;
    public  String usuario;

    public firereclamos(){

    }
    public firereclamos(String descripcion, String estado, String fecha, String prioridad, String telefono, String tema, String usuario) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.telefono = telefono;
        this.tema = tema;
        this.usuario = usuario;
    }

}
