package ar.com.erzsoftware.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String TAG ="package";
        try {
            //Intent sendIntent = new Intent("android.intent.action.MAIN");

/*
            //sendIntent.setAction(Intent.ACTION_SEND);
            final PackageManager pm = getPackageManager();
            List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

            for (ApplicationInfo packageInfo : packages) {
                //if (packageInfo.packageName.equals("ar.gob.jus.dnrpa.cedula")) {
                    Log.d(TAG, "Installed package :" + packageInfo.packageName);
                    Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
                    Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
                //}
            }
            */
     /*
     Launch Activity :
     START u0 {
     act=com.phonegap.plugins.barcodescanner.SCAN
     cat=[android.intent.category.DEFAULT]
      pkg=ar.gob.jus.dnrpa.cedula
      cmp=ar.gob.jus.dnrpa.cedula/com.google.zxing.client.android.CaptureActivity}
      from uid 10095

     Intent {
        act=android.intent.action.MAIN
        cat=[android.intent.category.LAUNCHER]
        flg=0x10000000
        pkg=ar.gob.jus.dnrpa.cedula
        cmp=ar.gob.jus.dnrpa.cedula/.CdulaAutomotor }
*/
/*
            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("ar.gob.jus.dnrpa.cedula");
            Log.d(TAG, String.valueOf(LaunchIntent));
            startActivity(LaunchIntent);
*/
//ADS16865 229LMJ C800
            //sendIntent.setData(Uri.parse("ADS16865 229LMJ C800"));



            Intent sendIntent = new Intent();
            sendIntent.setAction("android.intent.action.ACTION_VIEW");
            sendIntent.addCategory("android.intent.category.DEFAULT");
            sendIntent.setComponent(new ComponentName("ar.gob.jus.dnrpa.cedula","com.google.zxing.client.android.CaptureActivity"));

            sendIntent.setPackage("ar.gob.jus.dnrpa.cedula");

            //startActivityForResult(sendIntent,1);
            startActivity(sendIntent);

            // startActivity(sendIntent);
        } catch(Exception e) {
            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
