package com.onurisys.yciucatitlantis;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Actualizador extends Service {

	private static Syncmanager sSyncAdapter = null;
	private static final Object sSyncAdapterLock = new Object();

	
	 @Override
	    public void onCreate() {
	        synchronized (sSyncAdapterLock) {
	            if (sSyncAdapter == null) {
	                sSyncAdapter = new Syncmanager(getApplicationContext(), true);
	            }
	        }
	    }
	 
	@Override
	public IBinder onBind(Intent arg0) {
		return sSyncAdapter.getSyncAdapterBinder();
	}


}
