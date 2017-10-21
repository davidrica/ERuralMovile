package ar.com.erzsoftware.eruralmovil.modelos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.R;
import ar.com.erzsoftware.eruralmovil.modelos.Factura;
/**
 * Created by Administrador on 22/09/2017.
 */
public abstract class MisFacturas extends BaseAdapter  {
    private ArrayList<?> entradas;
    private int R_layout_IdView;
    private Context contexto;



    public MisFacturas(Context contexto, int R_layout_IdView, ArrayList<?> entradas) {
        super();
        this.contexto = contexto;
        this.entradas = entradas;
        this.R_layout_IdView = R_layout_IdView;

    }
    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onEntrada (entradas.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return entradas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /** Devuelve cada una de las entradas con cada una de las vistas a la que debe de ser asociada
     * @param entrada La entrada que ser� la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendr� los datos del paquete/handler
     */
    public abstract void onEntrada (Object entrada, View view);
}
