package ar.com.erzsoftware.eruralmovil.controladores;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ar.com.erzsoftware.eruralmovil.MainActivity;
import ar.com.erzsoftware.eruralmovil.modelos.empresa;
import ar.com.erzsoftware.eruralmovil.modelos.firereclamos;
import ar.com.erzsoftware.eruralmovil.modelos.temas;

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
    private DatabaseReference  refTemas = null;
    private ValueEventListener lisTemas= null;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public ArrayList<empresa> datos = new ArrayList<empresa>();
    public List<String> aTemas = new ArrayList<String>();

    String email="mipruebadavid@prueba.com";
    String password="prueba";


    public ctrfirebase(){
        this.mAuth = FirebaseAuth.getInstance();
        this.refGeneral  = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/");
        this.refReclamos = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/reclamos");
        this.refEmpresas = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/empresas");
        this.refEmpresa  = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/empresa");
        this.refUsuarios = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/usuarios");
        this.refTemas    = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reclamos-d7bb3.firebaseio.com/temas");

        this.lisReclamos=refReclamos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    //String nKey = childSnapshot.getValue(String.class);
                    firereclamos value = childSnapshot.getValue(firereclamos.class);

                    value.key = childSnapshot.getKey();
                    //key
                   //Log.d("reclamos_ctr", value.key);
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
                    //Log.d("Usuarios_ctr", "falta.. codigo");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {Log.d("Firebase", "Error en lectura Usuarios.", error.toException());}
        });

        this.lisTemas   =refTemas.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aTemas.clear();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                    temas value = childSnapshot.getValue(temas.class);
                    value.key = childSnapshot.getKey();
                    //Log.d("temas", value.key);
                    aTemas.add(value.desc);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        this.mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Firebase","onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Firebase", "onAuthStateChanged:signed_out");

                }
                // ...
            }
        };
        FireLogin();
   }

    public void FireLogin() {
        this.mAuth.signInWithEmailAndPassword(email, password);
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

    public ValueEventListener getLisReclamos() {return lisReclamos;}

    public void setLisReclamos(ValueEventListener lisReclamos) {this.lisReclamos = lisReclamos;}

    public ValueEventListener getLisEmpresas() {return lisEmpresas;}

    public void setLisEmpresas(ValueEventListener lisEmpresas) {this.lisEmpresas = lisEmpresas;}

    public ValueEventListener getLisEmpresa() {return lisEmpresa;}

    public void setLisEmpresa(ValueEventListener lisEmpresa) {this.lisEmpresa = lisEmpresa;}

    public ValueEventListener getLisUsuarios() {return lisUsuarios;}

    public void setLisUsuarios(ValueEventListener lisUsuarios) {this.lisUsuarios = lisUsuarios;}

    public DatabaseReference getRefTemas() {return refTemas;}

    public void setRefTemas(DatabaseReference refTemas) {this.refTemas = refTemas;}

    public ValueEventListener getLisTemas() {return lisTemas;}

    public void setLisTemas(ValueEventListener lisTemas) {this.lisTemas = lisTemas;}

    public FirebaseAuth getmAuth() {return mAuth;}

    public void setmAuth(FirebaseAuth mAuth) {this.mAuth = mAuth;}

    public FirebaseAuth.AuthStateListener getmAuthListener() {return mAuthListener;}

    public void setmAuthListener(FirebaseAuth.AuthStateListener mAuthListener) {this.mAuthListener = mAuthListener;}
}
