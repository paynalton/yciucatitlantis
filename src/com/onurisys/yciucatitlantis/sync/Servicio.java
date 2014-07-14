package com.onurisys.yciucatitlantis.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.onurisys.yciucatitlantis.sync.adapter;

public class Servicio extends Service  {
	private static adapter sSyncAdapter = null;
	private static final Object sSyncAdapterLock = new Object();
	@Override
    public void onCreate() {
        /*
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new adapter(getApplicationContext(), true);
            }
        }
    }
    /**
     * Return an object that allows the system to invoke
     * the sync adapter.
     *
     */
    @Override
    public IBinder onBind(Intent intent) {
        /*
         * Get the object that allows external processes
         * to call onPerformSync(). The object is created
         * in the base class code when the SyncAdapter
         * constructors call super()
         */
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
