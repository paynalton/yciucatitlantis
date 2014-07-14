package com.onurisys.yciucatitlantis.sync.cuentas;

import com.onurisys.yciucatitlantis.sync.cuentas.Autentificador;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
 
/**
 * Authenticator service that returns a subclass of AbstractAccountAuthenticator in onBind()
 */
public class Servicio extends Service {
 private Autentificador mAuthenticator;
 @Override
 public void onCreate() {
	 Log.v("SERVICIO","instanciando autentificador");
     mAuthenticator = new Autentificador(this);
 }
 
 public IBinder onBind(Intent intent) {
	 Log.v("SERVICIO","sirviendo el autentificador");
	 return mAuthenticator.getIBinder();
 }
}