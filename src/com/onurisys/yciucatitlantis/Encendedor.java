package com.onurisys.yciucatitlantis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Encendedor extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Log.v("ENCENDEDOR","SERVICIO INICIADO");
	}

}
