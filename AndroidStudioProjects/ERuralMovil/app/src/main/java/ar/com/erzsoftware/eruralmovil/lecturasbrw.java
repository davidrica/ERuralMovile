package ar.com.erzsoftware.eruralmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.datos.LecturasDB;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.BaseAdapters.MisLecturas;
import ar.com.erzsoftware.eruralmovil.modelos.lecturas;

public class lecturasbrw extends AppCompatActivity {

    public final static ctrPrincipal mictrPrincipal = MainActivity.mictrPrincipal;

    public final static Auxiliares miauxi = MainActivity.miauxi;

    public LecturasDB dblect;
    public ArrayList<lecturas> datoslect = new ArrayList<lecturas>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lecturasbrw);




        Intent intent = getIntent();


        CargarLecturas();


    }
    public void CargarLecturas(){

        dblect = new LecturasDB(getBaseContext());
        mictrPrincipal.setDblect(dblect );
        datoslect =mictrPrincipal.getDatoslect();

        ListView lvstring = (ListView) findViewById(R.id.ListView_lecturas);

        lvstring.setAdapter(new MisLecturas(this, R.layout.activity_lecturasbrw, datoslect){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView txtid = view.findViewById(R.id.txtVto1);
                    if (txtid!= null) {
                        txtid.setText(((lecturas) entrada).getId());
                    }
                    TextView txtPeriodo = view.findViewById(R.id.txtPeriodo);
                    if (txtPeriodo!= null) {
                        txtPeriodo.setText(((lecturas) entrada).getPeriodo());
                    }
                    TextView txtConsumo= view.findViewById(R.id.txtConsumo);
                    if (txtConsumo!= null) {
                        int con = ((lecturas) entrada).getConsumo();
                        txtConsumo.setText(String.valueOf(con));
                    }
                }


            }
        });
    }

    public void LecturasActualiza(final View view){
        mictrPrincipal.BuscarLecturasUrl(getBaseContext(),view,lecturasbrw.this);
        finish();
    }

    public void LecturasCerrar(View view){
        finish();
    }
    public void LecturasAdd(View view) {
        Intent intent = new Intent(getBaseContext(), lecturasAddEdit.class);
        startActivity(intent);
        finish();

    }

}
