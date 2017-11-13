package ar.com.erzsoftware.eruralmovil;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.modelos.firereclamos;

public class reclamosadd extends AppCompatActivity {
    public final static ctrPrincipal mictrPrincipal = MainActivity.mictrPrincipal;

    public Date fecreclamo= new Date();
    public TextView edFecha;
    public EditText edDescr;
    public Spinner spTemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamosadd);

        edFecha=(TextView)findViewById(R.id.edAddReclamosFecha);
        edDescr=(EditText)findViewById(R.id.edAddReclamosDesc);
        spTemas = (Spinner) findViewById(R.id.spTemas);

        edFecha.setText(DateFormat.format("dd/MM/yyyy H:M:s", fecreclamo.getTime()));
        edDescr.setText("");

        final List<String> list = mictrPrincipal.getCtrfire().aTemas;

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        spTemas.setAdapter(adp1);

    }


    public void ReclamosCerrar(View view){finish();}

    public void ReclamosEnviar(View view){

        DatabaseReference myRef = mictrPrincipal.getCtrfire().getRefReclamos();

        firereclamos mi = new firereclamos();


        mi.key = myRef.push().getKey();

        mi.descripcion=edDescr.getText().toString();
        mi.tema=spTemas.getSelectedItem().toString();
        mi.usuario=mictrPrincipal.getMiAuxi().getnroaux();
        mi.estado="ENVIADO";
        mi.fecha=edFecha.getText().toString();
        mi.prioridad="n";
        mi.telefono="asdf";

        myRef.child(mi.key).setValue(mi);
        Log.d("Firebase", "fin");
        finish();
    }
}
