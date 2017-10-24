package ar.com.erzsoftware.eruralmovil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ar.com.erzsoftware.eruralmovil.controladores.ctrPrincipal;
import ar.com.erzsoftware.eruralmovil.modelos.Auxiliares;
import ar.com.erzsoftware.eruralmovil.modelos.empresa;
import ar.com.erzsoftware.eruralmovil.modelos.firereclamos;


public class MainActivity extends AppCompatActivity {
    public static ctrPrincipal mictrPrincipal ;
    public static Auxiliares miauxi = new Auxiliares();
    public static empresa miEmpresa= new empresa();
    EditText txtclie ;
    EditText txtpass ;
    EditText txtempresa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtclie = (EditText)findViewById(R.id.edcliente);
        txtpass = (EditText)findViewById(R.id.edpass);
        txtempresa = (EditText)findViewById(R.id.edSelEmpresa);





        mictrPrincipal = new ctrPrincipal(getBaseContext()) ;

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
            Log.d("DataBase Empresa", miEmpresa.denom);
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
