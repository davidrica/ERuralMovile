package ar.com.erzsoftware.eruralmovil.controladores;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ar.com.erzsoftware.eruralmovil.modelos.empresa;
import ar.com.erzsoftware.eruralmovil.modelos.firereclamos;

/**
 * Created by Administrador on 19/10/2017.
 */

public class ctrfirebase {

    private DatabaseReference refGeneral  = null;

    private DatabaseReference  refReclamos = null;
    private ValueEventListener lisReclamos = null;
    private DatabaseReference  refEmpresas = null;
    private ValueEventListener lisEmpresas = null;
    private DatabaseReference  refEmpresa  = null;
    private ValueEventListener lisEmpresa  = null;
    private DatabaseReference  refUsuarios = null;
    private ValueEventListener lisUsuarios = null;
    public ArrayList<empresa> datos = new ArrayList<empresa>();

    public ctrfirebase(){
        this.refGeneral  = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/");
        this.refReclamos = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/reclamos");
        this.refEmpresas = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/empresas");
        this.refEmpresa  = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/empresa");
        this.refUsuarios = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/usuarios");
        this.lisReclamos=refReclamos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    //String nKey = childSnapshot.getValue(String.class);
                    firereclamos value = childSnapshot.getValue(firereclamos.class);

                    value.key = childSnapshot.getKey();
                    //key
                    Log.d("reclamos_ctr", value.key);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {Log.d("Firebase", "Error en lectura Reclamos.", error.toException());}
        });

        this.lisEmpresas=refEmpresas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datos.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    //String nKey = childSnapshot.getValue(String.class);
                    empresa value = childSnapshot.getValue(empresa.class);

                    value.key = childSnapshot.getKey();
                    //key
                    datos.add(value);
//                    Log.d("empresa_ctr", value.key);
//                    Log.d("empresa_ctr", value.denom);
                }
            }

            @Override
            protected void finalize() throws Throwable {

            }

            @Override
            public void onCancelled(DatabaseError error) {Log.d("Firebase", "Error en lectura Empresas.", error.toException());}
        });

        this.lisUsuarios=refUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Log.d("Usuarios_ctr", "falta.. codigo");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {Log.d("Firebase", "Error en lectura Usuarios.", error.toException());}
        });



    }
    public DatabaseReference getRefGeneral() {return refGeneral;}

    public void setRefGeneral(DatabaseReference refGeneral) {this.refGeneral = refGeneral;}

    public DatabaseReference getRefReclamos() {return refReclamos;}

    public void setRefReclamos(DatabaseReference refReclamos) {this.refReclamos = refReclamos;}

    public DatabaseReference getRefEmpresas() {return refEmpresas;}

    public void setRefEmpresas(DatabaseReference refEmpresas) {this.refEmpresas = refEmpresas;}

    public DatabaseReference getRefEmpresa() {return refEmpresa;}

    public void setRefEmpresa(DatabaseReference refEmpresa) {this.refEmpresa = refEmpresa;}

    public DatabaseReference getRefUsuarios() {return refUsuarios;}

    public void setRefUsuarios(DatabaseReference refUsuarios) {this.refUsuarios = refUsuarios;}

}
