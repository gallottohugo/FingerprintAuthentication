package com.example.fingerprintauth;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private FingerprintManager fingerprintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Boolean loginFingerPrint = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            loginFingerPrint = false;
        }
        else{
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (!fingerprintManager.isHardwareDetected()){
                loginFingerPrint = false;
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED ){
                loginFingerPrint = false;
            } else if (!fingerprintManager.hasEnrolledFingerprints()){
                loginFingerPrint = false;
            } else {
                loginFingerPrint = true;
            }
        }
        setNextButton(loginFingerPrint);
    }
    private void setNextButton(boolean loginFingerPrint){
        Button nextButton = (Button) findViewById(R.id.buttonNext);
        if (loginFingerPrint == true){
            nextButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)  {
                    startActivity(new Intent(MainActivity.this, LoginFingerprint.class));
                }
            });
        }
        else{
            nextButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)  {
                    startActivity(new Intent(MainActivity.this, LoginUserPass.class));
                }
            });
        }
    }
}
