package com.onurisys.yciucatitlantis;

import android.app.*;
import android.os.*;
import android.util.Log;

public class NuevaCuentaActivity  extends Activity{
	 @Override
	    public void onCreate(Bundle savedInstanceState)
		{
		 Log.v("NUEVACUENTA", "ACTIVIDAD INICIADA");
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.nuevacuenta);
	    }
	 
}
