package com.example.samyuktha.fingerprintdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by samyuktha on 10/17/2017.
 */

public class FingerprintDemo extends FingerprintManager.AuthenticationCallback {


    private CancellationSignal cancellationSignal;
    private Context appContext;

    public FingerprintDemo(Context context) {
        appContext = context;
    }

    public void startAuth(FingerprintManager manager,
                          FingerprintManager.CryptoObject cryptoObject) {

        cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);

        Toast.makeText(appContext, "logged in successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(appContext,Main2Activity.class);
        appContext.startActivity(i);

    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(appContext, "log in failed", Toast.LENGTH_SHORT).show();
    }
}
