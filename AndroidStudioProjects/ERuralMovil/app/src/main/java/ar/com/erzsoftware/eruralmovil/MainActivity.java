package ar.com.erzsoftware.eruralmovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.empresa;



public class MainActivity extends AppCompatActivity {
    public static ctrPrincipal mictrPrincipal ;
    public static Auxiliares miauxi = new Auxiliares();
    public static empresa miEmpresa= new empresa();

   // ProgressBar progressBar;
    EditText txtclie ;
    EditText txtpass ;
    EditText txtempresa;
    Button btnIngresar;
    TextView lblcliente;
    TextView       lblpass;
    TextView lblEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtclie = (EditText)findViewById(R.id.edcliente);
        txtpass = (EditText)findViewById(R.id.edpass);
        txtempresa = (EditText)findViewById(R.id.edSelEmpresa);
       // progressBar =(ProgressBar)findViewById(R.id.progressBar);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        lblcliente = (TextView) findViewById(R.id.lblcliente);
        lblEmpresa = (TextView) findViewById(R.id.lblEmpresa);
        lblpass = (TextView) findViewById(R.id.lblpass);
/*
        txtclie.setVisibility(View.INVISIBLE);
        txtpass.setVisibility(View.INVISIBLE);
        txtempresa.setVisibility(View.INVISIBLE);
        btnIngresar.setVisibility(View.INVISIBLE);
        lblcliente.setVisibility(View.INVISIBLE);
        lblEmpresa.setVisibility(View.INVISIBLE);
        lblpass.setVisibility(View.INVISIBLE);
*/

        mictrPrincipal = new ctrPrincipal(getBaseContext()) ;
        final FirebaseAuth auth = mictrPrincipal.getCtrfire().getmAuth();
        //authenticate user
        //if (auth.getCurrentUser() != null) {

            auth.signInWithEmailAndPassword("mipruebadavid@prueba.com", "prueba")
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            //progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {

                                Toast.makeText(MainActivity.this, "Error login", Toast.LENGTH_LONG).show();

                            } else {
                                /*
                                txtclie.setVisibility(View.VISIBLE);
                                txtpass.setVisibility(View.VISIBLE);
                                txtempresa.setVisibility(View.VISIBLE);
                                btnIngresar.setVisibility(View.VISIBLE);
                                lblcliente.setVisibility(View.VISIBLE);
                                lblEmpresa.setVisibility(View.VISIBLE);
                                lblpass.setVisibility(View.VISIBLE);
                                */
                                mictrPrincipal.getCtrfire().setmAuth(auth);
                            }
                        }
                    });
       // }else{
            mictrPrincipal.getCtrfire().setmAuth(auth);
       // }
        txtclie.setText("");
        txtpass.setText("");
        txtempresa.setText("");

        miauxi =mictrPrincipal.getMiAuxi();

        txtclie.setText(miauxi.getnroaux());
        if (miauxi.getnrodoc()!="0") {
            txtpass.setText(miauxi.getnrodoc());
        }else{
            txtpass.setText(miauxi.getcuit());
        }

        miEmpresa =mictrPrincipal.getMiEmp();


        if (miEmpresa.id !="0"){
            txtempresa.setText(miEmpresa.key);
        }
    }

    public  void descargar(final View view) {

        if (txtempresa.length() == 0) {
            //seleccionar empresa
            SeleccionarEmpresa();

        }
        if (txtclie.length() != 0) {
            if (txtpass.length() != 0) {
                if (txtempresa.length() != 0) {

                    mictrPrincipal.Ingresar(getBaseContext(), view, txtclie.getText(), txtpass.getText(), MainActivity.this);
                    /*if (mictrPrincipal.getIngreso()) {
                        Intent intent = new Intent(getBaseContext(), activity_Welcome.class);
                        startActivity(intent);
                    }
*/
                }else{
                    Snackbar.make(view, "No ha seleccionado ninguna empresa. Favor de Seleccionar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            } else {

                Toast.makeText(getBaseContext(), "Debe Ingresar el Nro de Cliente y/o la Contraseña..", Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(getBaseContext(), "Debe Ingresar el Nro de Cliente y/o la Contraseña..", Toast.LENGTH_LONG).show();
        }

    }

    private void SeleccionarEmpresa() {

        Intent intent = new Intent(getBaseContext(), selempresa.class);
        startActivity(intent);
        finish();
    }

}
