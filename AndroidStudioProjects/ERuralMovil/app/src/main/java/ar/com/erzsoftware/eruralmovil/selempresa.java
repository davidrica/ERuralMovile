package ar.com.erzsoftware.eruralmovil;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.BaseAdapters.MiselEmpresaBaseAdapter;
import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.modelos.empresa;

public class selempresa extends AppCompatActivity {
    public final static ctrPrincipal mictrPrincipal = MainActivity.mictrPrincipal;

    public ListView lvstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_selempresa);
        ArrayList<empresa> datos = mictrPrincipal.getCtrfire().datos;


        if (!datos.isEmpty()) {

            lvstring = (ListView) findViewById(R.id.ListView_empresas);

            lvstring.setAdapter(new MiselEmpresaBaseAdapter(this, R.layout.activity_selempresa, datos) {

                @Override
                public void onEntrada(Object entrada, View view) {
                    if (entrada != null) {
                        TextView texto_superior_entrada = view.findViewById(R.id.txtEmpresaDenom);
                        if (texto_superior_entrada != null) {
                            texto_superior_entrada.setText(((empresa) entrada).denom);
                        }
                        TextView texto_inferior_entrada = view.findViewById(R.id.txtEmpresaBase);
                        if (texto_inferior_entrada != null) {
                            texto_inferior_entrada.setText(((empresa) entrada).dbase);
                        }
                        TextView actual= view.findViewById(R.id.txtEmpresaHost);
                        if (actual != null) {
                            actual.setText(((empresa) entrada).host);
                        }

                    }
                }
            });
            lvstring.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Object listItem = lvstring.getItemAtPosition(position);
                    //borramos empresa seleccionada
                    mictrPrincipal.getDbempresa().Vaciar();
                    mictrPrincipal.getDbempresa().guardarEmpresa((empresa)listItem );
                    //Snackbar.make(view, "Acceder como:" + ((empresa) listItem).denom, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                    return false;
                }
            });
            lvstring.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Snackbar.make(view, "Para seleccionar la empresa, Mantenga Presionado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }

    }

    public void SelempresaCerrar(View view){        finish();    }
}
